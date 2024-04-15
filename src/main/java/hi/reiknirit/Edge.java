package hi.reiknirit;

class Edge {
    public Node getDepartureStop() {
        return departureStop;
    }

    public void setDepartureStop(Node departureStop) {
        this.departureStop = departureStop;
    }

    public Node getArrivalStop() {
        return arrivalStop;
    }

    public void setArrivalStop(Node arrivalStop) {
        this.arrivalStop = arrivalStop;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    private Node departureStop;
    private Node arrivalStop;
    private String departureTime;
    private String arrivalTime;
    private int weight;

    public Edge(Node stop1, Node stop2, String stopTime1, String stopTime2) {
        this.departureStop = stop1;
        this.arrivalStop = stop2;
        this.departureTime = stopTime1;
        this.arrivalTime = stopTime2;
        generateWeight();
    }

    private int parseDepartureTime(){
        String[] departureTimeParts = this.departureTime.split(":");
        int departureTimeMinutes = 0;
        departureTimeMinutes +=(Integer.valueOf(departureTimeParts[0])*60);
        departureTimeMinutes +=(Integer.valueOf((departureTimeParts[1])));
        return departureTimeMinutes;
    }

    private int parseArrivalTime(){
        String[] arrivalTimeParts = this.arrivalTime.split(":");
        int arrivalTimeMinutes = 0;
        arrivalTimeMinutes +=(Integer.valueOf(arrivalTimeParts[0])*60);
        arrivalTimeMinutes +=(Integer.valueOf((arrivalTimeParts[1])));
        return arrivalTimeMinutes;
    }
        private void generateWeight(){
            this.weight = parseArrivalTime()-parseDepartureTime();
    }
}
