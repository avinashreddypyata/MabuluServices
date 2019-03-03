package com.mabulu.project.dynamic.domain;

import java.util.List;

public class DomainWithTickerAndValues {
	
	
	private String domainTickerName;
	
	private List<Double> tickerValue;

	

	public String getDomainTickerName() {
		return domainTickerName;
	}

	public void setDomainTickerName(String domainTickerName) {
		this.domainTickerName = domainTickerName;
	}

	public List<Double> getTickerValue() {
		return tickerValue;
	}

	public void setTickerValue(List<Double> tickerValue) {
		this.tickerValue = tickerValue;
	}

	

}
