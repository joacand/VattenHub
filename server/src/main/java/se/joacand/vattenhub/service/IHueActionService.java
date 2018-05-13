package se.joacand.vattenhub.service;

import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.LightAction;

import java.util.List;

public interface IHueActionService {
    List<String> getLightActions();

    HueResult startLightAction(LightAction lightActionObj);
}
