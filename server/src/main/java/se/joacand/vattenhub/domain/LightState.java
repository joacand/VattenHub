package se.joacand.vattenhub.domain;

public class LightState {
	private boolean on;
	private int light;

	public LightState() {	}

	public boolean getOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public int getLight() {
		return light;
	}

	public void setLight(int light) {
		this.light = light;
	}
}
