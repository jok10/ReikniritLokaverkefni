package hi.reiknirit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    private String id;
    private String departureTime;
    private String arrivalTime;
    private String tripId;
    private String routeId;
    private String stopId;
    private String endStopId;
    private List<Edge> edges;



    private List<String> listOfNeighborIDs;

    public Node(String id) {
        this.id = id;
        this.edges = new ArrayList<>();
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

    public String getStopId() {
        return stopId;
    }

    public String getEndStopId() {
        return endStopId;
    }

    public List<Edge> getEdges() {
        return edges;
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

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public void setEndStopId(String endStopId) {
        this.endStopId = endStopId;
    }

    public List<String> getListOfNeighborIDs() {
        return listOfNeighborIDs;
    }

    public void setListOfNeighborIDs(List<String> listOfNeighborIDs) {
        this.listOfNeighborIDs = listOfNeighborIDs;
    }

    // Other methods
    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public int getOutdegree() {
        return edges.size();
    }

    public String printAsLine() {
        StringBuilder retString = new StringBuilder();
        retString.append("Node id: ").append(this.id).append(", edges for node: ");
        for (Edge edge : edges) {
            retString.append(edge.getArrivalStop().getId()).append(", ");
        }
        return retString.toString();
    }



    @Override
    public String toString() {
        return "Node{" +
                "stopid='" + id + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", tripId='" + tripId + '\'' +
                ", routeId='" + routeId + '\'' +
                ", number of neighbors='" + getOutdegree() + '\'' +
                '}';
    }


}