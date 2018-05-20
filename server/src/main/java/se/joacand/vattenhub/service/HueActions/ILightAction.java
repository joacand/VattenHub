package se.joacand.vattenhub.service.HueActions;

/**
 * This is a base class for specific implemented light actions
 * <p>
 * Derive from this class when creating new actions
 */
public interface ILightAction {
    String getName();

    void execute(int[] lights);

    void cancel();
}
