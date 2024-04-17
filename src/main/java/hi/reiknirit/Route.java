package hi.reiknirit;

public class Route {
    private String routeId;
    private int agencyId;
    private String routeShortName;
    private String routeLongName;
    private int routeType;

    public Route(String routeId, int agencyId, String routeShortName, String routeLongName, int routeType) {
        this.routeId = routeId;
        this.agencyId = agencyId;
        this.routeShortName = routeShortName;
        this.routeLongName = routeLongName;
        this.routeType = routeType;
    }

    // Getters and Setters
    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public int getAgencyId() { return agencyId; }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public void setRouteShortName(String routeShortName) {
        this.routeShortName = routeShortName;
    }

    public String getRouteLongName() {
        return routeLongName;
    }

    public void setRouteLongName(String routeLongName) {
        this.routeLongName = routeLongName;
    }

    public int getRouteType() {
        return routeType;
    }

    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }

    // Parse line and create Route object
    public static Route parseRoute(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid line: " + line);
        }
        String routeId = parts[0];
        int agencyId = Integer.parseInt(parts[1]);
        String routeShortName = parts[2];
        String routeLongName = parts[3];
        int routeType = Integer.parseInt(parts[4]);
        return new Route(routeId, agencyId, routeShortName, routeLongName, routeType);
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId='" + routeId + '\'' +
                ", agencyId=" + agencyId +
                ", routeShortName='" + routeShortName + '\'' +
                ", routeLongName='" + routeLongName + '\'' +
                ", routeType=" + routeType +
                '}';
    }


}
