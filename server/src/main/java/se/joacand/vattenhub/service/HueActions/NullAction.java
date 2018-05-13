package se.joacand.vattenhub.service.HueActions;

/**
 * A null object for the light action structure
 */
public class NullAction extends BaseAction implements ILightAction {

    public NullAction() {
        super("NullAction", null);
    }

    @Override
    public void execute() {
    }


}
