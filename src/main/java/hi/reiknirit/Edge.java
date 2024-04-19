package hi.reiknirit;

class Edge {
    private Node departureNode;
    private Node arrivalNode;
    private String time;

    public Edge(Node node1, Node node2, String time) {
        this.departureNode = node1;
        this.arrivalNode = node2;
        this.time = time;
    }

    //Getters
    public Node getDepartureNode() {
        return departureNode;
    }

    public Node getArrivalNode() {
        return arrivalNode;
    }

    public String getTime() {
        return time;
    }

    //Setters
    public void setDepartureNode(Node departureNode) {
        this.departureNode = departureNode;
    }

    public void setArrivalNode(Node arrivalNode) {
        this.arrivalNode = arrivalNode;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private int parseTime(){
        String[] timeStrings = this.time.split(":");
        int timeMinutes = 0;
        timeMinutes +=(Integer.valueOf(timeStrings[0])*60);
        timeMinutes +=(Integer.valueOf((timeStrings[1])));
        return timeMinutes;
    }

    @Override
    public String toString() {
        return arrivalNode.getId();
    }
}
