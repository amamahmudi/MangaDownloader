package com.mangadw;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mangadw.parser.ParserDownloadLink;

public class Start {

	private static final Logger logger = LogManager.getLogger(Start.class);

	public static void main(String[] args) {

		try {
			// List<DownloadConn> linkLists = new ArrayList<>();

			// WebParser.parse("http://www.mangashare.com", linkLists);
            ParserDownloadLink.parseTuseLink("http://www.tusfiles.net/10pls3dda10p");
            
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
