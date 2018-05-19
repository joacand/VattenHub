package se.joacand.vattenhub.domain.hue;

public class HueOption {
    private Object value;

    private String key;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
