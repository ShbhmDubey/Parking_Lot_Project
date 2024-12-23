package com.rk.parkingLot.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ParkingLot {
	 public List<Floor> getFloors() {
		return floors;
	}
@Value("2")
	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public Map<String, Long> getTokens() {
		return tokens;
	}

	public void setTokens(Map<String, Long> tokens) {
		this.tokens = tokens;
	}

	public CostStrategy getCostStrategy() {
		return costStrategy;
	}

	private List<Floor> floors;
	    private CostStrategy costStrategy;
	    private Map<String, Long> tokens;
      @Autowired
	    public ParkingLot(int numFloors, Map<VehicleType, Integer> capacity) {
	        this.floors = new ArrayList<>();
	        this.tokens = new HashMap<>();
	        for (int i = 1; i <= numFloors; i++) {
	            floors.add(new Floor(i, capacity));
	        }
	    }

	    public void setCostStrategy(CostStrategy costStrategy) {
	        this.costStrategy = costStrategy;
	    }

	    public String addVehicle(Vehicle vehicle, long timestamp) {
	        for (Floor floor : floors) {
	            if (floor.parkVehicle(vehicle)) {
	                String token = UUID.randomUUID().toString();
	                tokens.put(token, timestamp);
	                return token;
	            }
	        }
	        throw new IllegalStateException("Parking lot is full.");
	    }

	    public double removeVehicle(String token, long timestamp) {
	        if (!tokens.containsKey(token)) {
	            throw new IllegalArgumentException("Invalid token.");
	        }
	        long entryTime = tokens.remove(token);
	        long hours = (timestamp - entryTime) / 3600;

	        for (Floor floor : floors) {
	            for (VehicleSpace space : floor.spaces) {
	                if (!space.isAvailable() && space.getParkedVehicle() != null && space.getParkedVehicle().getRegistrationNumber().equals(token)) {
	                    Vehicle vehicle = space.removeVehicle();
	                    return costStrategy.calculateCost(vehicle.getType(), hours);
	                }
	            }
	        }
	        throw new IllegalStateException("Vehicle not found.");
	    }

	    public void checkAvailability(int floorNumber, VehicleType type) {
	        Floor floor = floors.get(floorNumber - 1);
	        System.out.println("Available spaces for " + type + ": " + floor.getAvailableSpaces(type));
	    }

	    public void displayStatus() {
	        for (Floor floor : floors) {
	            floor.displayStatus();
	        }
	    }
}
