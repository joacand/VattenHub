package se.joacand.vattenhub.service.HueActions;

import java.util.List;

public interface ILightActionHandler {
    List<String> getLightActions();

    void startLightAction(LightActionEnum lightActionEnum);
}
