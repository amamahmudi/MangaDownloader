/**
 * 
 */
package com.mangadw.conn;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @author ama Connect to web that provide manga
 * 
 */
public class WebConn {
	private final static Logger logger = LogManager.getLogger(WebConn.class);

	private final Document doc;

	public WebConn(String url) throws IOException {

		logger.trace("Connecting to {}", url);
		doc = Jsoup.connect(url).userAgent("Mozilla/17.0").get();
		logger.trace("{} is build", doc.getClass());

	}

	public Document getDoc() {
		return doc;
	}

}
