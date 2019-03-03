package com.mabulu.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mabulu.project.dao.MabuluDao;
import com.mabulu.project.domain.Data;
import com.mabulu.project.domain.PortFolio;
import com.mabulu.project.domain.PortFolioNameJson;
import com.mabulu.project.domain.ReturnMessage;
import com.mabulu.project.domain.SinglePortFolio;
import com.mabulu.project.domain.PortFactors;
import com.mabulu.project.domain.StockPrice;
import com.mabulu.project.dynamic.domain.PortReturnsCombinedFactors;
import com.mabulu.project.helper.JsonReadService;

/**
 * The Class SpringBootAngularRestController.
 */
/**
 * @author macpro
 *
 */
@RestController
@RequestMapping("/v4")
public class StaticMutualFundController {

	/** The mabulu dao. */
	@Autowired
	private MabuluDao mabuluDao;

	/** The json read service. */
	@Autowired
	private JsonReadService jsonReadService;

	/** The log. */
	private Logger log = LoggerFactory.getLogger(StaticMutualFundController.class);

	/**
	 * Gets the spy returns graph historical.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the spy returns graph historical
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortReturnsCombined/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getPortReturnsCombined(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(

					jsonReadService.getSpyReturnsGraphHistorical(moduleName, folderName, "PortReturnsCombined.json"),
					HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getSpyReturnsGraphHistorical", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the spy factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the spy factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortFactors/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortFactors> getPortFactors(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortFactors>(
					jsonReadService.getPortFactorsData(moduleName, folderName, "PORTFactors.json"), HttpStatus.OK);

		} catch (Exception exception) {
			log.error("Error in PortF", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the spy trade off factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the spy trade off factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getRiskReturnTradeOffGridCombined/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getRiskReturnTradeOffGridCombined(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadService.getRiskReturnTradeOffGridCombined(
					moduleName, folderName, "RRTradeOffGridCombined.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getSpyTradeOffFactors", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the scld avg data.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the scld avg data
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getScldAvgData/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getScldAvgData(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(
					jsonReadService.getScldAvgData(moduleName, folderName, "ScldVaRWAvg.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getScldAvgData", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the scld avg combined data.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the scld avg combined data
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getScldAvgCombinedData/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getScldAvgCombinedData(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(
					jsonReadService.getScldAvgCombinedData(moduleName, folderName, "ScldVaRWAvgCombined.json"),
					HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getScldAvgCombinedData", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the fund names.
	 *
	 * @return the fund names
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getfundNames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getFundNames() {
		try {
			return new ResponseEntity<List<String>>(mabuluDao.getFundNames(), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getFundNames", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the fund family names.
	 *
	 * @return the fund family names
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getFundFamilyNames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getFundFamilyNames() {
		try {
			return new ResponseEntity<List<String>>(mabuluDao.getFundFamilyNames(), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getFundFamilyNames", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the fund sector names.
	 *
	 * @return the fund sector names
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getFundSectorNames", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getFundSectorNames() {
		try {
			return new ResponseEntity<List<String>>(mabuluDao.getFundSectors(), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getFundSectorNames", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the fund family names by sector names.
	 *
	 * @param sectorName the sector name
	 * @return the fund family names by sector names
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getFundFamilyNamesBySector/{sectorName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getFundFamilyNamesBySectorNames(@PathVariable String sectorName) {

		try {
			return new ResponseEntity<List<String>>(mabuluDao.getFundFamilyNamesByFundSector(sectorName),
					HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getFundFamilyNamesBySector", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the fund family code by name.
	 *
	 * @param sectorName the sector name
	 * @return the fund family code by name
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getFundFamilyCodeByName/{sectorName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getFundFamilyCodeByName(@PathVariable String sectorName) {
		try {
			return new ResponseEntity<List<String>>(mabuluDao.getFundFundCodeByFundSector(sectorName), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getFundFamilyCodeByName", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the fund code by fund family.
	 *
	 * @param fundFamily the fund family
	 * @return the fund code by fund family
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getFundCodeByFundFamily/{fundFamily}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getFundCodeByFundFamily(@PathVariable String fundFamily) {

		try {
			return new ResponseEntity<List<String>>(mabuluDao.getFundCodeByFundFamilyName(fundFamily), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getFundCodeByFundFamily", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the favourites by user id.
	 *
	 * @param userid the userid
	 * @return the favourites by user id
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getFavouritesByUserId/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getFavouritesByUserId(@PathVariable int userid) {
		try {
			return new ResponseEntity<List<String>>(mabuluDao.getFavouritesByUserid(userid), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getFavouritesByUserId", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the stock price.
	 *
	 * @param fundName the fund name
	 * @return the stock price
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getStockPrice/{fundName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockPrice> getStockPrice(@PathVariable String fundName) {
		try {
			return new ResponseEntity<StockPrice>(mabuluDao.getFundStockPrice(fundName), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getStockPrice", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Login.
	 *
	 * @param userid    the userid
	 * @param favourite the favourite
	 * @return the response entity
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/insertFavourite", method = RequestMethod.GET)
	public ResponseEntity<String> login(@RequestParam("userid") int userid,
			@RequestParam("favourite") String favourite) {
		mabuluDao.insertFavourites(userid, favourite);
		try {
			return new ResponseEntity<String>("Inserted Sucessfully", HttpStatus.CREATED);
		} catch (Exception exception) {
			log.error("Error in insertFavourite", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the port folios.
	 *
	 * @param userportfolioid the userportfolioid
	 * @return the port folios
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortFolio/{userportfolioid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Data> getPortFolios(@PathVariable String userportfolioid) {
		try {
			Data data = new Data();
			if (!Objects.equals("null", userportfolioid)) {
				data.setPortFolios(mabuluDao.getPortfolioFunds(Integer.valueOf(userportfolioid)));
			} else {
				List<PortFolio> portFolios = new ArrayList<>();
				data.setPortFolios(portFolios);
			}
			return new ResponseEntity<Data>(data, HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getPortFolios", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the port folios by userid.
	 *
	 * @param userid the userid
	 * @return the port folios by userid
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortFoliosByUserid/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PortFolioNameJson>> getPortFoliosByUserid(@PathVariable int userid) {
		try {
			return new ResponseEntity<List<PortFolioNameJson>>(mabuluDao.getPortFoliosByUser(userid), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getPortFoliosByUserid", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the value by id.
	 *
	 * @param userportfolioid the userportfolioid
	 * @return the value by id
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getSinglePortFolioById/{userportfolioid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SinglePortFolio> getValueById(@PathVariable String userportfolioid) {
		try {
			return new ResponseEntity<SinglePortFolio>(
					mabuluDao.getSinglePortFolioByid(Integer.parseInt(userportfolioid)), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getSinglePortFolioById", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete port folio from userid.
	 *
	 * @param portFolioId the port folio id
	 * @return the response entity
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/deletePortFolioFromUserid/{portFolioId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnMessage> deletePortFolioFromUserid(@PathVariable String portFolioId) {
		try {
			mabuluDao.deletePortFolioEntry(Integer.valueOf(portFolioId));
			ReturnMessage returnMessage = new ReturnMessage();
			returnMessage.setMessage("Done");
			return new ResponseEntity<ReturnMessage>(returnMessage, HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in deletePortFolioFromUserid", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Post single port folio.
	 *
	 * @param singlePortFolio the single port folio
	 * @return the response entity
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/postSinglePortFolio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnMessage> postSinglePortFolio(@RequestBody SinglePortFolio singlePortFolio) {
		try {
			if (StringUtils.isEmpty(singlePortFolio.getIdportfolio())) {
				mabuluDao.insertSinglePortFolio(singlePortFolio);
			} else {
				mabuluDao.updatePortFolioEntry(singlePortFolio);
			}
			ReturnMessage returnMessage = new ReturnMessage();
			returnMessage.setMessage("Done");
			return new ResponseEntity<ReturnMessage>(returnMessage, HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in postSinglePortFolio", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Post new port folio.
	 *
	 * @param portFolioNameJson the port folio name json
	 * @return the response entity
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/postNewPortFolio", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnMessage> postNewPortFolio(@RequestBody PortFolioNameJson portFolioNameJson) {
		try {
			mabuluDao.insertNewPortFolio(portFolioNameJson);
			ReturnMessage returnMessage = new ReturnMessage();
			returnMessage.setMessage("Done");
			return new ResponseEntity<ReturnMessage>(returnMessage, HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in postNewPortFolio", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Generate csv files.
	 *
	 * @param userId the user id
	 * @return the response entity
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/generateCsvFiles/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnMessage> generateCsvFiles(@PathVariable int userId) {
		try {
			mabuluDao.generateDomainObjectsForCsvFiles(userId);
			ReturnMessage returnMessage = new ReturnMessage();
			returnMessage.setMessage("Done");
			return new ResponseEntity<ReturnMessage>(returnMessage, HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in generateCsvFiles", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the scld va grid avg combined factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the scld va grid avg combined factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getScldVaGridAvgCombinedFactors/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getScldVaGridAvgCombinedFactors(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadService.getScldVaGridAvgCombinedFactors(
					moduleName, folderName, "ScldVaRWAvgGridCombined_FACTORSnPort.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getScldVaGridAvgCombinedFactors", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the RR trade off grid combined factors.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the RR trade off grid combined factors
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getRRTradeOffGridCombinedFactors/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getRRTradeOffGridCombinedFactors(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadService.getRRTradeOffGridCombinedFactors(
					moduleName, folderName, "RRTradeOffGridCombined_FACTORSnPort.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getRRTradeOffGridCombinedFactors", exception);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Gets the RR trade off grid.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the RR trade off grid
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getRRTradeOffGrid/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getRRTradeOffGrid(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(
					jsonReadService.getRRTradeOffGrid(moduleName, folderName, "RRTradeOffGrid.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getRRTradeOffGrid", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the port va grid combined.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the port va grid combined
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortVaGridCombined/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getPortVaGridCombined(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(
					jsonReadService.getPortVaGridCombined(moduleName, folderName, "PORTVaRGridCombined99.json"),
					HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getPortVaGridCombined", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the port returns combined factors port.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the port returns combined factors port
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getPortReturnsCombinedFactorsPort/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getPortReturnsCombinedFactorsPort(@PathVariable String folderName,
			@PathVariable String moduleName) {
		log.debug("In getPortReturnsCombinedFactorsPort Controller");
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadService.getPortReturnsCombinedFactorsPort(
					moduleName, folderName, " PortReturnsCombined_FACTORSnPort.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getPortReturnsCombinedFactorsPort", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the va grid combined factors port.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the va grid combined factors port
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getVaGridCombinedFactorsPort/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getVaGridCombinedFactorsPort(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(jsonReadService.getVaGridCombinedFactorsPort(
					moduleName, folderName, "PORTVaRGridCombined99_FACTORSnPort.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getVaGridCombinedFactorsPort", exception);
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Gets the va grid.
	 *
	 * @param folderName the folder name
	 * @param moduleName the module name
	 * @return the va grid
	 */
	@CrossOrigin(origins = "http://localhost")
	@RequestMapping(value = "/getVaGrid/{moduleName}/{folderName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PortReturnsCombinedFactors> getVaGrid(@PathVariable String folderName,
			@PathVariable String moduleName) {
		try {
			return new ResponseEntity<PortReturnsCombinedFactors>(
					jsonReadService.getVaGrid(moduleName, folderName, "VaRGrid.json"), HttpStatus.OK);
		} catch (Exception exception) {
			log.error("Error in getVaGrid", exception);
			System.out.println();
			return new ResponseEntity<PortReturnsCombinedFactors>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
