package se.joacand.vattenhub.domain;

public class HueResult {

    private final boolean successful;

    private String error = "";

    public HueResult(boolean successful) {
        this.successful = successful;
    }

    public HueResult(boolean successful, String error) {
        this.successful = successful;
        this.error = error;
    }

    public boolean getSuccessful() {
        return successful;
    }

    public String getError() {
        return error;
    }

}
