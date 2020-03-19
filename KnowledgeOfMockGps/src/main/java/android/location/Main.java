package android.location;

/**
 * @author 揭光智
 * @date 2019/10/06
 */
public class Main {
    public static void main(String[] args) {
        GpsPosition gpsPosition = FinalLocation.getInstance().getAndRemoveFirstGpsPosition();
        gpsPosition.getLatitude();
        gpsPosition.getLongitude();
        gpsPosition.gethAcc();
        gpsPosition.getAlt();
        gpsPosition.getVel();
        gpsPosition.getBear();
        gpsPosition.getvAcc();
        gpsPosition.getsAcc();
        gpsPosition.getbAcc();
    }
}
