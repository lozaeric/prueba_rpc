import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Consultador {
	private static JSONParser parser = new JSONParser();
	private static String url = "http://api.openweathermap.org/data/2.5/forecast/city";
	private static String charset = "UTF-8";  
	private static String param1 ;
	private static String param2 = "d34e7ff512cceea4291f3629809e9b52";
	private static final long serialVersionUID = -231512405473655759L;
	
	public static Clima consultar (int id) {
		InputStream response = null;
		BufferedReader br = null;
		param1 = Integer.toString(id);
		Clima clima  = null;
		
		try {
			//conexion http
			String query = String.format("id=%s&appid=%s", 
			     URLEncoder.encode(param1, charset), 
			     URLEncoder.encode(param2, charset));
			
			URLConnection connection = new URL(url + "?" + query).openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			response = connection.getInputStream();
			br = new BufferedReader (new InputStreamReader (response));
			Object obj = parser.parse(br.readLine ());
			br.close ();
			//parseo json
			JSONObject array = (JSONObject) obj; 
			JSONArray list = (JSONArray) array.get("list");
			JSONObject primero = (JSONObject) list.get(0); 
			JSONObject main = (JSONObject) primero.get("main");
			JSONArray weather = (JSONArray) primero.get("weather");
			JSONObject primero_w = (JSONObject) weather.get(0);
			//calcula fecha para cache
			Date ahora = Calendar.getInstance ().getTime ();
			clima = new Clima (Float.parseFloat(main.get("temp").toString ()), primero_w.get ("description").toString(), ahora);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return clima;
	}

}
