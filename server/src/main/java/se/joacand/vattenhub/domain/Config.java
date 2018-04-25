package se.joacand.vattenhub.domain;

public class Config {

    public String id;
    public String hueUser;
    public String bridgeUrl;

    public Config() {
    }

    public Config(String id, String hueUser, String bridgeUrl) {
        this.id = id;
        this.hueUser = hueUser;
        this.bridgeUrl = bridgeUrl;
    }

    @Override
    public String toString() {
        return String.format("Config[id='%s', hueUser='%s', bridgeUrl='%s']", id, hueUser, bridgeUrl);
    }
}
