package hi.reiknirit;

import java.util.*;

class Node {
    //If the nodeType is BUSSTOP, the id should be the stop_id
    //Else if the nodeType is TRIP, the id should be the trip_id + stop_sequence
    private String id;
    private String nodeType;
    private List<Edge> edges;

    //If the node is of type TRIP, it needs to contain timeWeight and routeId
    private Integer timeWeight;
    private String routeId;

    //If the node is of type BUSSTOP, it needs to contain the coordinates and stopName
    private String stopName;
    private Double stopLat;
    private Double stopLon;


    public Node(String id, String nodeType) {
        //The node type indicates weather the node is a bus stop or a trip node
        //NodeType can either be BUSSTOP or TRIP
        this.id = id;
        this.nodeType = nodeType;
        this.edges = new ArrayList<>();

    }

    // Getters
    public String getId() {
        return id;
    }

    public String getNodeType() {
        return nodeType;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Integer getTimeWeight() {
        return timeWeight;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getStopName() {
        return stopName;
    }

    public Double getStopLat() {
        return stopLat;
    }

    public Double getStopLon() {
        return stopLon;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void setTimeWeight(Edge departureEdge, Edge arrivalEdge) {
        this.timeWeight = arrivalEdge.parseTime() - departureEdge.parseTime();
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public void setStopLat(Double stopLat) {
        this.stopLat = stopLat;
    }

    public void setStopLon(Double stopLon) {
        this.stopLon = stopLon;
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

        Set<String> edgeIds = new HashSet<>();
        for (Edge edge : edges) {
            edgeIds.add(edge.getArrivalNode().getId());
        }

        for (String edgeId : edgeIds) {
            retString.append(edgeId).append(", ");
        }

        return retString.toString();
    }

}