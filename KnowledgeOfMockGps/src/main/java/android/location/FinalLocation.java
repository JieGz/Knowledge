package android.location;

import java.util.ArrayList;
import java.util.List;

public class FinalLocation {

    private volatile static FinalLocation location;

    private FinalLocation() {
    }

    public static FinalLocation getInstance() {
        if (location == null) {
            synchronized (FinalLocation.class) {
                if (location == null) {
                    location = new FinalLocation();
                }
            }
        }
        return location;
    }

    private List<GpsPosition> gpsPositions = new ArrayList<>();
    private List<NetWorkPosition> netWorkPositions = new ArrayList<>();

    public GpsPosition getAndRemoveFirstGpsPosition() {
        synchronized (FinalLocation.class) {
            GpsPosition gpsPosition = null;
            if (gpsPositions.size() > 1) {
                gpsPosition = gpsPositions.get(0);
                gpsPositions.remove(gpsPosition);
            } else if (gpsPositions.size() == 1) {
                gpsPosition = gpsPositions.get(0);
            }
            return gpsPosition;
        }
    }

    public NetWorkPosition getAndRemoveFirstNetWorkPosition() {
        synchronized (FinalLocation.class) {
            NetWorkPosition netWorkPosition = null;
            if (netWorkPositions.size() > 1) {
                netWorkPosition = netWorkPositions.get(0);
                netWorkPositions.remove(netWorkPosition);
            } else if (netWorkPositions.size() == 1) {
                netWorkPosition = netWorkPositions.get(0);
            }
            return netWorkPosition;
        }
    }


    public void addGpsPosition(GpsPosition gpsPosition) {
        synchronized (FinalLocation.class) {
            gpsPositions.add(gpsPosition);
        }
    }

    public void addNetWorkPosition(NetWorkPosition netWorkPosition) {
        synchronized (FinalLocation.class) {
            netWorkPositions.add(netWorkPosition);
        }
    }
}
