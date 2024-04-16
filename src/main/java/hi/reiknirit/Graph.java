package hi.reiknirit;

import java.util.HashMap;
import java.util.Map;

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

    public void addNeighbour(String sourceId, String destinationId) {
        if (!nodes.containsKey(sourceId) || !nodes.containsKey(destinationId)) {
            // Handle error: One or both of the nodes do not exist
            return;
        }
        Node sourceNode = nodes.get(sourceId);
        Node destinationNode = nodes.get(destinationId);

        // Add destinationNode as a neighbor of sourceNode
        sourceNode.addEdge(destinationNode);

        // Add sourceNode as a neighbor of destinationNode
        destinationNode.addEdge(sourceNode);
    }



    public void printAllNodes() {
        System.out.println("Nodes in the graph:");
        for (Node node : nodes.values()) {
            System.out.println(node);
        }
    }

    public void printAllNodesAsLine() {
        for (Map.Entry<String,Node> entry : nodes.entrySet()) {
            System.out.println(entry.getValue().printAsLine());
        }
    }

    public void printGraph() {
        System.out.println("Graph:");

        // Iterate over all nodes in the graph
        for (Node node : nodes.values()) {
            System.out.println("Node: " + node.getId());

            // Iterate over the neighbors of the current node
            for (Node neighbor : node.getNeighbors()) {
                System.out.println("  Neighbor: " + neighbor.getId());
            }
        }
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
