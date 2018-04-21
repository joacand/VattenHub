package se.joacand.vattenhub.domain;

import java.util.List;

public class LightInfo {

	private final List<String> lights;

	public LightInfo(List<String> lights) {
		this.lights = lights;
	}
	
	public List<String> getLights() {
		return lights;
	}
}
