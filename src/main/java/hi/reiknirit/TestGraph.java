package hi.reiknirit;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestGraph {
    public static void main(String[] args) throws IOException {
        Map<String, Route> routesMap = RouteDataReader.readRoutesFromFile("routes.txt");
        List<StopTime> stopTimesList = RouteDataReader.readStopTimesFromFile("stop_times.txt");
        Map<String, Trip> routeTripsMap = RouteDataReader.readTripsFromFile("trips.txt");
        Map<String, Stop> stopsMap = RouteDataReader.readStopsFromFile("stops.txt");

        Graph graph = createGraph(stopTimesList, routeTripsMap, routesMap);

        //graph.printGraph();

        graph.printAllNodesAsLine();
        //graph.printAllNodes();


        //testShortestPath(graph);
    }

    private static Graph createGraph(List<StopTime> stopTimesList, Map<String, Trip> routeTripsMap, Map<String, Route> routesMap) {
        Graph graph = new Graph();

        for (StopTime stopTime : stopTimesList) {
            String tripId = stopTime.getTripId();
            Trip trip = routeTripsMap.get(tripId);
            String startStopId = stopTime.getStopId();
            if (trip != null) {
                StopTime nextStopTime = findNextStop(stopTimesList, tripId, stopTime.getStopSequence(), stopTimesList.indexOf(stopTime));
                if (nextStopTime != null) {
                    String endStopId = nextStopTime.getStopId();

                    Node startNode = graph.getNode(startStopId);
                    if (startNode == null) {
                        startNode = new Node(startStopId);
                        startNode.setTripId(tripId);
                        startNode.setRouteId(trip.getRouteId());
                        graph.addNode(startNode);
                    }

                    Node endNode = graph.getNode(endStopId);
                    if (endNode == null) {
                        endNode = new Node(endStopId);
                        endNode.setTripId(tripId);
                        endNode.setRouteId(trip.getRouteId());
                        graph.addNode(endNode);
                    }

                    graph.addNeighbour(startNode.getId(), endNode.getId(), stopTime.getDepartureTime(), nextStopTime.getArrivalTime());
                }
            }
        }

        return graph;
    }

    private static StopTime findNextStop(List<StopTime> stopTimesList, String tripId, int currentStopSequence, int index) {
        StopTime possiblyNextStop = stopTimesList.get(index+1);
        if (possiblyNextStop.getTripId().equals(tripId) && possiblyNextStop.getStopSequence() == currentStopSequence + 1) {
            return possiblyNextStop;
        }
        return null;
    }

    private static void testShortestPath(Graph graph) {
        // Test shortest path
        String sourceId = "90000116";
        String destinationId = "90000446";
        List<Node> shortestPath = graph.findShortestPath(sourceId, destinationId);
        if (shortestPath != null) {
            System.out.println("Shortest path from " + sourceId + " to " + destinationId + ": " + shortestPath);
        } else {
            System.out.println("No path found from " + sourceId + " to " + destinationId);
        }
    }
}
