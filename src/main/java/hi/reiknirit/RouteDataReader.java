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
                // If statement that filters out trips we don't need
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





}
