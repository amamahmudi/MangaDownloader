package com.mangadw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mangadw.conn.DownloadConn;
import com.mangadw.parser.ParserDownloadLink;
import com.mangadw.parser.WebParser;

public class Start {

	private static final Logger logger = LogManager.getLogger(Start.class);

	public static void main(String[] args) {

		try {
			 List<DownloadConn> linkLists = new ArrayList<>();

			 WebParser.parse("http://gamelix.com/resources/Unity%20Free%20Assets/", linkLists);
			
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
