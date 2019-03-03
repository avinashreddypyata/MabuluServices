package com.mabulu.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "schema" })
public class PortFactors {
	
	@JsonProperty("data")
	private List<PortFactorsData> portFactorsDatas;

	public List<PortFactorsData> getPortFactorsDatas() {
		return portFactorsDatas;
	}

	public void setPortFactorsDatas(List<PortFactorsData> portFactorsDatas) {
		this.portFactorsDatas = portFactorsDatas;
	}

	

	

}
