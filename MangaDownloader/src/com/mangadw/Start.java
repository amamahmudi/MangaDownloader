package com.mangadw;

import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mangadw.conn.WebConn;
import com.mangadw.parser.WebParser;

public class Start {

	private static final Logger logger = LogManager.getLogger(Start.class);

	public static void main(String[] args) {

		try {
			HashMap<String, String> pMaps = new HashMap<String, String>();
			HashMap<String, String> dMaps = new HashMap<String, String>();

			WebConn mangaShare = new WebConn("http://www.mangashare.com");

			WebParser.parse(mangaShare.getDoc(), pMaps);
			for (String mangaLink : pMaps.keySet()) {
				WebConn wCon = new WebConn(mangaLink);
				WebParser.parse(wCon.getDoc(), dMaps);
			}

			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
