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
	 * Get link and manga name's from manga website and insert it to map
	 * 
	 * @throws IOException
	 * 
	 */
	public static void parse(String url, List<DownloadConn> linkLists)
			throws IOException {
		Document doc = Jsoup.connect(url).get();
		logger.trace("Trying parse {}", url);

		Elements tbElements = doc.select("table[class=datalist]");

		for (Element tbElement : tbElements) {
			Elements trElements = tbElement.select("tr[class=datarow]");
			for (Element trElement : trElements) {
				Elements aElements = trElement
						.select("td[class=datarow-0] > a");
				for (Element aElement : aElements) {
					String ahref = aElement.attr("href");
					String alt = aElement.select("img").first().attr("alt");

					logger.debug("Link Download : {}", ahref);

					logger.debug("Manga Name : {}", alt);
					DownloadConn e = new DownloadConn(ahref, alt);
					ParserDownloadLink.parse(e);

					linkLists.add(e);

				}
			}

		}

		logger.trace("Finish parse {}", url);
	}

}
