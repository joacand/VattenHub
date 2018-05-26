package se.joacand.vattenhub.service.HueActions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

// TODO: Not optimized for a distributed environment
@Component
public class LightActionHandler implements ILightActionHandler {

    private final List<String> lightActions = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ILightActionFactory lightActionFactory;
    private final Map<Integer, LightActionEnum> startedActions;

    public LightActionHandler(ILightActionFactory lightActionFactory) {
        this.lightActionFactory = lightActionFactory;
        startedActions = Collections.synchronizedMap(new HashMap<>());

        for (LightActionEnum lightAction : LightActionEnum.values()) {
            lightActions.add(lightAction.toString());
        }
    }

    @Override
    public List<String> getLightActions() {
        return lightActions;
    }

    @Override
    public void startLightAction(LightActionEnum lightActionEnum, int[] lights) {
        logger.info("Starting action " + lightActionEnum);
        cancelAllAffectedLights(lights);

        ILightAction lightAction = lightActionFactory.GetAction(lightActionEnum);
        lightAction.execute(lights);

        AddStartedLights(lights, lightActionEnum);
    }

    private void cancelAllAffectedLights(int[] lights) {
        for (int light : lights) {
            if (startedActions.containsKey(light)) {
                cancelLightAction(startedActions.get(light));
            }
        }
    }

    private void AddStartedLights(int[] lights, LightActionEnum lightActionEnum) {
        for (int light : lights) {
            startedActions.put(light, lightActionEnum);
        }
    }

    @Override
    public void cancelLightAction(LightActionEnum lightActionEnum) {
        logger.info("Cancelling action " + lightActionEnum);
        ILightAction lightAction = lightActionFactory.GetAction(lightActionEnum);
        lightAction.cancel();
    }

}
