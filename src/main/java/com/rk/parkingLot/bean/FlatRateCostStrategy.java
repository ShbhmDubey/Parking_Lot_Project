package com.rk.parkingLot.bean;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class FlatRateCostStrategy  implements CostStrategy {
	
	public Map<VehicleType, Double> getRates() {
		return rates;
	}

	public void setRates(Map<VehicleType, Double> rates) {
		this.rates = rates;
	}

	private Map<VehicleType, Double> rates;
        @Autowired
	    public FlatRateCostStrategy(Map<VehicleType, Double> rates) {
	        this.rates = rates;
	    }

	    @Override
	    public double calculateCost(VehicleType type, long hours) {
	        return rates.getOrDefault(type, 0.0) * hours;
	    }
}
