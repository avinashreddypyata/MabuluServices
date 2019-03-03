package com.mabulu.project.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mabulu.project.dynamic.domain.DomainWithTickerAndValues;
import com.mabulu.project.dynamic.domain.DynamicPortFolio;
import com.mabulu.project.dynamic.domain.PortReturnsCombinedFactors;
import com.mabulu.project.dynamic.domain.TickerDomain;

public class JsonReadAndGenerateDynamicTickers {

	public static PortReturnsCombinedFactors generateTickers(String contents, String labelName) throws IOException {
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(contents);
		PortReturnsCombinedFactors portReturnsCombinedFactors = new PortReturnsCombinedFactors();
		List<String> dates = new ArrayList<>();
		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
		List<JsonNode> arrayList = new ArrayList<JsonNode>();
		List<Double> listForMaxList = new ArrayList<>();
		while (fieldsIterator.hasNext()) {
			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			if (Objects.equals(field.getKey(), "data")) {
				arrayList.add(field.getValue());
			}
		}
		JsonNode jsonNode = arrayList.get(0);
		List<DynamicPortFolio> dynamicPortFolios = new ArrayList<>();
		String[] splitString = jsonNode.toString().split("},");
		for (String splitStringValue : splitString) {
			String[] eachString = splitStringValue.split(",");
			List<TickerDomain> tickerDomains = new ArrayList<>();
			String dateValue = null;
			for (String eachValue : eachString) {
				TickerDomain tickerDomain = new TickerDomain();
				String formattedEachValue = eachValue.replace("}]", "");
				String[] eachStringSplit = formattedEachValue.split(":");
				if (!eachStringSplit[0].contains(labelName)) {
					tickerDomain.setTicker(eachStringSplit[0].replaceAll("\"", ""));
					tickerDomain.setValue(Double.valueOf(eachStringSplit[1]));
					listForMaxList.add(Double.valueOf(eachStringSplit[1]));
					tickerDomains.add(tickerDomain);
				} else {
					dateValue = eachStringSplit[1].replaceAll("\"", "");
					dates.add(dateValue);
				}
			}
			portReturnsCombinedFactors.setLabelValues(dates);
			DynamicPortFolio dynamicPortFolio = new DynamicPortFolio();
			dynamicPortFolio.setDate(dateValue);
			dynamicPortFolio.setTickerDomains(tickerDomains);
			dynamicPortFolios.add(dynamicPortFolio);
		}
		portReturnsCombinedFactors.setMaxValue(Collections.max(listForMaxList));
		portReturnsCombinedFactors.setMinValue(Collections.min(listForMaxList));
		portReturnsCombinedFactors.setDomainWithTickerAndValues(
				getDomainWithTickers(dynamicPortFolios, dynamicPortFolios.get(0).getTickerDomains()));
		return portReturnsCombinedFactors;
	}

	public static PortReturnsCombinedFactors generateTickersForPredefinedLength(String contents, String labelName,
			int length) throws IOException {
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		JsonNode rootNode = mapper.readTree(contents);
		PortReturnsCombinedFactors portReturnsCombinedFactors = new PortReturnsCombinedFactors();
		List<String> dates = new ArrayList<>();
		Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
		List<JsonNode> arrayList = new ArrayList<JsonNode>();
		List<Double> listForMaxList = new ArrayList<>();
		while (fieldsIterator.hasNext()) {
			Map.Entry<String, JsonNode> field = fieldsIterator.next();
			if (Objects.equals(field.getKey(), "data")) {
				arrayList.add(field.getValue());
			}
		}
		JsonNode jsonNode = arrayList.get(0);
		List<DynamicPortFolio> dynamicPortFolios = new ArrayList<>();
		String[] splitString = jsonNode.toString().split("},");
		for (String splitStringValue : splitString) {
			String[] eachString = splitStringValue.split(",");
			List<TickerDomain> tickerDomains = new ArrayList<>();
			String dateValue = null;
			for (String eachValue : eachString) {
				TickerDomain tickerDomain = new TickerDomain();
				String formattedEachValue = eachValue.replace("}]", "");
				String[] eachStringSplit = formattedEachValue.split(":");
				if (!eachStringSplit[0].contains(labelName)) {
					tickerDomain.setTicker(eachStringSplit[0].replaceAll("\"", ""));
					tickerDomain.setValue(Double.valueOf(eachStringSplit[1]));
					listForMaxList.add(Double.valueOf(eachStringSplit[1]));
					tickerDomains.add(tickerDomain);
				} else {
					dateValue = eachStringSplit[1].replaceAll("\"", "");
					dates.add(dateValue);
				}
			}
			portReturnsCombinedFactors.setLabelValues(dates.subList(Math.max(dates.size() - length, 0), dates.size()));
			DynamicPortFolio dynamicPortFolio = new DynamicPortFolio();
			dynamicPortFolio.setDate(dateValue);
			dynamicPortFolio.setTickerDomains(tickerDomains);
			dynamicPortFolios.add(dynamicPortFolio);
		}
		portReturnsCombinedFactors.setMaxValue(Collections.max(listForMaxList));
		portReturnsCombinedFactors.setMinValue(Collections.min(listForMaxList));
		portReturnsCombinedFactors.setDomainWithTickerAndValues(getDomainWithTickersDynamicLength(dynamicPortFolios,
				dynamicPortFolios.get(0).getTickerDomains(), length));
		return portReturnsCombinedFactors;
	}

