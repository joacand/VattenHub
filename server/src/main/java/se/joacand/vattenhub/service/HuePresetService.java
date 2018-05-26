package se.joacand.vattenhub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;
import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.hue.HueOption;
import se.joacand.vattenhub.domain.hue.HuePreset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class HuePresetService implements IHuePresetService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IHueService hueService;
    private List<HuePreset> presets = new ArrayList<>();

    public HuePresetService(IHueService hueService) {
        this.hueService = hueService;

        List<InputStream> inputStreams = new ArrayList<>();
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            Resource[] resources = resolver.getResources("classpath*:HuePresets/*.json");
            for (Resource resource : resources) {
                inputStreams.add(resource.getInputStream());
            }
        } catch (IOException | NullPointerException e) {
            logger.error("Error trying to find hue presets");
            CleanUpInputStreams(inputStreams);
            return;
        }

        for (final InputStream inputStream : inputStreams) {
            try {
                String json = readFileIntoString(inputStream);
                logger.info(json);
                HuePreset preset = new ObjectMapper().readValue(json, HuePreset.class);
                logger.info("Found preset: " + preset.getName() + " description: " + preset.getDescription());
                presets.add(preset);
            } catch (IOException e) {
                logger.error("Exception during deserialization of HuePreset: " + e);
            }
        }

        CleanUpInputStreams(inputStreams);
        logger.info("Presets loaded: " + Integer.toString(presets.size()));
    }

    private void CleanUpInputStreams(List<InputStream> inputStreams) {
        logger.info("Cleaning up input streams");
        for (int i = 0; i < inputStreams.size(); i++) {
            InputStream is = inputStreams.get(i);
            try {
                is.close();
                inputStreams.remove(i);
            } catch (IOException e) {
                logger.error("Exception when closing input stream: " + e);
            }
        }
    }

    private String readFileIntoString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    @Override
    public List<HuePreset> getHuePresets() {
        return presets;
    }

    @Override
    public HueResult startHuePreset(String name) {
        for (HuePreset preset : presets) {
            if (preset.getName().equals(name)) {
                logger.warn("Starting preset: " + name);
                startPreset(preset);
                return new HueResult(true);
            }
        }
        logger.warn("Failed to start preset, preset not found: " + name);
        return new HueResult(false);
    }

    private void startPreset(HuePreset preset) {
        logger.info(preset.getName() + " " + preset.getDescription());

        HueOption[] optionsArr = preset.getHueOptions();
        int[] lights = preset.getLights();
        if (optionsArr == null || lights == null) {
            logger.error("Null objects in hueoptions");
            return;
        }

        List<HueOption> options = Arrays.asList(optionsArr);

        HashMap<String, Object> jsonVals = new HashMap<>();
        for (HueOption option : options) {
            jsonVals.put(option.getKey(), option.getValue());
        }

        try {
            this.hueService.sendRaw(jsonVals, lights);
        } catch (HueApiException e) {
            logger.info("Exception during hue request " + e.toString());
        }
        logger.warn("Sent to hue service: " + preset.getName());
    }
}
