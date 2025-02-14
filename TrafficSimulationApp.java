package demoproject;
/**
 *
 * @author Mokshidul Momin
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

public class TrafficSimulationApp {
    public static void main(String[] args) {
        List<Road> roads = new ArrayList<>();
        Intersection intersection = null;
        List<Vehicle> vehicles = new ArrayList<>();

        try {
            // Read road data from file
            BufferedReader roadReader = new BufferedReader(new FileReader("roads.txt"));
            String roadData;
            while ((roadData = roadReader.readLine()) != null) {
                String[] roadInfo = roadData.split(",");
                String roadName = roadInfo[0];
                double roadLength = Double.parseDouble(roadInfo[1]);
                double maxSpeed = Double.parseDouble(roadInfo[2]);
                Road road = new Road(roadName, roadLength, maxSpeed);
                roads.add(road);
            }
            roadReader.close();

            // Read intersection data from file
            BufferedReader intersectionReader = new BufferedReader(new FileReader("intersections.txt"));
            String intersectionData;
            while ((intersectionData = intersectionReader.readLine()) != null) {
                String[] intersectionInfo = intersectionData.split(",");
                String intersectionName = intersectionInfo[0];
                intersection = new Intersection(intersectionName);
                for (int i = 1; i < intersectionInfo.length; i++) {
                    String roadName = intersectionInfo[i];
                    for (Road road : roads) {
                        if (road.getName().equals(roadName)) {
                            intersection.addRoad(road);
                            break;
                        }
                    }
                }
            }
            intersectionReader.close();

            // Read vehicle data from file
            BufferedReader vehicleReader = new BufferedReader(new FileReader("vehicles.txt"));
            String vehicleData;
            while ((vehicleData = vehicleReader.readLine()) != null) {
                String[] vehicleInfo = vehicleData.split(",");
                String licensePlate = vehicleInfo[0];
                double speed = Double.parseDouble(vehicleInfo[1]);
                Vehicle vehicle = new Vehicle(licensePlate, speed);
                vehicles.add(vehicle);
            }
            vehicleReader.close();

            // Associate vehicles with roads
            for (Vehicle vehicle : vehicles) {
                int roadIndex = vehicles.indexOf(vehicle) % roads.size();
                Road road = roads.get(roadIndex);
                vehicle.setCurrentRoad(road);
                road.addVehicle(vehicle);
            }

            // Print information
            System.out.println("Intersection: " + intersection.getName());
            for (Road road : intersection.getRoads()) {
                System.out.println("Road: " + road.getName() + ", Length: " + road.getLength() + " km");
                System.out.println("Max Speed: " + road.getMaxSpeed() + " km/h");
                System.out.println("Vehicles: " + road.getVehicles().size());
                System.out.println();
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

