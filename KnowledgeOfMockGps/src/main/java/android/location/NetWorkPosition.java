package android.location;

public class NetWorkPosition {

    private double latitude;
    private double longitude;

    private double hAcc;
    private double alt;
    private float vel;
    private float bear;

    private float vAcc;
    private float sAcc;
    private float bAcc;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double gethAcc() {
        return hAcc;
    }

    public void sethAcc(double hAcc) {
        this.hAcc = hAcc;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public float getVel() {
        return vel;
    }

    public void setVel(float vel) {
        this.vel = vel;
    }

    public float getBear() {
        return bear;
    }

    public void setBear(float bear) {
        this.bear = bear;
    }

    public float getvAcc() {
        return vAcc;
    }

    public void setvAcc(float vAcc) {
        this.vAcc = vAcc;
    }

    public float getsAcc() {
        return sAcc;
    }

    public void setsAcc(float sAcc) {
        this.sAcc = sAcc;
    }

    public float getbAcc() {
        return bAcc;
    }

    public void setbAcc(float bAcc) {
        this.bAcc = bAcc;
    }

    @Override
    public String toString() {
        return "GpsPosition{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", hAcc=" + hAcc +
                ", alt=" + alt +
                ", vel=" + vel +
                ", bear=" + bear +
                ", vAcc=" + vAcc +
                ", sAcc=" + sAcc +
                ", bAcc=" + bAcc +
                '}';
    }
}
