package hi.reiknirit;

import java.util.HashMap;
import java.util.Map;

class Node {


    private String id;
    // Other node attributes
    private String departureTime;
    private String arrivalTime;
    private String tripId;
    private String routeId;
    private String startStopId;
    private String endStopId;
    private Map<String, Node> neighbors;

    public Node(String id) {
        this.id = id;
        this.neighbors = new HashMap<>();
    }

    public void addEdge(Node neighbor) {
        neighbors.put(neighbor.getId(), neighbor);
    }

    // Getters and setters
    public String getId() {
        return id;
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