package se.joacand.vattenhub.service.HueActions;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import se.joacand.vattenhub.service.IHueService;

@Component
public class LightActionHandler implements ILightActionHandler {

    private List<String> lightActions = new ArrayList<String>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final IHueService hueService;

    public LightActionHandler(IHueService hueService) {
        this.hueService = hueService;
        
        for(LightActionEnum lightAction : LightActionEnum.values()) {
            lightActions.add(lightAction.toString());
        }
    }

    @Override
    public List<String> getLightActions() {
        logger.info("Returning {0} actions", lightActions.size());
        return lightActions;
    }

    @Override
    public void startLightAction(LightActionEnum lightActionEnum) {
        ILightAction lightAction = new NullAction();

        switch (lightActionEnum) {
        case DiscoAction:
            lightAction = new DiscoAction(hueService);
            break;
        case TimeAction:
            lightAction = new TimeAction(hueService);
            break;
        }

        lightAction.execute();
    }

}
