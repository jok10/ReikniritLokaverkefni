package hi.reiknirit;

import java.util.*;



public class Graph {
    private Map<String, Node> nodes;
    private List<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    //Getters
    public Node getNode(String id) {
        return nodes.get(id);
    }

    public Integer getNodesSize(){
        return nodes.size();
    }

    public Integer getEdgesSize(){
        return edges.size();
    }

    //Setters
    public void addStop(Node busStop) {
        nodes.put(busStop.getId(), busStop);
    }

    public void addTrip(Node tripNode, String departureTime, String arrivalTime, String departureStop, String arrivalStop){
        Node departureBusStop = nodes.get(departureStop);
        Node arrivalBusStop = nodes.get(arrivalStop);
        Edge departureEdge = new Edge(departureBusStop, tripNode, departureTime);
        Edge arrivalEdge = new Edge(tripNode, arrivalBusStop, arrivalTime);
        tripNode.addEdge(arrivalEdge);
        tripNode.setTimeWeight(departureEdge, arrivalEdge);

        edges.add(departureEdge);
        edges.add(arrivalEdge);
        nodes.put(tripNode.getId(), tripNode);

        departureBusStop.addEdge(departureEdge);
    }

    public List<Node> findShortestPath(String startId, String endId) {
        Map<String, Integer> distance = new HashMap<>();
        Map<String, Node> previous = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));


        for (String nodeId : nodes.keySet()) {
            distance.put(nodeId, Integer.MAX_VALUE);
            previous.put(nodeId, null);
        }
        distance.put(startId, 0);


        queue.add(nodes.get(startId));

        // Dijkstra's algorithm
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.getId().equals(endId)) {
                break; // Fann nóðuna
            }

            for (Edge edge : currentNode.getEdges()) {
                Node neighborNode = edge.getArrivalNode();
                int altDistance = distance.get(currentNode.getId()) + neighborNode.getTimeWeight();

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


    /*public void printAllNodesAsLine() {
        Set<String> printedEdges = new HashSet<>();
        for (Map.Entry<String, Node> node : nodes.entrySet()) {
            System.out.println(node.getKey() + ":");
            if (node.getValue().getEdges() != null) {
                for (Edge neighbor : node.getValue().getEdges()) {
                    String edgeKey = node.getKey() + " -> " + neighbor.getArrivalNode().getId() ;
                    if (!printedEdges.contains(edgeKey)) {
                        System.out.println("   " + node.getKey() + " -> " + neighbor.getArrivalNode().getId() + " Time: " + neighbor.getTime());
                        printedEdges.add(edgeKey);
                    }
                }
            }
        }
    }*/

    public void printAllNodesAsLine() {
        Set<String> printedEdges = new HashSet<>();
        for (Map.Entry<String, Node> nodeEntry : nodes.entrySet()) {
            Node currentNode = nodeEntry.getValue();
            System.out.println("Node: " + currentNode.getId());

            if (currentNode.getEdges() != null) {
                for (Edge neighbor : currentNode.getEdges()) {
                    String edgeKey = currentNode.getId() + " -> " + neighbor.getArrivalNode().getId();
                    if (!printedEdges.contains(edgeKey)) {
                        System.out.println("   Edge: " + neighbor.getDepartureNode().getId() +
                                " -> " + neighbor.getArrivalNode().getId() +
                                " Time: " + neighbor.getTime());
                        printedEdges.add(edgeKey);
                    }
                }
            }

            // Print details about trip nodes
            if ("TRIP".equals(currentNode.getNodeType())) {
                System.out.println("   Trip Node Details:");
                System.out.println("     ID: " + currentNode.getId());
                System.out.println("     Route ID: " + currentNode.getRouteId());
                System.out.println("     Time Weight: " + currentNode.getTimeWeight());
                // You can add more details here if needed
            }
        }
    }

    public static String padRight(int n) {
        return " ".repeat(n);
    }

    public static void printASCII(String startStop, String departureTime, List<String> stopsBetween, String endStop, String arrivalTime) {

        int startStopLength = ("Start Stop: " + startStop).length();
        int departureTimeLength = ("Departure Time: " + departureTime).length();
        int endStopLength = ("End Stop: " + endStop).length();
        int arrivalTimeLength = ("Arrival Time: " + arrivalTime).length();

        int maxStringLength = Math.max(Math.max(startStopLength, endStopLength), Math.max(departureTimeLength, arrivalTimeLength));
        for (String stop : stopsBetween) {
            maxStringLength = Math.max(maxStringLength, stop.length() + 1);
        }

        int lineLength = maxStringLength;

        String dashes = "-".repeat(lineLength+2);

        System.out.println(" " + dashes);
        System.out.println("/" + padRight( lineLength+2) + "\\");

        System.out.println("|  Start Stop: " + startStop + padRight(lineLength - startStopLength) + "|");
        System.out.println("|  Departure Time: " + departureTime + padRight( lineLength - departureTimeLength) + "|");
        System.out.println("|"   + padRight( lineLength+2) + "|");

        for (String stop : stopsBetween) {
            System.out.println("|  -" + stop + padRight(lineLength - stop.length()-1) + "|");
        }

        System.out.println("|"   + padRight( lineLength+2) + "|");
        System.out.println("|  End Stop: " + endStop + padRight(lineLength - endStopLength) + "|");
        System.out.println("|  Arrival Time: " + arrivalTime + padRight( lineLength - arrivalTimeLength) + "|");
        System.out.println("|"   + padRight( lineLength+2) + "|");

        System.out.println("\\"+dashes+"/");
    }

    public Node getNodeWithHighestOutdegree() {
        int maxOutdegree = 0;
        Node retNode = null;

        for (Map.Entry<String, Node> nodeEntry : nodes.entrySet()) {
            Node currentNode = nodeEntry.getValue();
            int outdegree = currentNode.getOutdegree();
            // Update maxOutdegree and nodeIdWithMaxOutdegree
            if (outdegree > maxOutdegree) {
                maxOutdegree = outdegree;
                retNode = currentNode;
            }
        }

        return retNode;
    }




}
