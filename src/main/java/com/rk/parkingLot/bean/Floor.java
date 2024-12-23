package com.rk.parkingLot.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Floor {
	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public List<VehicleSpace> getSpaces() {
		return spaces;
	}

	public void setSpaces(List<VehicleSpace> spaces) {
		this.spaces = spaces;
	}

	private int floorNumber;
    List<VehicleSpace> spaces;
@Autowired
    public Floor(int floorNumber, Map<VehicleType, Integer> capacity) {
        this.floorNumber = floorNumber;
        this.spaces = new ArrayList<>();
        int spaceNumber = 1;
        for (Map.Entry<VehicleType, Integer> entry : capacity.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                spaces.add(new VehicleSpace(spaceNumber++, entry.getKey()));
            }
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (VehicleSpace space : spaces) {
            if (space.isAvailable() && space.getType() == vehicle.getType()) {
                space.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }

    public Vehicle removeVehicle(String registrationNumber) {
        for (VehicleSpace space : spaces) {
            if (!space.isAvailable()  &&  space.getParkedVehicle().getRegistrationNumber().equals(registrationNumber)) {
                return space.removeVehicle();
            }
        }
        throw new IllegalArgumentException("Vehicle not found on this floor.");
    }

    public int getAvailableSpaces(VehicleType type) {
        int count = 0;
        for (VehicleSpace space : spaces) {
            if (space.isAvailable() && space.getType() == type) {
                count++;
            }
        }
        return count;
    }

    public void displayStatus() {
        System.out.println("Floor " + floorNumber + " Status:");
        for (VehicleSpace space : spaces) {
            System.out.println(space);
        }
    }
}
