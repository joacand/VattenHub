package se.joacand.vattenhub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.LightAction;
import se.joacand.vattenhub.service.HueActions.ILightActionHandler;
import se.joacand.vattenhub.service.HueActions.LightActionEnum;

@Service
public class HueActionService implements IHueActionService {

    private final ILightActionHandler lightActionHandler;

    public HueActionService(ILightActionHandler lightActionHandler) {
        this.lightActionHandler = lightActionHandler;
    }

    @Override
    public List<String> getLightActions() {
        return lightActionHandler.getLightActions();
    }

    @Override
    public HueResult startLightAction(LightAction lightActionObj) {
        lightActionHandler.startLightAction(LightActionEnum.valueOf(lightActionObj.getActionName()));
        return new HueResult(true);
    }

}
