package com.mabulu.project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortFactorsData {

	@JsonProperty("FACTORS")
	public String factors;
	
	@JsonProperty("FACTORSEffect")
	public Double factorsEffect;

	public String getFactors() {
		return factors;
	}

	public void setFactors(String factors) {
		this.factors = factors;
	}

	public Double getFactorsEffect() {
		return factorsEffect;
	}

	public void setFactorsEffect(Double factorsEffect) {
		this.factorsEffect = factorsEffect;
	}

	

	
}
