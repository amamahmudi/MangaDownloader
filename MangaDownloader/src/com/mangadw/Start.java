package com.mangadw;

import java.io.IOException;

import com.mangadw.conn.WebConn;

public class Start {

	public static void main(String[] args) {
		
		try {
			
			new WebConn("http://www.mangashare.com").parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
