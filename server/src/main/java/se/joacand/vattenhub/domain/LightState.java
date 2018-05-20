package se.joacand.vattenhub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LightState {
    private boolean on;
    private int[] lights;
    private int bri;

    public LightState() {
    }

    public boolean getOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int[] getLights() {
        return lights;
    }

    public void setLights(int[] lights) {
        this.lights = lights;
    }

    public int getBri() {
        return bri;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }
}
