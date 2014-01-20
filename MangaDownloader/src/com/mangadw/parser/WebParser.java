package com.mangadw.parser;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mangadw.conn.DownloadConn;

public class WebParser {

	private static final Logger logger = LogManager.getLogger(WebParser.class);

	/**
	 * Get link download all
	 * 
	 * @throws IOException
	 * 
	 */
	public static void parse(String url, List<DownloadConn> linkLists)
			throws IOException {
		Document doc = Jsoup.connect(url).get();
		logger.trace("Trying parse {}", url);

		Elements tbElements = doc.select("a");

		for (Element tbElement : tbElements) {
			
					logger.debug("Link Download : {}", tbElement);

				
					
			}

	

		logger.trace("Finish parse {}", url);
	}

}
