package hi.reiknirit;
import java.util.HashMap;
import java.util.Map;

public class Stop {
    private String stopId;
    private String stopName;
    private double stopLat;
    private double stopLon;
    private int locationType;

    public Stop(String stopId, String stopName, double stopLat, double stopLon, int locationType) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
        this.locationType = locationType;
    }

    public String getStopId() {
        return stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public double getStopLat() {
        return stopLat;
    }

    public double getStopLon() {
        return stopLon;
    }

    public int getLocationType() {
        return locationType;
    }

    public static Stop parseStop(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Empty or null line provided.");
        }

        String[] parts = line.split(",");
        if (parts.length < 5) {
            throw new IllegalArgumentException("Invalid line: " + line);
        }

        String stopId = parts[0];
        String stopName = parts[1];
        double stopLat = Double.parseDouble(parts[2]);
        double stopLon = Double.parseDouble(parts[3]);
        int locationType = Integer.parseInt(parts[4]);

        return new Stop(stopId, stopName, stopLat, stopLon, locationType);
    }
}
