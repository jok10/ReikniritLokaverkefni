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

        Graph graph = createGraph(stopTimesList, routeTripsMap, stopsMap, routesMap);

        //graph.printGraph();

        graph.printAllNodesAsLine();
        //graph.printAllNodes();


        //testShortestPath(graph);
    }

    private static Graph createGraph(List<StopTime> stopTimesList, Map<String, Trip> routeTripsMap,Map<String, Stop> stopsMap, Map<String, Route> routesMap) {
        Graph graph = new Graph();
        //fara í gegnum allar stops og setja í grafið
        for (Stop stop : stopsMap.values()) {
            Node stopNode = new Node(stop.getStopId(), "STOP");
            graph.addNode(stopNode);
        }

       for (StopTime stopTime : stopTimesList) {
            String tripId = stopTime.getTripId();
            Trip trip = routeTripsMap.get(tripId);
            Stop startStop = stopsMap.get(stopTime.getStopId());
            if (trip != null && startStop != null) {
                StopTime nextStopTime = findNextStop(stopTimesList, tripId, stopTime.getStopSequence(), stopTimesList.indexOf(stopTime));
                if (nextStopTime != null) {
                    Stop endStop = stopsMap.get(nextStopTime.getStopId());

                    // Búa til trip nóðu á milli stoppana
                    graph.addTrip(tripId, stopTime.getDepartureTime(), nextStopTime.getArrivalTime(), startStop.getStopId(), endStop.getStopId());
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

    /*private static void testShortestPath(Graph graph) {
        // Test shortest path
        String sourceId = "90000116";
        String destinationId = "90000446";
        List<Node> shortestPath = graph.findShortestPath(sourceId, destinationId);
        if (shortestPath != null) {
            System.out.println("Shortest path from " + sourceId + " to " + destinationId + ": " + shortestPath);
        } else {
            System.out.println("No path found from " + sourceId + " to " + destinationId);
        }
    }*/


}
