package se.joacand.vattenhub.service.HueActions;

import se.joacand.vattenhub.service.IHueService;

/**
 * Changes the Hue light colors according to the current time
 */
public class TimeAction extends BaseAction implements ILightAction {

    public TimeAction(IHueService hueService) {
        super("TimeAction", hueService);
    }

    @Override
    public void execute() {
        // TODO: Not yet implemented

    }

}
