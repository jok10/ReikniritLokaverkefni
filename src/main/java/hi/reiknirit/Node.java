package hi.reiknirit;

import java.util.HashMap;
import java.util.Map;

class Node {


    private String id;
    // Other node attributes
    private Map<String, Node> neighbors;

    public Node(String id) {
        this.id = id;
        this.neighbors = new HashMap<>();
    }

    public void addEdge(Node neighbor) {
        neighbors.put(neighbor.getId(), neighbor);
    }

    // Getters and setters
    public String getId() {
        return id;
    }
}