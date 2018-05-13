package se.joacand.vattenhub.service.HueActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LightActionHandler implements ILightActionHandler {

    private List<String> lightActions = new ArrayList<String>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ILightActionFactory lightActionFactory;
    private LightActionEnum lastStartedAction = LightActionEnum.Off;

    public LightActionHandler(ILightActionFactory lightActionFactory) {
        this.lightActionFactory = lightActionFactory;

        for (LightActionEnum lightAction : LightActionEnum.values()) {
            lightActions.add(lightAction.toString());
        }
    }

    @Override
    public List<String> getLightActions() {
        return lightActions;
    }

    @Override
    public void startLightAction(LightActionEnum lightActionEnum) {
        cancelLightAction(lastStartedAction);

        ILightAction lightAction = lightActionFactory.GetAction(lightActionEnum);
        lightAction.execute();
        lastStartedAction = lightActionEnum;
    }

    @Override
    public void cancelLightAction(LightActionEnum lightActionEnum) {
        ILightAction lightAction = lightActionFactory.GetAction(lightActionEnum);
        lightAction.cancel();
    }

}
