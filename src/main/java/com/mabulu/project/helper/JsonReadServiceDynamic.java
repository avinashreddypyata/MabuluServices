package com.mabulu.project.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabulu.project.dynamic.domain.PortReturnsCombinedFactors;
import com.mabulu.project.dynamic.domain.RelocationPortfolio;
import com.mabulu.project.util.JsonReadAndGenerateDynamicTickers;

@Component
public class JsonReadServiceDynamic {

	@Value("${dynamicbasePath}")
	private String basePath;

	private void alterFileContentWithNoFolderName(String fileName, String portfolioName)
			throws FileNotFoundException, IOException {
		String content = IOUtils.toString(new FileInputStream(fileName), "utf-8");

		content = content.replaceAll(portfolioName, "portfolio");
		IOUtils.write(content, new FileOutputStream(fileName), "utf-8");

	}

	public PortReturnsCombinedFactors getPortReturnsCombinedFactors(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickersForPredefinedLength(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"Date", 700);
	}

	public RelocationPortfolio getRelocationPortFolio(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		this.alterFileContentWithNoFolderName(basePath + "/" + moduleName + "/" + folderName + "/" + fileName,
				moduleName);
		RelocationPortfolio relocationPortfolio = mapper.readValue(
				new File(basePath + "/" + moduleName + "/" + folderName + "/" + fileName), RelocationPortfolio.class);
		return relocationPortfolio;

	}

	public PortReturnsCombinedFactors getScldAvg(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getPortVaGrid(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getPortVaGridCombinedFactors(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getRRTradeOffCombinedFactors(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getScldVaGridAvgFactors(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

}
