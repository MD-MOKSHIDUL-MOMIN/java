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
import java.util.Random;

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

    // Method to simulate vehicle movement
    public void move() {
        if (currentRoad != null) {
            double maxSpeed = currentRoad.getMaxSpeed();
            speed = Math.min(speed, maxSpeed); // Ensure vehicle speed does not exceed road speed limit
            double distance = speed / 3600; // Convert speed from km/h to km/s
            double remainingDistance = currentRoad.getLength();
            while (remainingDistance > 0) {
                if (distance >= remainingDistance) {
                    distance = remainingDistance;
                }
                remainingDistance -= distance;
                System.out.println("Vehicle " + licensePlate + " travels " + distance + " km on road " + currentRoad.getName());
                try {
                    Thread.sleep(1000); // Simulate time passing
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TrafficSimulation {
    private static List<Road> roads = new ArrayList<>();

    public static void main(String[] args) {
        loadRoadData();

        List<Vehicle> vehicles = generateVehicles(10); // Generate 10 vehicles

        // Simulate traffic
        simulateTraffic(vehicles);
    }

    // Method to load road data from file
    private static void loadRoadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("roads.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double length = Double.parseDouble(parts[1]);
                double maxSpeed = Double.parseDouble(parts[2]);
                roads.add(new Road(name, length, maxSpeed));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to generate vehicles
    private static List<Vehicle> generateVehicles(int numVehicles) {
        List<Vehicle> vehicles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numVehicles; i++) {
            String licensePlate = "ABC" + (1000 + i); // Example license plate format
            double speed = random.nextDouble() * 100; // Random speed between 0 and 100 km/h
            Vehicle vehicle = new Vehicle(licensePlate, speed);
            vehicles.add(vehicle);
        }
        return vehicles;
    }

    // Method to simulate traffic movement
    private static void simulateTraffic(List<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            Thread thread = new Thread(() -> {
                Random random = new Random();
                while (true) {
                    try {
                        Thread.sleep(random.nextInt(5000) + 1000); // Random delay between 1 to 5 seconds
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Road road = getRandomRoad(); // Get a random road
                    vehicle.setCurrentRoad(road);
                    vehicle.move(); // Simulate vehicle movement on the road
                }
            });
            thread.start();
        }
    }

    // Method to get a random road from the list of roads
    private static Road getRandomRoad() {
        Random random = new Random();
        return roads.get(random.nextInt(roads.size()));
    }
}
