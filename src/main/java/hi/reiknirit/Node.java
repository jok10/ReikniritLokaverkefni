package hi.reiknirit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Node {
    private String id;
    private String departureTime;
    private String arrivalTime;
    private String tripId;
    private String routeId;
    private String startStopId;
    private String endStopId;
    private Map<String, Node> neighbors;

    public Node(String id) {
        this.id = id;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getTripId() {
        return tripId;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getStartStopId() {
        return startStopId;
    }

    public String getEndStopId() {
        return endStopId;
    }

    public Map<String, Node> getNeighbors() {
        return neighbors;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public void setStartStopId(String startStopId) {
        this.startStopId = startStopId;
    }

    public void setEndStopId(String endStopId) {
        this.endStopId = endStopId;
    }
}
