package se.joacand.vattenhub.service;

import se.joacand.vattenhub.domain.LightState;

public interface IHueService {
	boolean changeState(LightState lightState);
	boolean registerAccount();
}
