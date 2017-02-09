package luukhermans.nl.spyhunt.library;

import android.location.Location;

/**
 * Created by Luuk on 7-2-2017.
 */

public class Player {

    public String getUid() {
        return Uid;
    }

    private String Uid;
    private String name;
    private Double longtitude;
    private Double latitude;
    private String picture;
    private Integer privacyLevel;
    private Integer score;

    public Player() {

    }
    public Player(String Uid, String name, String picture) {
        this.Uid = Uid;
        this.name = name;
        this.picture = picture;
        this. longtitude = 0D;
        this.latitude = 0D;
        privacyLevel = 0;
        score = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation (Location location) {
        this.latitude = location.getLatitude();
        this.longtitude = location.getLongitude();
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public void setUid(String uid) {

        Uid = uid;
    }

    public Double getLatitude() {

        return latitude;
    }

    public Double getLongtitude() {

        return longtitude;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPrivacyLevel(Integer privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPicture() {
        return picture;
    }

    public Integer getPrivacyLevel() {
        return privacyLevel;
    }

    public Integer getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}
