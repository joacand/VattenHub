package se.joacand.vattenhub.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.LightAction;
import se.joacand.vattenhub.service.HueActions.ILightActionHandler;
import se.joacand.vattenhub.service.HueActions.LightActionEnum;

import java.util.List;

@Service
public class HueActionService implements IHueActionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
        logger.info("Starting action " + lightActionObj.getActionName());
        lightActionHandler.startLightAction(
                LightActionEnum.valueOf(lightActionObj.getActionName()),
                lightActionObj.getLights());
        return new HueResult(true);
    }

}
