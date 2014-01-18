package com.mangadw.dw;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.http.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mangadw.conn.DownloadConn;

public class Downloader {

	
	private static final Logger logger = LogManager
			.getLogger(Downloader.class);
	
	public static void doDownload(Header header) throws IOException{
		String fileName = header.getValue().substring(
				header.getValue().lastIndexOf('/') + 1,
				header.getValue().length());
		
		logger.debug("Filename that will download {}", header.getValue());
		URL website = new URL(header.getValue());
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		String filePath = "/home/ama/Downloads/"+fileName;
		FileOutputStream fos = new FileOutputStream(filePath);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.flush();
		fos.close();
	}
}
