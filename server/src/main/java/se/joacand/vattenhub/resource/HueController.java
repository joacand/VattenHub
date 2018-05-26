package se.joacand.vattenhub.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.LightAction;
import se.joacand.vattenhub.domain.LightInfo;
import se.joacand.vattenhub.domain.LightState;
import se.joacand.vattenhub.domain.hue.HuePreset;
import se.joacand.vattenhub.service.HueApiException;
import se.joacand.vattenhub.service.IHueActionService;
import se.joacand.vattenhub.service.IHuePresetService;
import se.joacand.vattenhub.service.IHueService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("hue")
public class HueController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IHueService hueService;
    private final String apiver = "/api/v1/";
    private final IHueActionService hueActionService;
    private final IHuePresetService huePresetService;

    public HueController(IHueService hueService, IHueActionService hueActionService, IHuePresetService huePresetService) {
        this.hueService = hueService;
        this.hueActionService = hueActionService;
        this.huePresetService = huePresetService;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "lights", method = RequestMethod.POST)
    public HueResult lights(@RequestBody LightState input) {
        boolean res = false;
        try {
            res = hueService.changeState(input);
        } catch (HueApiException e) {
            logger.info("Hue API exception: " + e.toString());
        }
        return new HueResult(res);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "lights", method = RequestMethod.GET)
    public LightInfo lights() {
        try {
            return hueService.getLights();
        } catch (HueApiException e) {
            logger.info("Hue API exception: " + e.toString());
        }
        return new LightInfo(new ArrayList<>());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "register", method = RequestMethod.POST)
    public HueResult register() {
        boolean res = hueService.registerAccount();
        return new HueResult(res);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "action", method = RequestMethod.POST)
    public HueResult action(@RequestBody LightAction input) {
        return hueActionService.startLightAction(input);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "action", method = RequestMethod.GET)
    @ResponseBody
    public List<String> action() {
        return hueActionService.getLightActions();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "preset", method = RequestMethod.GET)
    @ResponseBody
    public List<HuePreset> preset() {
        return huePresetService.getHuePresets();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "preset", method = RequestMethod.POST)
    public HueResult preset(@RequestBody String presetName) {
        return huePresetService.startHuePreset(presetName);
    }
}
