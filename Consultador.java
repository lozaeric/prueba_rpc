import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Consultador {
	private static JSONParser parser = new JSONParser();
	private static String url = "http://api.openweathermap.org/data/2.5/forecast/city";
	private static String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
	private static String param1 ;//= "London";
	private static String param2 = "d34e7ff512cceea4291f3629809e9b52";
	
	public static Clima consultar (int id) {
		InputStream response = null;
		BufferedReader br = null;
		param1 = Integer.toString(id);
		Clima clima  = null;
		
		try {
			String query = String.format("id=%s&appid=%s", 
			     URLEncoder.encode(param1, charset), 
			     URLEncoder.encode(param2, charset));
			
			URLConnection connection = new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			response = connection.getInputStream();
			br = new BufferedReader (new InputStreamReader (response));
			Object obj = parser.parse(br.readLine ());
			JSONObject array = (JSONObject) obj; 
			JSONArray list = (JSONArray) array.get("list");
			JSONObject primero = (JSONObject) list.get(0); 
			JSONObject main = (JSONObject) primero.get("main");
			JSONArray weather = (JSONArray) primero.get("weather");
			JSONObject primero_w = (JSONObject) weather.get(0);
			
			clima = new Clima (Double.parseDouble(main.get("temp").toString ()), primero_w.get ("description").toString());
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return clima;
	}

}
