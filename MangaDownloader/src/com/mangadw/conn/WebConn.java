/**
 * 
 */
package com.mangadw.conn;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author ama Connect to web that provide manga
 * 
 */
public class WebConn {

	private final Document doc;
	private final static Logger 	logger = LogManager.getLogger(WebConn.class);;

	public WebConn(String url) throws IOException {
		
         
	
		logger.info("Connecting to {}", url);
		doc = Jsoup.connect(url).userAgent("Mozilla/17.0").get();

	}

	/**
	 * Get link and manga name's from manga website
	 * 
	 */
	public void parse() {

		logger.trace("Trying parse {}", doc);

		Elements tbElements = doc.select("table[class=datalist]");

		for (Element tbElement : tbElements) {
			Elements trElements = tbElement.select("tr[class=datarow]");
			for (Element trElement : trElements) {
				Elements aElements = trElement
						.select("td[class=datarow-0] > a");
				for (Element aElement : aElements) {
					logger.debug("Link Download : {}", aElement.attr("href"));
					logger.debug("Manga Name : {}", aElement.select("img")
							.first().attr("alt"));
				}
			}

		}

		logger.trace("Finish parse {}", doc);
	}

}
