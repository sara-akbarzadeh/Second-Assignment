import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApp {
    // Copy your API-KEY here
    public final static String apiKey = "cf832432b9f94642a5862420232702";
    // TODO: Write main function
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("enter the city name: ");
        String city = in.nextLine();
        String weatherData = getWeatherData(city);
        double temp = getTemperature(weatherData);
        int hum = getHumidity(weatherData);
        double speed = getwindspeed(weatherData);
        String direction = getwinddirection(weatherData);
        System.out.println(city);
        System.out.println(temp);
        System.out.println(hum);
        System.out.println(speed);
        System.out.println(direction);



        /**
         * Retrieves weather data for the specified city.
         *
         * @param city the name of the city for which weather data should be retrieved
         * @return a string representation of the weather data, or null if an error occurred
         */



      }
    public static String getWeatherData (String city){
        try {
            URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=" + city);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO: Write getTemperature function returns celsius temperature of city by given json string
    public static double getTemperature (String weatherJson){
        double answer = 0.0;
        JSONObject information = new JSONObject(weatherJson);
        answer = information.getJSONObject("current").getDouble("temp_c");
        return answer;
    }
    // TODO: Write getHumidity function returns humidity percentage of city by given json string
    public static int getHumidity (String weatherJson){
        int answer = 0;
        JSONObject information = new JSONObject(weatherJson);
        answer = information.getJSONObject("current").getInt("humidity");
        return answer;
    }

    public static double getwindspeed (String weatherJson){
        double answer = 0;
        JSONObject information = new JSONObject(weatherJson);
        answer = information.getJSONObject("current").getDouble("wind_mph");
        return answer;
    }
    public static String getwinddirection (String weatherJson){
        String answer;
        JSONObject information = new JSONObject(weatherJson);
        answer = information.getJSONObject("current").getString("wind_dir");
        return answer;
    }
}