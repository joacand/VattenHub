package se.joacand.vattenhub.service;

import java.util.List;

import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.LightAction;

public interface IHueActionService {
    List<String> getLightActions();

    HueResult startLightAction(LightAction lightActionObj);
}
