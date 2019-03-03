package com.mabulu.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mabulu.project.dynamic.domain.PortReturnsCombinedFactors;
import com.mabulu.project.dynamic.domain.RelocationPortfolio;
import com.mabulu.project.helper.JsonReadServiceDynamic;

/**
 * The Class PersonalPortfolioController.
 */
@RestController
@RequestMapping("/v4")
public class PersonalPortfolioController {

	/** The json read service dynamics. */
	@Autowired
	private JsonReadServiceDynamic jsonReadServiceDynamics;

	/** The log. */
	private Logger log = LoggerFactory.getLogger(PersonalPortfolioController.class);

	/**
	 * Gets the port va grid dynamic. "PORTVaRGridCombined99.json"
	 * 
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the port va grid dynamic
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortReturnsVaGridDynamic/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getPortVaGridDynamic(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(
					jsonReadServiceDynamics.getPortVaGrid(moduleName, folderName, "PORTVaRGridCombined99.json"),
					HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error Has Occured in getPortVaGridDynamic", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the scld avg returns dynamic.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the scld avg returns dynamic
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getScldVarCombined/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getScldAvgReturnsDynamic(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(
					jsonReadServiceDynamics.getScldAvg(moduleName, folderName, "ScldVaRWAvgCombined.json"),
					HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error Has Occured in getScldAvgReturnsDynamic", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the port returns combined factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the port returns combined factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortReturnsCombinedDataReturns/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getPortReturnsCombinedFactors(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadServiceDynamics.getPortReturnsCombinedFactors(
					moduleName, folderName, "PortReturnsCombined_FACTORS.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error Has Occured in getPortReturnsCombinedFactors", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	/**
	 * Gets the dynamic port returns va grid combined factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the dynamic port returns va grid combined factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortReturnsVaGridCombinedFactors/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getDynamicPortReturnsVaGridCombinedFactors(
			@PathVariable String folderName, @PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadServiceDynamics.getPortVaGridCombinedFactors(
					moduleName, folderName, "PORTVaRGridCombined99_FACTORS.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error Has Occured in getPortReturnsVaGridCombinedFactors", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the dynamic RR trade off combined factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the dynamic RR trade off combined factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getRRTradeOffCombinedFactors/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getDynamicRRTradeOffCombinedFactors(
			@PathVariable String folderName, @PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadServiceDynamics.getRRTradeOffCombinedFactors(
					moduleName, folderName, "RRTradeOffGridCombined_FACTORS.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error Has Occured in getDynamicRRTradeOffCombinedFactors", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the dynamic scld va grid avg factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the dynamic scld va grid avg factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getScldVaGridAvgFactors/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getDynamicScldVaGridAvgFactors(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadServiceDynamics.getScldVaGridAvgFactors(
					moduleName, folderName, "ScldVaRWAvgGridCombined_FACTORS.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error Has Occured in getPortReturnsVaGridCombinedFactors", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Gets the dynamic scld va grid avg factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the dynamic scld va grid avg factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getRellocatedPortFolios/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RelocationPortfolio> getRellocatedPortFolios(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<RelocationPortfolio>(jsonReadServiceDynamics.getRelocationPortFolio(
					moduleName, folderName, "RRAllocPositions.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error Has Occured in getPortReturnsVaGridCombinedFactors", exception);
			return new ResponseEntity<RelocationPortfolio>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
