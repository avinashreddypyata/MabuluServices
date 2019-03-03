package com.mabulu.project.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

	@JsonProperty("data")
	public List<PortFolio> portFolios;

	public List<PortFolio> getPortFolios() {
		return portFolios;
	}

	public void setPortFolios(List<PortFolio> portFolios) {
		this.portFolios = portFolios;
	}
}
