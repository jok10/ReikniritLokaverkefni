package hi.reiknirit;

public class StopTime {
    private String tripId;
    private String arrivalTime;
    private String departureTime;
    private String stopId;
    private int stopSequence;
    private String stopHeadsign;
    private int pickupType;

    public StopTime(String tripId, String arrivalTime, String departureTime, String stopId, int stopSequence, String stopHeadsign, int pickupType) {
        this.tripId = tripId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.stopSequence = stopSequence;
        this.stopHeadsign = stopHeadsign;
        this.pickupType = pickupType;
    }

    public String getTripId() {
        return tripId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getStopId() {
        return stopId;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public String getStopHeadsign() {
        return stopHeadsign;
    }

    public int getPickupType() {
        return pickupType;
    }

    public static StopTime parseStopTime(String line) {
        String[] parts = line.split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid line: " + line);
        }
        String tripId = parts[0];
        String arrivalTime = parts[1];
        String departureTime = parts[2];
        String stopId = parts[3];
        int stopSequence = Integer.parseInt(parts[4]);
        String stopHeadsign = parts[5];
        int pickupType = Integer.parseInt(parts[6]);
        return new StopTime(tripId, arrivalTime, departureTime, stopId, stopSequence, stopHeadsign, pickupType);
    }

    @Override
    public String toString() {
        return "StopTime{" +
                "tripId='" + tripId + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", departureTime='" + departureTime + '\'' +
                ", stopId='" + stopId + '\'' +
                ", stopSequence=" + stopSequence + '\'' +
                ", stopHeadsign=" + stopHeadsign + '\'' +
                ", pickupType=" + pickupType +
                '}';
    }
}
