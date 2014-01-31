package com.mangadw.dw;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.apache.http.Header;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mangadw.Start;

public class Downloader {

	private static final Logger logger = LogManager.getLogger(Downloader.class);

	public static void doDownload(String filename, String href)
			throws IOException {
		logger.trace("Filename that will download {}", filename);
		File file = new File(Start.filePath + filename);
		if (file.exists()) {
			logger.debug("{} already exist, cancel downloading it", filename);

		} else {
			logger.debug("{} is saving into {}", filename, Start.filePath);

			URL website = new URL(href);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(Start.filePath
					+ URLDecoder.decode(filename, "UTF-8"));

			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.flush();
			fos.close();
			logger.debug("Saving {} into {} is success ", filename,
					Start.filePath);
		}
		logger.trace("Finish getting {}", filename);

	}
}
