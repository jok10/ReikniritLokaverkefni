package hi.reiknirit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RouteDataReader {
    public static Map<String, Route> readRoutesFromFile(String filename) throws IOException {
        Map<String, Route> routesMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Route route = Route.parseRoute(line);
                // If statement that filters out routs we don't need
                if (route.getRouteId().substring(0,2).equals("ST")) {
                    routesMap.put(route.getRouteId(), route);
                }
            }
        }
        return routesMap;
    }
    public static List<StopTime> readStopTimesFromFile(String filename) throws IOException {
        List<StopTime> stopTimesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                StopTime stopTime = StopTime.parseStopTime(line);
                if (stopTime.getPickupType() == 0) {
                    stopTimesList.add(stopTime);
                }
            }
        }
        return stopTimesList;
    }

    public static Map<String, Trip> readTripsFromFile(String filename) throws IOException {
        Map<String, Trip> routeTripsMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Trip trip = Trip.parseTrip(line);
                // If statement that filters out trips we don't need
                List<String> nightBus = List.of("101", "102", "103", "104", "105", "106");
                if (trip.getRouteId().substring(0,2).equals("ST")
                        && !trip.getServiceId().contains("S")
                        && !nightBus.contains(trip.getRouteId().substring(3))) {
                    routeTripsMap.put(trip.getTripId(), trip);
                }
            }
        }
        return routeTripsMap;
    }

    public static Map<String, Stop> readStopsFromFile(String filename) throws IOException {
        Map<String, Stop> stopsMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                Stop stop = Stop.parseStop(line);
                stopsMap.put(stop.getStopId(), stop);
            }
        }
        return stopsMap;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Route> routesMap = readRoutesFromFile("routes.txt");
        List<StopTime> stopTimesList = readStopTimesFromFile("stop_times.txt");
        Map<String, Trip> routeTripsMap = readTripsFromFile("trips.txt");
        Map<String, Stop> stopsMap = readStopsFromFile("stops.txt");

        Graph graph = new Graph();

        for (StopTime stopTime : stopTimesList) {
            String tripId = stopTime.getTripId();
            Trip trip = routeTripsMap.get(tripId);
            if (trip != null) {
                String routeId = trip.getRouteId();
                Route route = routesMap.get(routeId);
                if (route != null) {
                    String startStopId = stopTime.getStopId();
                    String departureTime = stopTime.getDepartureTime();

                    // Find the next stop for this trip
                    StopTime nextStopTime = findNextStop(stopTimesList, tripId, stopTime.getStopSequence(), stopTimesList.indexOf(stopTime));
                    if (nextStopTime != null) {
                        String endStopId = nextStopTime.getStopId();
                        String arrivalTime = nextStopTime.getArrivalTime();

                        // Create a new node for this direct connection
                        Node newNode = new Node(startStopId + "_" + endStopId);
                        newNode.setDepartureTime(departureTime);
                        newNode.setArrivalTime(arrivalTime);
                        newNode.setTripId(tripId);
                        newNode.setRouteId(routeId);
                        newNode.setStartStopId(startStopId);
                        newNode.setEndStopId(endStopId);

                        // Add the new node to the graph
                        graph.addNode(newNode);

                        // Connect the start and end stops with the new node
                        graph.addEdge(startStopId, newNode.getId());
                        graph.addEdge(newNode.getId(), endStopId);
                    }
                }
            }
        }
    }

    private static StopTime findNextStop(List<StopTime> stopTimesList, String tripId, int currentStopSequence, int index) {
        StopTime possiblyNextStop = stopTimesList.get(index+1);
        if (possiblyNextStop.getTripId().equals(tripId) && possiblyNextStop.getStopSequence() == currentStopSequence + 1) {
            return possiblyNextStop;
        }
        return null;
    }

}
