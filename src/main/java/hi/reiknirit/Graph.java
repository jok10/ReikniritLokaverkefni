package hi.reiknirit;

import java.util.*;
import java.util.stream.IntStream;
import java.lang.Math;


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

    public Integer getNumberOfNodes(){
        return nodes.size();
    }

    public Integer getNumberOfEdges(){
        return edges.size();
    }

    public Integer getNumberOfBusStops(){
        Integer busStopCounter = 0;
        for (Node node : nodes.values()){
            if (node.getNodeType() == "BUSSTOP"){
                busStopCounter += 1;
            }
        }
        return busStopCounter;
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
        List<Node> busStops = new ArrayList<Node>();
        for (Node node : nodes.values()) {
            if (node.getNodeType() == "BUSSTOP") {
                busStops.add(node);
            }
        }

        int rows = this.getNumberOfBusStops();
        int columns = this.getNumberOfBusStops();
        int[][] adjacencyMatrix = new int[rows][columns];

        for (Node node : busStops) {
            Map<Node, Integer> neighbors = new HashMap<Node, Integer>();
            for (Edge edge : node.getEdges()) {
                neighbors.put(edge.getArrivalNode(), edge.getArrivalNode().getTimeWeight());
            }
            for (Map.Entry<Node, Integer> entry : neighbors.entrySet()) {
                Node buddy = entry.getKey();
                Integer weight = entry.getValue();
                adjacencyMatrix[busStops.indexOf(node)][busStops.indexOf(buddy)] = weight;
            }
        }

        int nVertices = adjacencyMatrix[0].length;

        int[] shortestDistances = new int[nVertices];
        List<Node> shortestPath = new ArrayList<Node>();
        /*

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices;
             vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[Integer.parseInt(startId)] = 0;

        // Parent array to store shortest
        // path tree
        List<Node> parents = new ArrayList<Node>();

        // The starting vertex does not
        // have a parent
        parents[Integer.parseInt(startId)] = -1;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++)
        {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                if (!added[vertexIndex] &&
                        shortestDistances[vertexIndex] <
                                shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;

            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0;
                 vertexIndex < nVertices;
                 vertexIndex++)
            {
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }
*/
        return shortestPath;

        /*
        //Don't use this code!
        List<Node> shortestPath = new ArrayList<Node>();

        int rows = this.getNumberOfBusStops() + 1;
        int columns = this.getNumberOfBusStops();
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 1; j < columns; j++){
                matrix[i][j] = -1;
            }
        }

        matrix[0][0] = Integer.parseInt(startId);

        int endStop = Integer.parseInt(endId);

        for (int iterations = 1; iterations < rows; iterations++) {
            int index = iterations - 1;
            String nodeId = String.valueOf(matrix[0][index]);
            Node node = this.getNode(nodeId);
            for (Edge edge : node.getEdges()) {
                int weight = edge.getArrivalNode().getTimeWeight();
                Node destination = edge.getArrivalNode().getEdges().get(0).getArrivalNode();

                if (IntStream.of(matrix[0]).anyMatch(x -> x == Integer.parseInt(destination.getId()))){
                    int tempIndex = Arrays.binarySearch(matrix[0], Integer.parseInt(destination.getId()));
                    matrix[iterations][tempIndex] = Math.min(matrix[iterations][index] + weight, matrix[iterations][tempIndex]);
                } else {
                    int killer = 0;
                    while (matrix[0][killer] > 0) {
                        killer++;
                    }
                    matrix[0][killer] = Integer.parseInt(destination.getId());
                    matrix[iterations][killer] = matrix[iterations][index] + weight;
                }
            }
        }
*/


        /*
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
        */
    }


    public void printAllNodesAsLine() {
        Set<String> printedEdges = new HashSet<>();
        for (Map.Entry<String, Node> node : nodes.entrySet()) {
            System.out.println(node.getKey() + ":");
            if (node.getValue().getEdges() != null) {
                for (Edge neighbor : node.getValue().getEdges()) {
                    String edgeKey = node.getKey() + " -> " + neighbor.getArrivalNode().getId() ;
                    if (!printedEdges.contains(edgeKey)) {
                        System.out.println("   " + node.getKey() + " -> " + neighbor.getArrivalNode().getId());
                        printedEdges.add(edgeKey);
                    }
                }
            }
        }
    }

    /*public void printAllNodesAsLine() {
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
    }*/

    public static String padRight(int n) {
        return " ".repeat(n);
    }

    public void printASCII(String startStop, String departureTime, List<String> stopsBetween, String endStop, String arrivalTime) {
        String startStopName = nodes.containsKey(startStop) ? nodes.get(startStop).getStopName() : "Unknown";
        String endStopName = nodes.containsKey(endStop) ? nodes.get(endStop).getStopName() : "Unknown";
        List<String> stopNamesBetween = new ArrayList<>();
        for (String stopId : stopsBetween) {
            if (nodes.containsKey(stopId)) {
                stopNamesBetween.add(nodes.get(stopId).getStopName());
            } else {
                stopNamesBetween.add("Unknown");
            }
        }

        int startStopLength = ("Start Stop: " + startStopName).length();
        int departureTimeLength = ("Departure Time: " + departureTime).length();
        int endStopLength = ("End Stop: " + endStopName).length();
        int arrivalTimeLength = ("Arrival Time: " + arrivalTime).length();

        int maxStringLength = Math.max(Math.max(startStopLength, endStopLength), Math.max(departureTimeLength, arrivalTimeLength));
        for (String stop : stopsBetween) {
            maxStringLength = Math.max(maxStringLength, stop.length() + 1);
        }

        int lineLength = maxStringLength;

        String dashes = "-".repeat(lineLength+2);

        System.out.println(" " + dashes);
        System.out.println("/" + padRight( lineLength+2) + "\\");

        System.out.println("|  Start Stop: " + startStopName + padRight(lineLength - startStopLength) + "|");
        System.out.println("|  Departure Time: " + departureTime + padRight( lineLength - departureTimeLength) + "|");
        System.out.println("|"   + padRight( lineLength+2) + "|");

        for (String stop : stopNamesBetween) {
            System.out.println("|  -" + stop + padRight(lineLength - stop.length()-1) + "|");
        }

        System.out.println("|"   + padRight( lineLength+2) + "|");
        System.out.println("|  End Stop: " + endStopName + padRight(lineLength - endStopLength) + "|");
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
