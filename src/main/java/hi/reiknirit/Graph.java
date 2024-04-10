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

    public void addEdge(String sourceId, String destinationId) {
        if (!nodes.containsKey(sourceId) || !nodes.containsKey(destinationId)) {
            // Handle error: One or both of the nodes do not exist
            return;
        }
        Node sourceNode = nodes.get(sourceId);
        Node destinationNode = nodes.get(destinationId);
        sourceNode.addEdge(destinationNode);
    }

    // Other graph-related methods...

    // Getters and setters
}
