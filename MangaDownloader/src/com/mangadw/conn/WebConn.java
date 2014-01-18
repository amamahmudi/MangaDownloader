/**
 * 
 */
package com.mangadw.conn;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author ama Connect to web that provide manga
 * 
 */
public class WebConn {

	final Document doc;

	public WebConn(String url) throws IOException {
		doc = Jsoup.connect(url).userAgent("Mozilla/17.0").get();
	}

	/**
	 * Get element from manga website that want to download
	 * 
	 * 
	 */
	public void parse() {
		System.out.println("Trying parse");

		Elements tbElements = doc.select("table[class=datalist]");

		for (Element tbElement : tbElements) {
			Elements trElements = tbElement.select("tr[class=datarow]");
			for (Element trElement : trElements) {
				Elements aElements = trElement
						.select("td[class=datarow-0] > a");
				for (Element aElement : aElements) {
					System.out.println(aElement.attr("href"));
					System.out.println(aElement.select("img").first().attr("alt"));
				}
			}

		}

		System.out.println("Finish parse");
	}

}
