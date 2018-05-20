package se.joacand.vattenhub.domain;

public class LightAction {
    private String actionName;

    private int[] lights;

    public LightAction() {
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }


    public int[] getLights() {
        return lights;
    }

    public void setLights(int[] lights) {
        this.lights = lights;
    }
}
