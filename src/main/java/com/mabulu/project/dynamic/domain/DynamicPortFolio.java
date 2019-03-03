package com.mabulu.project.dynamic.domain;

import java.util.List;

public class DynamicPortFolio {
	
	private String date;
	
	private List<TickerDomain> tickerDomains;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<TickerDomain> getTickerDomains() {
		return tickerDomains;
	}

	public void setTickerDomains(List<TickerDomain> tickerDomains) {
		this.tickerDomains = tickerDomains;
	}

}
