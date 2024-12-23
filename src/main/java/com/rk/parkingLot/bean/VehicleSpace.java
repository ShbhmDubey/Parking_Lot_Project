package com.rk.parkingLot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleSpace {
	
	    public int getSpaceNumber() {
		return spaceNumber;
	}

	public void setSpaceNumber(int spaceNumber) {
		this.spaceNumber = spaceNumber;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public void setParkedVehicle(Vehicle parkedVehicle) {
		this.parkedVehicle = parkedVehicle;
	}

		private int spaceNumber;
	    private boolean isAvailable;
	    private VehicleType type;
	    private Vehicle parkedVehicle;
      @Autowired
	    public VehicleSpace(int spaceNumber, VehicleType type) {
	        this.spaceNumber = spaceNumber;
	        this.type = type;
	        this.isAvailable = true;
	    }

	    public boolean isAvailable() {
	        return isAvailable;
	    }

	    public void parkVehicle(Vehicle vehicle) {
	        if (!isAvailable) {
	            throw new IllegalStateException("Space is already occupied.");
	        }
	        this.parkedVehicle = vehicle;
	        this.isAvailable = false;
	    }

	    public Vehicle removeVehicle() {
	        if (isAvailable) {
	            throw new IllegalStateException("No vehicle is parked here.");
	        }
	        Vehicle vehicle = this.parkedVehicle;
	        this.parkedVehicle = null;
	        this.isAvailable = true;
	        return vehicle;
	    }

	    public VehicleType getType() {
	        return type;
	    }

	    public Vehicle getParkedVehicle() {
	        return parkedVehicle;
	    }

	    @Override
	    public String toString() {
	        return "Space{Number=" + spaceNumber + ", Type=" + type + ", Available=" + isAvailable + "}";
	    }
}
