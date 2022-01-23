package src;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JsonFlighFillerOracle {
    private ArrayList<Flight> list;

    JsonFlighFillerOracle(String jsonString, World w) {
        list = new ArrayList<Flight>();
        try {
            InputStream is = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            JsonReader rdr = Json.createReader(is);
            JsonObject obj = rdr.readObject();
            JsonArray results = obj.getJsonArray("data");
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {
                try {
                    System.out.println(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayFlight()
    {

    }

    public void getList()
    {

    }
}
