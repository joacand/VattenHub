package se.joacand.vattenhub.resource;

import org.springframework.web.bind.annotation.*;
import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.LightAction;
import se.joacand.vattenhub.domain.LightInfo;
import se.joacand.vattenhub.domain.LightState;
import se.joacand.vattenhub.service.IHueActionService;
import se.joacand.vattenhub.service.IHueService;

import java.util.List;

@RestController
@RequestMapping("hue")
public class HueController {

    private IHueService hueService;
    private final String apiver = "/api/v1/";
    private final IHueActionService hueActionService;

    public HueController(IHueService hueService, IHueActionService hueActionService) {
        this.hueService = hueService;
        this.hueActionService = hueActionService;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "lights", method = RequestMethod.POST)
    public HueResult lights(@RequestBody LightState input) {
        boolean res = hueService.changeState(input);
        return new HueResult(res);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "lights", method = RequestMethod.GET)
    public LightInfo lights() {
        return hueService.getLights();
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
        HueResult res = hueActionService.startLightAction(input);
        return res;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = apiver + "action", method = RequestMethod.GET)
    @ResponseBody
    public List<String> action() {
        List<String> actions = hueActionService.getLightActions();
        return actions;
    }
}
