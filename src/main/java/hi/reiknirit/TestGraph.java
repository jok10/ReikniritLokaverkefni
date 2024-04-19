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

        Graph graph = createGraph(routeTripsMap, stopTimesList, routesMap, stopsMap);

        //testShortestPath(graph);
        //graph.printGraph();
        //graph.printAllNodesAsLine();
        //graph.printAllNodes();
        //testShortestPath(graph);
    }

    private static Graph createGraph(Map<String, Trip> routeTripsMap, List<StopTime> stopTimesList, Map<String, Route> routesMap, Map<String, Stop> stopsMap) {
        Graph graph = new Graph();
        //Start by adding all bus stops to our empty graph
        for (Stop stop : stopsMap.values()) {
            Node stopNode = new Node(stop.getStopId(), "BUSSTOP");
            stopNode.setStopName(stop.getStopName());
            stopNode.setStopLat(stop.getStopLat());
            stopNode.setStopLon(stop.getStopLon());
            graph.addStop(stopNode);
        }

        //Then we take all the trips and add them to the graph as well
       for (StopTime stopTime : stopTimesList) {
            String tripId = stopTime.getTripId();
            Trip trip = routeTripsMap.get(tripId);
            Stop startStop = stopsMap.get(stopTime.getStopId());
            if (trip != null && startStop != null) {
                StopTime nextStopTime = findNextStop(stopTimesList, tripId, stopTime.getStopSequence(), stopTimesList.indexOf(stopTime));
                if (nextStopTime != null) {
                    Stop endStop = stopsMap.get(nextStopTime.getStopId());
                    Node tripNode = new Node(stopTime.getTripId() + "_" + stopTime.getStopSequence(), "TRIP");
                    tripNode.setRouteId(trip.getRouteId());
                    graph.addTrip(tripNode, stopTime.getDepartureTime(), nextStopTime.getArrivalTime(), startStop.getStopId(), endStop.getStopId());
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

    /*
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
*/

}
