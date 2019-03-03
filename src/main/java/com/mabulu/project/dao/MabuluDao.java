package com.mabulu.project.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mabulu.project.domain.Company;
import com.mabulu.project.domain.CsvFileDomain;
import com.mabulu.project.domain.PortFolio;
import com.mabulu.project.domain.PortFolioNameJson;
import com.mabulu.project.domain.SinglePortFolio;
import com.mabulu.project.domain.StockPrice;
import com.mabulu.project.helper.CsvGenerator;

@Repository
public class MabuluDao {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private CsvGenerator csvGenerator;

	@Value("${dynamicScriptFilePath}")
	private String dynamicScriptFilePath;

	/** The log. */
	private Logger log = LoggerFactory.getLogger(MabuluDao.class);

	private static final String insertSql =

			"INSERT INTO portfolio (" + "user_portfolioid," +

					"fund_code," +

					"quantity) " +

					"VALUES (?,?,?)";

	public List<Company> getCompanies() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM companylist";

		List<Company> companies = new ArrayList<Company>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for (Map<?, ?> row : rows) {
			Company company = new Company();
			company.setSymbol((String) row.get("Symbol"));
			company.setName((String) row.get("Name"));
			companies.add(company);
		}

		return companies;
	}

	public boolean test() {
		return false;
	}

	public String getPassword(String username) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT password FROM users_creds where username = " + "'" + username + "'";

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		if (!rows.isEmpty()) {

			return (String) rows.get(0).get("password");
		}
		return null;

	}

	public List<String> getFundNames() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT fund_name FROM fundsbreakdown order by fund_name";
		List<String> fundNames = new ArrayList<String>();
		;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			fundNames.add((String) rowsListIterator.next().get("fund_name"));
		}
		return fundNames;

	}

	public List<String> getFundFamilyNames() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT DISTINCT fund_family FROM fundsbreakdown order by fund_family";
		List<String> fundFamilyNames = new ArrayList<String>();
		;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			fundFamilyNames.add((String) rowsListIterator.next().get("fund_family"));
		}
		return fundFamilyNames;

	}

	public List<String> getFundSectors() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT DISTINCT fund_sector FROM fundsbreakdown order by fund_sector";
		List<String> fundSectorNames = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			fundSectorNames.add((String) rowsListIterator.next().get("fund_sector"));
		}
		return fundSectorNames;
	}

	public List<PortFolioNameJson> getPortFoliosByUser(int userid) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT portfolio_name,iduser_portfolio FROM user_portfolio WHERE userid=? ORDER BY portfolio_name";
		List<PortFolioNameJson> portFolioNames = new ArrayList<PortFolioNameJson>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userid);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			Map<String, Object> row = rowsListIterator.next();
			PortFolioNameJson portFolioNameJson = new PortFolioNameJson();
			portFolioNameJson.setPortFolioName((String) row.get("portfolio_name"));
			portFolioNameJson.setIduserportfolio((int) row.get("iduser_portfolio"));
			portFolioNames.add(portFolioNameJson);
		}
		return portFolioNames;

	}

	public List<String> getFundFamilyNamesByFundSector(String sectorName) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT DISTINCT fund_family FROM fundsbreakdown where fund_sector = " + "'" + sectorName + "'"
				+ "ORDER BY fund_family";
		List<String> fundSectorNames = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			fundSectorNames.add((String) rowsListIterator.next().get("fund_family"));
		}
		return fundSectorNames;
	}

	public List<String> getFundFundCodeByFundSector(String sectorName) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT  fund_name  FROM fundsbreakdown where fund_sector= " + "'" + sectorName + "'"
				+ "order by fund_name";
		List<String> fundSectorNames = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			fundSectorNames.add((String) rowsListIterator.next().get("fund_name"));
		}
		return fundSectorNames;

	}

	public List<String> getFundCodeByFundFamilyName(String fundFamily) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT  fund_name  FROM fundsbreakdown where fund_family = " + "'" + fundFamily + "'"
				+ "order by fund_name";
		List<String> fundSectorNames = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			fundSectorNames.add((String) rowsListIterator.next().get("fund_name"));
		}
		return fundSectorNames;

	}

	public int getUserIdByUserName(String username) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT user_id FROM users_creds where username = " + "'" + username + "'";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		if (rowsListIterator.hasNext()) {
			return (int) rowsListIterator.next().get("user_id");
		}
		return 0;

	}

	public List<String> getFavouritesByUserid(int userid) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT favourite FROM userfavourites where users_userid =" + userid;
		List<String> favourites = new ArrayList<String>();
		;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {
			favourites.add((String) rowsListIterator.next().get("favourite"));
		}
		return favourites;

	}

	// INSERT INTO `WallStreetStocks`.`userfavourites` (`users_userid`, `favourite`)
	// VALUES ('1', 'RYZCX');
	public void insertFavourites(int userid, String favourite) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO userfavourites (users_userid, favourite) VALUES (?, ?)";
		jdbcTemplate.update(sql, userid, favourite);
	}

	public void insertNewPortFolio(PortFolioNameJson portFolioNameJson) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "INSERT INTO user_portfolio (userid, portfolio_name) VALUES (?, ?)";
		jdbcTemplate.update(sql, portFolioNameJson.getUserid(), portFolioNameJson.getPortFolioName());
	}

	public void insertSinglePortFolio(SinglePortFolio singlePortFolio) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// define query arguments
		Object[] params = new Object[] { singlePortFolio.getUserportfolioid(), singlePortFolio.getFundName(),
				singlePortFolio.getQuantity() };

		// define SQL types of the arguments
		int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.VARCHAR };

		jdbcTemplate.update(insertSql, params, types);
	}

	public StockPrice getFundStockPrice(String fundCode) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM prices where fund_code = " + "'" + fundCode + "'";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		StockPrice stockPrice = new StockPrice();
		Map<String, Object> row = rowsListIterator.next();
		stockPrice.setStockName((String) row.get("fund_code"));
		Double fundStockPrice = (Double) row.get("fund_stockprice");
		double roundOffStockPrice = Math.round(fundStockPrice * 100.0) / 100.0;
		stockPrice.setStockPrice(roundOffStockPrice);
		return stockPrice;

	}

	public List<PortFolio> getPortfolioFunds(int userportfolioid) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM portfolio where user_portfolioid = ?";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userportfolioid);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();

		List<PortFolio> portFolios = new ArrayList<>();
		while (rowsListIterator.hasNext()) {
			PortFolio portFolio = new PortFolio();
			Map<String, Object> row = rowsListIterator.next();
			portFolio.setFundCode(((String) row.get("fund_code")));
			portFolio.setQuantity(((String) row.get("quantity")));
			String updateButton = "<button type='button' name='update' class='btn btn-warning btn-xs update'" + "id='"
					+ (int) row.get("idportfolio") + "'>Update</button>";
			String deleteButton = "<button type='button' name='delete' class='btn btn-danger btn-xs delete'" + "id='"
					+ (int) row.get("idportfolio") + "'>Delete</button>";
			portFolio.setUpdateButton(updateButton);
			portFolio.setDeleteButton(deleteButton);
			portFolios.add(portFolio);
		}
		return portFolios;
	}

	public void deletePortFolioEntry(int portfolioid) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "DELETE FROM portfolio WHERE idportfolio = ?";
		jdbcTemplate.update(sql, portfolioid);

	}

	public void updatePortFolioEntry(SinglePortFolio singlePortFolio) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "UPDATE portfolio SET fund_code = ?, quantity = ?" + "WHERE idportfolio = ?";
		jdbcTemplate.update(sql, singlePortFolio.getFundName(), singlePortFolio.getQuantity(),
				singlePortFolio.getIdportfolio());

	}

	public SinglePortFolio getSinglePortFolioByid(int userportfolioid) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT fund_code,quantity from portfolio where idportfolio = ?";
		SinglePortFolio singlePortFolio = new SinglePortFolio();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userportfolioid);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		if (rowsListIterator.hasNext()) {
			Map<String, Object> row = rowsListIterator.next();
			singlePortFolio.setFundName((String) row.get("fund_code"));
			singlePortFolio.setQuantity((String) row.get("quantity"));
		}

		return singlePortFolio;

	}

	public void generateDomainObjectsForCsvFiles(int userid)
			throws InvalidFormatException, IOException, InterruptedException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT portfolio.fund_code,portfolio.user_portfolioid,user_portfolio.portfolio_name,portfolio.quantity\n"
				+ "FROM portfolio\n" + "INNER JOIN user_portfolio \n"
				+ "ON portfolio.user_portfolioid = user_portfolio.iduser_portfolio \n"
				+ "where user_portfolio.userid=?";
		List<CsvFileDomain> csvFileDomains = new ArrayList<>();
		Set<String> portFolioNames = new LinkedHashSet<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, userid);
		ListIterator<Map<String, Object>> rowsListIterator = rows.listIterator();
		while (rowsListIterator.hasNext()) {

			Map<String, Object> rowMap = rowsListIterator.next();

			portFolioNames.add((String) rowMap.get("portfolio_name"));
			CsvFileDomain csvFileDomain = new CsvFileDomain();
			csvFileDomain.setFundName((String) rowMap.get("fund_code"));
			csvFileDomain.setPortFolioName((String) rowMap.get("portfolio_name"));//
			csvFileDomain.setQuantity((String) rowMap.get("quantity"));
			csvFileDomains.add(csvFileDomain);

		}
		csvGenerator.createFundNamesCsv(portFolioNames, csvFileDomains);
		csvGenerator.createFundQuantityCsv(portFolioNames, csvFileDomains);
		generateTheFiles("P2");

	}

	private void generateTheFiles(String portFolioName) throws IOException, InterruptedException {
		ProcessBuilder pb = new ProcessBuilder(dynamicScriptFilePath, portFolioName);
		Process p = pb.start();
		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = null;
		while ((line = reader.readLine()) != null) {
			log.debug(line);
		}
		log.debug("The Status of the Process is {} ", Integer.toString(p.waitFor()));
	}

}
