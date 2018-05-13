package se.joacand.vattenhub.service.HueActions;

public interface ILightActionFactory {
    ILightAction GetAction(LightActionEnum action);
}
