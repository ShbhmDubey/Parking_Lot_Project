package com.rk.parkingLot.Test;

i
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rk.parkingLot.bean.FlatRateCostStrategy;
import com.rk.parkingLot.bean.ParkingLot;
import com.rk.parkingLot.bean.Vehicle;
import com.rk.parkingLot.bean.VehicleType;
import com.rk.parkingLot.config.AppConfig;

public class AppTestUsingSpring {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext  container=
				new AnnotationConfigApplicationContext(AppConfig.class);
		Map<VehicleType, Integer> capacity = new HashMap<>();
        capacity.put(VehicleType.BIKE, 10);
        capacity.put(VehicleType.CAR, 20);
        capacity.put(VehicleType.BUS, 5);
ParkingLot parkingLot=container.getBean("parkingLot",ParkingLot.class);
Map<VehicleType, Double> rates = new HashMap<>();
rates.put(VehicleType.BIKE, 10.0);
rates.put(VehicleType.CAR, 20.0);
rates.put(VehicleType.BUS, 30.0);

parkingLot.setCostStrategy(new FlatRateCostStrategy(rates));

String token1 = parkingLot.addVehicle(new Vehicle("ABC123", "Red", VehicleType.CAR), System.currentTimeMillis());
String token2 = parkingLot.addVehicle(new Vehicle("XYZ789", "Blue", VehicleType.CAR), System.currentTimeMillis());

parkingLot.displayStatus();

double cost = parkingLot.removeVehicle(token1, System.currentTimeMillis() + 7200000);
System.out.println("Cost for parking: " + cost);



	}

}
