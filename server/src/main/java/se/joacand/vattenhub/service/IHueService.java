package se.joacand.vattenhub.service;

import se.joacand.vattenhub.domain.LightInfo;
import se.joacand.vattenhub.domain.LightState;

import java.util.HashMap;

public interface IHueService {
    boolean changeState(LightState lightState);

    LightInfo getLights();

    boolean registerAccount();

    boolean sendRaw(HashMap<String, Object> jsonVals, int[] lights);

}
