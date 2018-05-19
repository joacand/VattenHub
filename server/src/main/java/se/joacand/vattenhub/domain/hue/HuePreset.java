package se.joacand.vattenhub.domain.hue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HuePreset {
    private String description;

    private String name;

    private HueOption[] hueOptions;

    private int[] lights;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HueOption[] getHueOptions() {
        return hueOptions;
    }

    public void setHueOptions(HueOption[] hueOptions) {
        this.hueOptions = hueOptions;
    }

    public int[] getLights() {
        return lights;
    }

    public void setLights(int[] lights) {
        this.lights = lights;
    }
}