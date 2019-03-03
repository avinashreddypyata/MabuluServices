package com.mabulu.project.dynamic.domain;

import java.util.List;

public class PortReturnsCombinedFactors {
	private Double maxValue;
	private Double minValue;
	private List<DomainWithTickerAndValues> domainWithTickerAndValues;
	private List<String> labelValues;

	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public List<DomainWithTickerAndValues> getDomainWithTickerAndValues() {
		return domainWithTickerAndValues;
	}
	public void setDomainWithTickerAndValues(List<DomainWithTickerAndValues> domainWithTickerAndValues) {
		this.domainWithTickerAndValues = domainWithTickerAndValues;
	}
	public List<String> getLabelValues() {
		return labelValues;
	}
	public void setLabelValues(List<String> labelValues) {
		this.labelValues = labelValues;
	}
}
