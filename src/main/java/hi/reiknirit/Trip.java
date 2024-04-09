package hi.reiknirit;

import java.util.Arrays;

public class Trip {
    private String routeId;
    private String serviceId;
    private String tripId;
    private String tripHeadsign;
    private String tripShortName;
    private int directionId;
    private String blockId;
    private String shapeId;

    public Trip(String routeId, String serviceId, String tripId, String tripHeadsign, String tripShortName, int directionId, String blockId, String shapeId) {
        this.routeId = routeId;
        this.serviceId = serviceId;
        this.tripId = tripId;
        this.tripHeadsign = tripHeadsign;
        this.tripShortName = tripShortName;
        this.directionId = directionId;
        this.blockId = blockId;
        this.shapeId = shapeId;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getTripId() {
        return tripId;
    }

    public String getTripHeadsign() {
        return tripHeadsign;
    }

    public String getTripShortName() {
        return tripShortName;
    }

    public int getDirectionId() {
        return directionId;
    }

    public String getBlockId() {
        return blockId;
    }

    public String getShapeId() {
        return shapeId;
    }

    public static Trip parseTrip(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Empty or null line provided.");
        }
        String[] parts = line.split(",");
        String routeId = parts[0];
        String serviceId = parts[1];
        String tripId = parts[2];
        String tripHeadsign = parts.length > 3 && !parts[3].isEmpty() ? parts[3] : null;
        String tripShortName = parts.length > 4 && !parts[4].isEmpty() ? parts[4] : null;
        int directionId = parts.length > 5 && !parts[5].isEmpty() ? Integer.parseInt(parts[5]) : 0;
        String blockId = parts.length > 6 && !parts[6].isEmpty() ? parts[6] : null;
        String shapeId = parts.length > 7 && !parts[7].isEmpty() ? parts[7] : null;

        return new Trip(routeId, serviceId, tripId, tripHeadsign, tripShortName, directionId, blockId, shapeId);
    }




}
