package se.joacand.vattenhub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.hue.HueOption;
import se.joacand.vattenhub.domain.hue.HuePreset;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
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

        File folder = null;
        try {
            URI uri = this.getClass().getResource("/HuePresets").toURI();
            folder = new File(uri);
        } catch (URISyntaxException | NullPointerException e) {
            logger.error("Error trying to find hue preset directory");
        }

        if (!folder.isDirectory()) {
            logger.error("Could not find hue presets directory or it is not a directory");
            return;
        }

        for (final File file : folder.listFiles()) {
            try {
                String json = readFileIntoString(file);
                logger.info(json);
                HuePreset preset = new ObjectMapper().readValue(json, HuePreset.class);
                logger.info("Found preset: " + preset.getName() + " description: " + preset.getDescription());
                presets.add(preset);
            } catch (IOException e) {
                logger.error("Exception during deserialization of HuePreset: " + e);
            }
        }

        startHuePreset("EverythingOff");
    }

    private String readFileIntoString(File file) throws IOException {
        InputStream inputStream = new FileInputStream(file);

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

        this.hueService.sendRaw(jsonVals, lights);
        logger.warn("Sent to hue service: " + preset.getName());
    }
}
