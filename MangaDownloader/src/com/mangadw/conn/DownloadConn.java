package com.mangadw.conn;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DownloadConn {
	private static final Logger logger = LogManager
			.getLogger(DownloadConn.class);
	private final String originLink;
	private final List<String> originDownloadLink;
	private final String mangaName;
	private final List<String> downloadLink;

	public DownloadConn(String originLink, String mangaName) {

		this.originLink = originLink;
		this.mangaName = mangaName;
		originDownloadLink = new ArrayList<String>();
		downloadLink = new ArrayList<String>();
		logger.trace("{} download link created",mangaName);
	}

	public List<String> getDownloadLink() {
		return downloadLink;
	}

	public String getOriginLink() {
		return originLink;
	}

	public String getMangaName() {
		return mangaName;
	}

	public List<String> getOriginDownloadLink() {
		return originDownloadLink;
	}

	public void addOriginDownloadLink(String ahref) {
		originDownloadLink.add(ahref);
		
	}

	

}
