package com.mabulu.project.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {
	
	@JsonProperty("symbol")
	public String symbol;
	
	@JsonProperty("name")
	public String Name;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
