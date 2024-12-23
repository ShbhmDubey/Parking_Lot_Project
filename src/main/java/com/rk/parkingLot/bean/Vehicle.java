package com.rk.parkingLot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {
	  public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	private String registrationNumber;
	    private String color;
	    private VehicleType type;
      @Autowired
	    public Vehicle(String registrationNumber, String color, VehicleType type) {
	        this.registrationNumber = registrationNumber;
	        this.color = color;
	        this.type = type;
	    }

	    public String getRegistrationNumber() {
	        return registrationNumber;
	    }

	    public VehicleType getType() {
	        return type;
	    }

	    @Override
	    public String toString() {
	        return "Vehicle{Type=" + type + ", Registration=" + registrationNumber + ", Color=" + color + "}";
	    }
}
