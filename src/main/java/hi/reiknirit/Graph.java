package hi.reiknirit;

import java.util.*;

public class Graph {
    private Map<String, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }
    public Node getNode(String stopId) {
        return nodes.get(stopId);
    }

    //Megum taka þetta function út
    public void addNeighbour(String sourceId, String destinationId, String departureTime, String arrivalTime) {
        if (!nodes.containsKey(sourceId) || !nodes.containsKey(destinationId)) {
            // Handle error: One or both of the nodes do not exist
            return;
        }
        Node sourceNode = nodes.get(sourceId);
        Node destinationNode = nodes.get(destinationId);
        Edge edge = new Edge(sourceNode, destinationNode, departureTime, arrivalTime);
        sourceNode.addEdge(edge);
    }

    public void addStop(Node busStop) {
        nodes.put(busStop.getId(), busStop);
    }
    public void addTrip(String tripNodeId, String departureTime, String arrivalTime, String departureStop, String arrivalStop){
        //Þurfum að bæta við ef strætó stoppistöðin er ekki til
        Node tripNode = new Node(tripNodeId, "TRIP");
        Node departureBusStop = nodes.get(departureStop);
        Node arrivalBusStop = nodes.get(arrivalStop);
        Edge departureEdge = new Edge(departureBusStop, tripNode, departureTime);
        Edge arrivalEdge = new Edge(tripNode, arrivalBusStop, arrivalTime);
        tripNode.addEdge(departureEdge);
        tripNode.addEdge(arrivalEdge);
        tripNode.setTimeWeight(departureEdge, arrivalEdge);
        nodes.put(tripNodeId, tripNode);

        departureBusStop.addEdge(departureEdge);
        arrivalBusStop.addEdge(arrivalEdge);
    }


    public void printAllNodes() {
        System.out.println("Nodes in the graph:");
        for (Node node : nodes.values()) {
            System.out.println(node);
        }
    }

    public void printAllNodesAsLine() {
        Set<String> printedEdges = new HashSet<>();
        for (Map.Entry<String, Node> node : nodes.entrySet()) {
            System.out.println(node.getKey() + ":");
            if (node.getValue().getEdges() != null) {
                for (Edge neighbor : node.getValue().getEdges()) {
                    String edgeKey = node.getKey() + " -> " + neighbor.getArrivalNode().getId();
                    if (!printedEdges.contains(edgeKey)) {
                        System.out.println("   " + node.getKey() + " -> " + neighbor);
                        printedEdges.add(edgeKey);
                    }
                }
            }
        }
    }

    public void printGraph() {
        System.out.println("Graph:");


        for (Node node : nodes.values()) {
            System.out.println("Node: " + node.getId() + " Route: "+ node.getRouteId());


            for (Edge edge : node.getEdges()) {
                System.out.println("  Edge: " + edge.getDepartureNode().getId() +" Departure Time: " + edge.getDepartureTime() + " -> " + edge.getArrivalNode().getId() + " Arrival Time: " + edge.getArrivalTime());
            }
        }
    }

    public List<Node> findShortestPath(String startId, String endId) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, Node> previous = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        // Initialize distance for all nodes to infinity, except the start node
        for (String nodeId : nodes.keySet()) {
            distance.put(nodeId, Integer.MAX_VALUE);
            previous.put(nodeId, null);
        }
        distance.put(startId, 0);

        // Add start node to the queue
        queue.add(nodes.get(startId));

        // Dijkstra's algorithm
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.getId().equals(endId)) {
                break; // Found the shortest path to the destination
            }

            for (Edge edge : currentNode.getEdges()) {
                Node neighborNode = edge.getArrivalNode();
                int altDistance = distance.get(currentNode.getId()) + edge.getWeight();
                if (altDistance < distance.get(neighborNode.getId())) {
                    distance.put(neighborNode.getId(), altDistance);
                    previous.put(neighborNode.getId(), currentNode);
                    queue.add(neighborNode);
                }
            }
        }

        // Reconstruct the shortest path
        List<Node> shortestPath = new ArrayList<>();
        Node currentNode = nodes.get(endId);
        while (currentNode != null) {
            shortestPath.add(currentNode);
            currentNode = previous.get(currentNode.getId());
        }
        Collections.reverse(shortestPath);

        return shortestPath;
    }

    private StopTime findNextStop(Map<String, StopTime> stopTimesMap, String tripId, int currentStopSequence) {
        for (StopTime stopTime : stopTimesMap.values()) {
            if (stopTime.getTripId().equals(tripId) && stopTime.getStopSequence() == currentStopSequence + 1) {
                return stopTime;
            }
        }
        return null;
    }
}