	/*
	 * public static void main(String[] args) throws IOException {
	 * generateTickers(new String(Files.readAllBytes(Paths .get(
	 * "/Users/macpro/Downloads/WallStreet/Dynamic/P1/MembersRecount/PortReturnsCombined_FACTORS.json"
	 * ))),"Date"); }
	 */

	public static List<DomainWithTickerAndValues> getDomainWithTickers(List<DynamicPortFolio> dynamicPortFolios,
			List<TickerDomain> tickerDomains) {
		List<String> tickerNames = new ArrayList<>();
		Map<String, List<Double>> tickerNameValueMap = new HashMap<>();
		tickerDomains.forEach(tickerDomain -> {
			tickerNames.add(tickerDomain.getTicker());
			tickerNameValueMap.put(tickerDomain.getTicker(), new ArrayList<>());
		});
		dynamicPortFolios.forEach(dynamicPortFolio -> {
			List<TickerDomain> tickerDomainValueAll = dynamicPortFolio.getTickerDomains();
			tickerDomainValueAll.forEach(tickerDomainValue -> {
				if (tickerNames.contains(tickerDomainValue.getTicker())) {
					tickerNameValueMap.get(tickerDomainValue.getTicker()).add(tickerDomainValue.getValue());
				}
			});

		});
		List<DomainWithTickerAndValues> domainWithTickerValues = new ArrayList<>();
		Iterator<Entry<String, List<Double>>> it = tickerNameValueMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<Double>> pair = it.next();
			DomainWithTickerAndValues domainWithTickerAndValues = new DomainWithTickerAndValues();
			domainWithTickerAndValues.setDomainTickerName((String) pair.getKey());
			ArrayList<Double> tickerValueArrayList = new ArrayList<>();
			tickerValueArrayList.addAll(pair.getValue());
			domainWithTickerAndValues.setTickerValue(tickerValueArrayList);
			domainWithTickerValues.add(domainWithTickerAndValues);
			it.remove(); // avoids a ConcurrentModificationException
		}
		return domainWithTickerValues;

	}

	public static List<DomainWithTickerAndValues> getDomainWithTickersDynamicLength(
			List<DynamicPortFolio> dynamicPortFolios, List<TickerDomain> tickerDomains, int length) {
		List<String> tickerNames = new ArrayList<>();
		Map<String, List<Double>> tickerNameValueMap = new HashMap<>();
		tickerDomains.forEach(tickerDomain -> {
			tickerNames.add(tickerDomain.getTicker());
			tickerNameValueMap.put(tickerDomain.getTicker(), new ArrayList<>());
		});
		dynamicPortFolios.forEach(dynamicPortFolio -> {
			List<TickerDomain> tickerDomainValueAll = dynamicPortFolio.getTickerDomains();
			tickerDomainValueAll.forEach(tickerDomainValue -> {
				if (tickerNames.contains(tickerDomainValue.getTicker())) {
					tickerNameValueMap.get(tickerDomainValue.getTicker()).add(tickerDomainValue.getValue());
				}
			});

		});
		List<DomainWithTickerAndValues> domainWithTickerValues = new ArrayList<>();
		Iterator<Entry<String, List<Double>>> it = tickerNameValueMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, List<Double>> pair = it.next();
			DomainWithTickerAndValues domainWithTickerAndValues = new DomainWithTickerAndValues();
			domainWithTickerAndValues.setDomainTickerName((String) pair.getKey());
			ArrayList<Double> tickerValueArrayList = new ArrayList<>();
			List<Double> pairValue = pair.getValue();
			tickerValueArrayList.addAll(pairValue.subList(Math.max(pairValue.size() - length, 0), pairValue.size()));
			domainWithTickerAndValues.setTickerValue(tickerValueArrayList);
			domainWithTickerValues.add(domainWithTickerAndValues);
			it.remove();
		}
		return domainWithTickerValues;

	}

}
