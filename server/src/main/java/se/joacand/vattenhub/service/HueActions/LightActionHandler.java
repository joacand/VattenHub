package se.joacand.vattenhub.service.HueActions;

import java.util.List;

import org.springframework.stereotype.Component;

import se.joacand.vattenhub.service.IHueService;

@Component
public class LightActionHandler implements ILightActionHandler {

    private List<String> lightActions;

    private final IHueService hueService;

    public LightActionHandler(IHueService hueService) {
        this.hueService = hueService;
    }

    @Override
    public List<String> getLightActions() {
        return lightActions;
    }

    @Override
    public void registerLightAction(Class<?> classType) throws Exception {
        String className = classType.getSimpleName();

        if (lightActions.contains(className)) {
            throw new Exception("LightAction with this class is already registered");
        }

        lightActions.add(className);
    }

    @Override
    public void startLightAction(String lightActionName) {
        ILightAction lightAction = new NullAction();

        switch (lightActionName) {
        case "DiscoAction":
            lightAction = new DiscoAction(hueService);
            break;
        case "TimeAction":
            lightAction = new TimeAction(hueService);
            break;
        }

        lightAction.execute();
    }

}
