package se.joacand.vattenhub.service;

import se.joacand.vattenhub.domain.LightInfo;
import se.joacand.vattenhub.domain.LightState;

public interface IHueService {
	boolean changeState(LightState lightState);
	LightInfo getLights();
	boolean registerAccount();	
}
