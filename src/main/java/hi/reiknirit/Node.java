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
    private Map<String, Node> neighbors;



    private List<String> listOfNeighborIDs;

    public Node(String id) {
        this.id = id;
        this.neighbors = new HashMap<>();
        this.listOfNeighborIDs = new ArrayList<>();
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

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public void setEndStopId(String endStopId) {
        this.endStopId = endStopId;
    }

    public void setNeighbors(Map<String, Node> neighbors) {
        this.neighbors = neighbors;
    }
    public List<String> getListOfNeighborIDs() {
        return listOfNeighborIDs;
    }

    public void setListOfNeighborIDs(List<String> listOfNeighborIDs) {
        this.listOfNeighborIDs = listOfNeighborIDs;
    }

    // Other methods
    public void addEdge(Node neighbor) {
        neighbors.put(neighbor.getId(), neighbor);
        listOfNeighborIDs.add(neighbor.getId());
    }

    public int getOutdegree() {
        int degree = neighbors.size();
        for (Node neighbor : neighbors.values()) {
            degree += neighbor.getOutdegree();
        }
        return degree;
    }

    public String printAsLine() {
        StringBuilder retString = new StringBuilder();
        retString.append("Node id: ").append(this.id).append(", edges for node: ");
        printNeighbors(this, retString);
        return retString.toString();
    }

    private void printNeighbors(Node node, StringBuilder retString) {
        Map<String, Node> neighborsMap = node.getNeighbors();
        if (neighborsMap.isEmpty()) {
            return;
        }
        for (Node neighbor : neighborsMap.values()) {
            retString.append(neighbor.getId()).append(", ");
            printNeighbors(neighbor, retString);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "stopid='" + id + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", tripId='" + tripId + '\'' +
                ", routeId='" + routeId + '\'' +
               // ", neighboursMap='" + neighbors + '\'' +
                ", number of neighbors='" + getOutdegree() + '\'' +
                '}';
    }


}
