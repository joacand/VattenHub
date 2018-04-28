package se.joacand.vattenhub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.LightAction;
import se.joacand.vattenhub.service.HueActions.ILightActionHandler;

@Service
public class HueActionService implements IHueActionService {

    private final ILightActionHandler lightActionLocator;

    public HueActionService(ILightActionHandler lightActionHandler) {
        this.lightActionLocator = lightActionHandler;
    }

    @Override
    public List<String> getLightActions() {
        return lightActionLocator.getLightActions();
    }

    @Override
    public HueResult startLightAction(LightAction lightActionObj) {
        lightActionLocator.startLightAction(lightActionObj.getActionName());
        return new HueResult(true);
    }

}
