package se.joacand.vattenhub.service;

import se.joacand.vattenhub.domain.LightInfo;
import se.joacand.vattenhub.domain.LightState;
import se.joacand.vattenhub.domain.hue.State;

public interface IHueService {
    boolean changeState(LightState lightState);

    LightInfo getLights();

    boolean registerAccount();

    boolean sendRaw(State hueState, int[] lights);

}
