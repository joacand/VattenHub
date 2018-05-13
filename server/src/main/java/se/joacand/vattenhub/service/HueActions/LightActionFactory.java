package se.joacand.vattenhub.service.HueActions;

import org.springframework.stereotype.Component;
import se.joacand.vattenhub.service.IHueService;

import java.util.HashMap;

@Component
public class LightActionFactory implements ILightActionFactory {

    private final IHueService hueService;
    private HashMap<LightActionEnum, ILightAction> startedActions = new HashMap<>();

    private LightActionFactory(IHueService hueService) {
        this.hueService = hueService;
    }

    @Override
    public ILightAction GetAction(LightActionEnum action) {

        if (startedActions.containsKey(action)) {
            return startedActions.get(action);
        }

        ILightAction lightAction;

        switch (action) {
            case DiscoAction:
                lightAction = new DiscoAction(hueService);
                break;
            case TimeAction:
                lightAction = new TimeAction(hueService);
                break;
            case Off:
            default:
                lightAction = new NullAction();
        }

        startedActions.put(action, lightAction);
        return lightAction;
    }

}
