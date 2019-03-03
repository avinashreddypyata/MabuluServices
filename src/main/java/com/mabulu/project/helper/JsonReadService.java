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
import com.mabulu.project.domain.PortFactors;
import com.mabulu.project.dynamic.domain.PortReturnsCombinedFactors;
import com.mabulu.project.util.JsonReadAndGenerateDynamicTickers;

@Component
public class JsonReadService {

	@Value("${basePath}")
	private String basePath;

	/*
	 * public static void main(String[] args) throws JsonParseException,
	 * JsonMappingException, IOException { UUID uniqueKey = UUID.randomUUID();
	 * System.out.println(uniqueKey.toString().length());
	 * System.out.println(uniqueKey.toString()); Charset charset =
	 * StandardCharsets.UTF_8;
	 * 
	 * // getSpyFactorsData("WithHeader_SPYPORTFactors_VALUES.json"); }
	 */

	private void alterFileContentWithNoFolderName(String fileName, String fundName)
			throws FileNotFoundException, IOException {
		String content = IOUtils.toString(new FileInputStream(fileName), "utf-8");

		content = content.replaceAll(fundName + "_", "");
		IOUtils.write(content, new FileOutputStream(fileName), "utf-8");

	}

	public PortFactors getPortFactorsData(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		this.alterFileContentWithNoFolderName(basePath + "/" + moduleName + "/" + folderName + "/" + fileName,
				folderName);
		PortFactors portFactorsData = mapper.readValue(
				new File(basePath + "/" + moduleName + "/" + folderName + "/" + fileName), PortFactors.class);
		return portFactorsData;

	}

	public PortReturnsCombinedFactors getScldVaGridAvgCombinedFactors(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	//
	public PortReturnsCombinedFactors getRRTradeOffGridCombinedFactors(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getRRTradeOffGrid(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getPortVaGridCombined(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getPortReturnsCombinedFactorsPort(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"Date");
	}

	// PORTVaRGridCombined99_FACTORSnPort
	public PortReturnsCombinedFactors getVaGridCombinedFactorsPort(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	// VaRGrid
	public PortReturnsCombinedFactors getVaGrid(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getSpyReturnsGraphHistorical(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"Date");
	}

	public PortReturnsCombinedFactors getRiskReturnTradeOffGridCombined(String moduleName, String folderName,
			String fileName) throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getScldAvgData(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}

	public PortReturnsCombinedFactors getScldAvgCombinedData(String moduleName, String folderName, String fileName)
			throws JsonParseException, JsonMappingException, IOException {
		return JsonReadAndGenerateDynamicTickers.generateTickers(
				new String(
						Files.readAllBytes(Paths.get(basePath + "/" + moduleName + "/" + folderName + "/" + fileName))),
				"index");
	}
}
