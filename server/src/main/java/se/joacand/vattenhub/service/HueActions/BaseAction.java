package se.joacand.vattenhub.service.HueActions;

import se.joacand.vattenhub.service.IHueService;

public abstract class BaseAction implements ILightAction {

    private final String name;
    final IHueService hueService;

    BaseAction(String name, IHueService hueService) {
        this.name = name;
        this.hueService = hueService;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void cancel() {

    }
}
