package com.mangadw;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mangadw.parser.WebParser;

public class Start {

	private static final Logger logger = LogManager.getLogger(Start.class);
	public static String filePath = "/home/ama/Downloads/Gamelix/";
	public static void main(String[] args) {

		try {
		

			WebParser.parse("http://gamelix.com/resources/Unity%20Free%20Assets/");
			//WebParser.parse("http://gamelix.com/resources/Unity%20Free%20Assets/3DMagicVR/");
			
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
