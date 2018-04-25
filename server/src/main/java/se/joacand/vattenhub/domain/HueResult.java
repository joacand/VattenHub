package se.joacand.vattenhub.domain;

public class HueResult {

    private final boolean successful;

    public HueResult(boolean successful) {
        this.successful = successful;
    }

    public boolean getSuccessful() {
        return successful;
    }

}
