package com.rk.parkingLot.bean;




public interface CostStrategy {
	  double calculateCost(VehicleType type, long hours);
}
