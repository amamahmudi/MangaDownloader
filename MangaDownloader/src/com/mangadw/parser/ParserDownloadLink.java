package com.mangadw.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mangadw.conn.DownloadConn;
import com.mangadw.dw.Downloader;

public class ParserDownloadLink {
	private static final Logger logger = LogManager
			.getLogger(ParserDownloadLink.class);

	public static void parse(DownloadConn dwConn) throws IOException {
		Document doc = Jsoup.connect(dwConn.getOriginLink()).get();
		logger.trace("Trying parse {}", dwConn.getOriginLink());

		Elements tbElements = doc.select("table[class=datalist]");

		for (Element tbElement : tbElements) {
			Elements trElements = tbElement.select("tr[class=datarow]");
			for (Element trElement : trElements) {
				Elements aElements = trElement
						.select("td[class=datarow-0] > a");
				for (Element aElement : aElements) {
					String ahref = aElement.attr("href");

					logger.debug("Link Origin Download : {}", ahref);
					parseOriginDwLink(ahref);

				}
			}

		}

		logger.trace("Finish parse {}", dwConn.getOriginLink());

	}

	public static void parseOriginDwLink(String ahref) throws IOException {
		Document doc = Jsoup.connect(ahref).get();
		logger.trace("Trying parse {}", ahref);
		Elements divElements = doc.select("div[class=dloptions]");
		for (Element divElement : divElements) {

			Element liElement = divElement.select("ul > li > a").last();

			String tHref = liElement.attr("href");
			logger.debug("Link tusfiles Download : {}", tHref);
			parseTuseLink(tHref);

		}
		logger.trace("Finish parse {}", ahref);

	}

	public static void parseTuseLink(String ahref) throws IOException {
		Document doc = Jsoup.connect(ahref).get();
		logger.trace("Trying parse {}", ahref);
		HttpPost post = new HttpPost(ahref);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		Elements formElements = doc
				.select("form[name=F1] > input[type=hidden]");
		for (Element element : formElements) {

			logger.debug("Hidden input : {}", element);
			nvps.add(new BasicNameValuePair(element.attr("name"), element
					.attr("value")));
		}

		post.setEntity(new UrlEncodedFormEntity(nvps));
		HttpClient defaultHttpClient = HttpClients.createDefault();
		HttpResponse response = defaultHttpClient.execute(post);

		Header lastHeader = response.getLastHeader("Location");

		Downloader.doDownload(lastHeader);

		logger.trace("Finish parse {}", ahref);

	}

}
