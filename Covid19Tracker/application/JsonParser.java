package application;

import java.io.*;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class JsonParser {

	public void liveURLToJSON() {

		/*
		 * DOESNOT WORK FOR COPYING LONG FILES
		 * 
		 * try { URL url = new
		 * URL("https://api.covid19india.org/state_district_wise.json");
		 * HttpURLConnection huc = (HttpURLConnection)url.openConnection();
		 * if(huc.getResponseCode()==200) { InputStream is = huc.getInputStream();
		 * StringBuffer sb = new StringBuffer(); BufferedReader br = new
		 * BufferedReader(new InputStreamReader(is)); FileOutputStream fo = new
		 * FileOutputStream("covid19json.json"); BufferedWriter bw = new
		 * BufferedWriter(new OutputStreamWriter(fo)); String line = br.readLine();
		 * while(line!=null) { bw.write(line); bw.newLine(); System.out.println(line);
		 * line=br.readLine(); }
		 * 
		 * } else { System.out.println("System down"); } } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		
		try {
			InputStream in = new URL("https://api.covid19india.org/raw_data.json").openStream();
			Files.copy(in, Paths.get("jsoncovid19.json"), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
