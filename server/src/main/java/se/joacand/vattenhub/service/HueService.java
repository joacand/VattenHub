package se.joacand.vattenhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import se.joacand.vattenhub.dataaccess.IConfigRepository;
import se.joacand.vattenhub.domain.Config;
import se.joacand.vattenhub.domain.LightState;
import se.joacand.vattenhub.domain.hue.LinkResponse;

@Service
public class HueService implements IHueService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final IConfigRepository configRepository;
	private final String defaultHueUrl;
	private final String defaultHueConfigId;
	private final String defaultHuePassword;
	private final RestTemplate restTemplate;
	private String hueRestUrl;
	private String user;

	public HueService(IConfigRepository configRepository) {
		try (AbstractApplicationContext context = new ClassPathXmlApplicationContext("vattenhubConfig.xml")) {
			defaultHueUrl = (String) context.getBean("defaultHueUrl");
			defaultHueConfigId = (String) context.getBean("defaultHueConfigId");
			defaultHuePassword = (String) context.getBean("defaultHuePassword");
		}

		this.configRepository = configRepository;

		LoadConfigFromRepository();

		restTemplate = new RestTemplate();
	}

	private void LoadConfigFromRepository() {
		Optional<Config> config = configRepository.findById("0");

		if (config.isPresent()) {
			logger.info("Config found in database");
			logger.info(config.toString());
			LoadFromConfig(config.get());
		} else {
			logger.info("No config found in database - adding default configuration");
			Config newConfig = new Config(defaultHueConfigId, defaultHuePassword, defaultHueUrl);
			configRepository.save(newConfig);
			LoadFromConfig(newConfig);
		}
	}

	private void LoadFromConfig(Config config) {
		hueRestUrl = config.bridgeUrl;
		user = config.hueUser;
	}

	@Override
	public boolean changeState(LightState lightState) {
		logger.info("changeState called");

		String url = hueRestUrl + user + "/lights/" + lightState.getLight() + "/state";

		logger.info("Sending light state change to " + url);
		restTemplate.put(url, "{\"on\":" + lightState.getOn() + "}");

		return true;
	}

	// Button needs to be pressed before this is called
	public boolean registerAccount() {
		logger.info("registerAccount called");

		String url = hueRestUrl;

		HttpEntity<String> entity = new HttpEntity<String>("{ \"devicetype\": \"vattenhub\" }");
		ResponseEntity<List<LinkResponse>> lr = restTemplate.exchange(url, HttpMethod.POST, entity,
				new ParameterizedTypeReference<List<LinkResponse>>() {
				});
		List<LinkResponse> result = lr.getBody();

		for (LinkResponse authResponse : result) {

			if (authResponse.getSuccess() != null) {
				logger.info("Hue register successful");
				user = authResponse.getSuccess().getUsername();
				SaveUserToDatabase(user);
				return true;
			}
			logger.info("Error when trying to authenticate to HUE: " + authResponse.getError().getDescription());
		}
		logger.error("Hue register failed");
		return false;
	}

	private void SaveUserToDatabase(String user) {
		Config newConfig = new Config("0", user, hueRestUrl);
		configRepository.save(newConfig);
	}

}