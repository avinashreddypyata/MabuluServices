package com.mabulu.project.dynamic.domain;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The Class RelocationData.
 */
public class RelocationData {
	
	/** The port folio name. */
	@JsonProperty("portfolio")
	private String portFolioName;
	
	/** The port folio quantity. */
	@JsonProperty("portfolio_RROptimzedPortfolio")
	private Double portFolioQuantity;

	/**
	 * Gets the port folio name.
	 *
	 * @return the port folio name
	 */
	public String getPortFolioName() {
		return portFolioName;
	}

	/**
	 * Sets the port folio name.
	 *
	 * @param portFolioName the new port folio name
	 */
	public void setPortFolioName(String portFolioName) {
		this.portFolioName = portFolioName;
	}

	/**
	 * Gets the port folio quantity.
	 *
	 * @return the port folio quantity
	 */
	public Double getPortFolioQuantity() {
		return portFolioQuantity;
	}

	/**
	 * Sets the port folio quantity.
	 *
	 * @param portFolioQuantity the new port folio quantity
	 */
	public void setPortFolioQuantity(Double portFolioQuantity) {
		this.portFolioQuantity = portFolioQuantity;
	}

}
