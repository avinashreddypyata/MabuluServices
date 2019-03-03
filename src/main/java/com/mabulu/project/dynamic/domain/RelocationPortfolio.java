package com.mabulu.project.dynamic.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The Class RelocationPortfolio.
 */
@JsonIgnoreProperties(value = { "schema" })
public class RelocationPortfolio {

	/** The relocation datas. */
	@JsonProperty("data")
	private List<RelocationData> relocationDatas;

	/**
	 * Gets the relocation datas.
	 *
	 * @return the relocation datas
	 */
	public List<RelocationData> getRelocationDatas() {
		return relocationDatas;
	}

	/**
	 * Sets the relocation datas.
	 *
	 * @param relocationDatas the new relocation datas
	 */
	public void setRelocationDatas(List<RelocationData> relocationDatas) {
		this.relocationDatas = relocationDatas;
	}

}
