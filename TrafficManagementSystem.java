package demoproject;
/**
 *
 * @author Mokshidul Momin
 */
import java.util.ArrayList;
import java.util.List;

// Class to represent a road
class Road {
    private String name;
    private double length; // Length of the road in kilometers
    private double maxSpeed; // Maximum speed limit on the road
    private List<Vehicle> vehicles; // List of vehicles on the road

    // Constructor
    public Road(String name, double length, double maxSpeed) {
        this.name = name;
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.vehicles = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getLength() {
        return length;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    // Method to add a vehicle to the road
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // Method to remove a vehicle from the road
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }
}

// Class to represent an intersection
class Intersection {
    private String name;
    private List<Road> roads; // List of roads connected to the intersection

    // Constructor
    public Intersection(String name) {
        this.name = name;
        this.roads = new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public List<Road> getRoads() {
        return roads;
    }

    // Method to add a road to the intersection
    public void addRoad(Road road) {
        roads.add(road);
    }
}

// Class to represent a vehicle
class Vehicle {
    private String licensePlate;
    private double speed; // Current speed of the vehicle
    private Road currentRoad; // Road on which the vehicle is traveling

    // Constructor
    public Vehicle(String licensePlate, double speed) {
        this.licensePlate = licensePlate;
        this.speed = speed;
    }

    // Getters and setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public double getSpeed() {
        return speed;
    }

    public Road getCurrentRoad() {
        return currentRoad;
    }

    public void setCurrentRoad(Road currentRoad) {
        this.currentRoad = currentRoad;
    }
}

public class TrafficManagementSystem {
    public static void main(String[] args) {
        // Test the defined structure
        Road road1 = new Road("Highway 1", 50.0, 100.0);
        Road road2 = new Road("City Avenue", 20.0, 60.0);

        Intersection intersection = new Intersection("Main Intersection");
        intersection.addRoad(road1);
        intersection.addRoad(road2);

        Vehicle car1 = new Vehicle("ABC123", 80.0);
        Vehicle car2 = new Vehicle("XYZ456", 70.0);

        car1.setCurrentRoad(road1);
        car2.setCurrentRoad(road2);

        road1.addVehicle(car1);
        road2.addVehicle(car2);

        System.out.println("Vehicles on " + road1.getName() + ": " + road1.getVehicles().size());
        System.out.println("Vehicles on " + road2.getName() + ": " + road2.getVehicles().size());
    }
}
