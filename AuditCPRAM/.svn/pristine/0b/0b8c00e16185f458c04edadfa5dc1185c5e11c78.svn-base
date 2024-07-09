package th.co.gosoft.audit.cpram.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class APICallerUtils {

	private static String username = "";
	private static String password = "";
	
	public APICallerUtils(){
	}
	
//	JSONObject jSONObject
	public static String generateCheckListPlan(String json, String url) throws IOException {
//		webServiceUrl = webServiceUrl.replace("10.151.36.163:9088","localhost:8080");
		try{
			URL object=new URL(url);
			HttpURLConnection con = (HttpURLConnection) object.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
			con.setRequestMethod("POST");
			con.setConnectTimeout(15000);
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			wr.write(json);
			wr.flush();
			wr.close();
			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String jsonText = readAll(rd);
			con.disconnect();
			return jsonText;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		  sb.append((char) cp);
		}
		return sb.toString();
	}

}
