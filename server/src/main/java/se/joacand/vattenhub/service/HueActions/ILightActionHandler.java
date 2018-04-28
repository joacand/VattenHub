package se.joacand.vattenhub.service.HueActions;

import java.util.List;

public interface ILightActionHandler {
    List<String> getLightActions();

    void registerLightAction(Class<?> classType) throws Exception;

    void startLightAction(String lightAction);
}
