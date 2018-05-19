package se.joacand.vattenhub.service;

import se.joacand.vattenhub.domain.HueResult;
import se.joacand.vattenhub.domain.hue.HuePreset;

import java.util.List;

public interface IHuePresetService {
    List<HuePreset> getHuePresets();

    HueResult startHuePreset(String name);
}
