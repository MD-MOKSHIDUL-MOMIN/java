
package file;

/**
 *
 * @author Mokshidul Momin
 */
import java.util.*;

class Road {
    private final String name;
    private final int length;

    public Road(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }
}

class Intersection {
    private final String name;
    private final List<Road> connectedRoads;

    public Intersection(String name) {
        this.name = name;
        this.connectedRoads = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addConnectedRoad(Road road) {
        connectedRoads.add(road);
    }

    public List<Road> getConnectedRoads() {
        return connectedRoads;
    }
}

class RouteFinder {
    private final Map<String, Intersection> intersections;

    public RouteFinder(Map<String, Intersection> intersections) {
        this.intersections = intersections;
    }

    public List<List<Road>> findAlternativeRoutes(String currentLocation, String destination) {
        Set<String> visited = new HashSet<>();
        List<List<Road>> alternativeRoutes = new ArrayList<>();
        findRoutesDFS(currentLocation, destination, new ArrayList<>(), alternativeRoutes, visited);
        return alternativeRoutes;
    }

    private void findRoutesDFS(String currentLocation, String destination, List<Road> currentRoute,
                                List<List<Road>> alternativeRoutes, Set<String> visited) {
        visited.add(currentLocation);
        if (currentLocation.equals(destination)) {
            alternativeRoutes.add(new ArrayList<>(currentRoute));
            visited.remove(currentLocation);
            return;
        }
        Intersection intersection = intersections.get(currentLocation);
        if (intersection == null) {
            visited.remove(currentLocation);
            return;
        }
        for (Road road : intersection.getConnectedRoads()) {
            if (!visited.contains(road.getName())) {
                currentRoute.add(road);
                findRoutesDFS(road.getName(), destination, currentRoute, alternativeRoutes, visited);
                currentRoute.remove(road);
            }
        }
        visited.remove(currentLocation);
    }
}

public class RoadNetworkSystem {
    private final Map<String, Road> roads;
    private final Map<String, Intersection> intersections;

    public RoadNetworkSystem() {
        this.roads = new HashMap<>();
        this.intersections = new HashMap<>();
    }

    public void addRoad(String name, int length) {
        roads.put(name, new Road(name, length));
    }

    public void addIntersection(String name) {
        intersections.put(name, new Intersection(name));
    }

    public void connectRoads(String intersectionName, String roadName) {
        Intersection intersection = intersections.get(intersectionName);
        Road road = roads.get(roadName);
        if (intersection != null && road != null) {
            intersection.addConnectedRoad(road);
            // Assuming bi-directional connection
            Intersection reverseIntersection = intersections.get(roadName);
            if (reverseIntersection != null) {
                reverseIntersection.addConnectedRoad(roads.get(intersectionName));
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            RoadNetworkSystem roadNetworkSystem = new RoadNetworkSystem();
            
            // Add roads and intersections
            roadNetworkSystem.addRoad("DIU Road", 100);
            roadNetworkSystem.addRoad("Satarkul Road", 3600);
            roadNetworkSystem.addRoad("Beraid Road", 350);
            roadNetworkSystem.addRoad("UIU Road ", 650);
            roadNetworkSystem.addRoad("Madani avenue Road", 2600);
            roadNetworkSystem.addRoad("Bir Uttam Rafiqul Islam ave Road", 2500);
            roadNetworkSystem.addIntersection("Diu Circle");
            roadNetworkSystem.addIntersection("UIU South");
            roadNetworkSystem.addIntersection("UIU North");
            roadNetworkSystem.addIntersection("Nuton bus stand");
            roadNetworkSystem.addIntersection("Uttor Badda bus stand");
            
            // Connect roads to intersections
            roadNetworkSystem.connectRoads("Diu Circle", "DIU Road");
            roadNetworkSystem.connectRoads("Diu Circle", "Satarkul Road");
            roadNetworkSystem.connectRoads("Diu Circle", "Beraid Road");
            roadNetworkSystem.connectRoads("UIU South", "Beraid Road");
            roadNetworkSystem.connectRoads("UIU South", "UIU Road");
            roadNetworkSystem.connectRoads("UIU North", "UIU Road");
            roadNetworkSystem.connectRoads("UIU North", "Madani avenue Road");
            roadNetworkSystem.connectRoads("Nuton bus stand", "Madani avenue Road");
            roadNetworkSystem.connectRoads("Nuton bus stand", "Bir Uttam Rafiqul Islam ave Road");
            roadNetworkSystem.connectRoads("Badda bus stand", "Bir Uttam Rafiqul Islam ave Road");
            roadNetworkSystem.connectRoads("Badda bus stand", "Satarkul Road");
            
            // Get user input for location and destination
            System.out.print("Enter your current location: ");
            String currentLocation = scanner.nextLine();
            System.out.print("Enter your destination: ");
            String destination = scanner.nextLine();
            
            // Find alternative routes between current location and destination
            RouteFinder routeFinder = new RouteFinder(roadNetworkSystem.intersections);
            List<List<Road>> alternativeRoutes = routeFinder.findAlternativeRoutes(currentLocation, destination);
            if (alternativeRoutes.isEmpty()) {
                System.out.println("No alternative routes found between " + currentLocation + " and " + destination + ".");
            } else {
                System.out.println("Alternative routes between " + currentLocation + " and " + destination + ":");
                for (List<Road> route : alternativeRoutes) {
                    System.out.println("Route:");
                    for (Road road : route) {
                        System.out.println("- " + road.getName());
                    }
                    System.out.println();
                }
            }
        }
    }
}
