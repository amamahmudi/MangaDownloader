package com.mangadw.parser;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mangadw.Start;
import com.mangadw.dw.Downloader;

public class WebParser {

	private static final Logger logger = LogManager.getLogger(WebParser.class);

	/**
	 * Get link download all
	 * 
	 * @throws IOException
	 * 
	 */
	public static void parse(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		logger.trace("Trying parse {}", url);

		Elements tbElements = doc.select("a");

		for (Element tbElement : tbElements) {

			String href = tbElement.absUrl("href");
			String extension = FilenameUtils.getExtension(href);
			String attr = tbElement.attr("href");
			if (!extension.isEmpty()) {

				Downloader.doDownload(attr, href);
				
			} else if (!href.contains("?C=") && href.contains(url)) {
				Start.filePath = URLDecoder.decode(Start.filePath + attr,"UTF-8");
                boolean isMkdirSuccess = new File(Start.filePath).mkdir();
                if(isMkdirSuccess){
                	logger.debug("Creating folder {}",Start.filePath);
    				parse(href);
	
                }
                
			}

		}
		Start.filePath = "/home/ama/Downloads/Gamelix/";
		logger.trace("Finish parse {}", url);
	}

}
