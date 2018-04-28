package se.joacand.vattenhub.domain.hue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

    private Boolean on;

    private int bri;

    private int hue;

    private int sat;

    private String alert;

    private String effect;

    private int transitiontime;

    private int bri_inc;

    private int sat_inc;

    private int hue_inc;

    private int ct_inc;

    private int xy_inc;

    private Boolean reachable;

    public Boolean getOn() {
        return on;
    }

    public void setOn(Boolean on) {
        this.on = on;
    }

    public int getBri() {
        return bri;
    }

    public void setBri(int bri) {
        this.bri = bri;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Boolean getReachable() {
        return reachable;
    }

    public void setReachable(Boolean reachable) {
        this.reachable = reachable;
    }

    public int getHue() {
        return hue;
    }

    public void setHue(int hue) {
        this.hue = hue;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getTransitiontime() {
        return transitiontime;
    }

    public void setTransitiontime(int transitiontime) {
        this.transitiontime = transitiontime;
    }

    public int getBri_inc() {
        return bri_inc;
    }

    public void setBri_inc(int bri_inc) {
        this.bri_inc = bri_inc;
    }

    public int getSat_inc() {
        return sat_inc;
    }

    public void setSat_inc(int sat_inc) {
        this.sat_inc = sat_inc;
    }

    public int getHue_inc() {
        return hue_inc;
    }

    public void setHue_inc(int hue_inc) {
        this.hue_inc = hue_inc;
    }

    public int getCt_inc() {
        return ct_inc;
    }

    public void setCt_inc(int ct_inc) {
        this.ct_inc = ct_inc;
    }

    public int getXy_inc() {
        return xy_inc;
    }

    public void setXy_inc(int xy_inc) {
        this.xy_inc = xy_inc;
    }
}