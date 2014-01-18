package com.mangadw.parser;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebParser {

	private static final Logger logger = LogManager.getLogger(WebParser.class);

	/**
	 * Get link and manga name's from manga website and insert it to map
	 * 
	 */
	public static void parse(Document doc, Map<String, String> pMaps) {

		logger.trace("Trying parse {}", doc.getClass());

		Elements tbElements = doc.select("table[class=datalist]");

		for (Element tbElement : tbElements) {
			Elements trElements = tbElement.select("tr[class=datarow]");
			for (Element trElement : trElements) {
				Elements aElements = trElement
						.select("td[class=datarow-0] > a");
				for (Element aElement : aElements) {
					String ahref = aElement.attr("href");
					String alt = aElement.select("img").first().attr("alt");

					pMaps.put(ahref,alt);
					logger.debug("Link Download : {}", ahref);

					logger.debug("Manga Name : {}", alt);
				}
			}

		}

		logger.trace("Finish parse {}", doc.getClass());
	}

}
