package src;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class World {

    protected ArrayList<Aeroport> list;

    World(String fileName)
    {
        list = new ArrayList<Aeroport>();
        try
        {
            BufferedReader buf = new BufferedReader(new FileReader(fileName));
            String s = buf.readLine();
            while (s!=null)
            {
                s = s.replaceAll("\"","");
                String fields[] = s.split(",");
                if(fields[1].equals("large_airport"))
                {
                    String lat = fields[11];
                    String lon = fields[12];
                    System.out.println("lat : " + lat);
                    System.out.println("lon : " + lon);
                    //list.add(new Aeroport(fields[9],fields[2],fields[5],fields[11]))
                }
                s = buf.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("maybe the file isn't there?");
            //System.out.println(list.get(list.size()-1));
            e.printStackTrace();
        }
    }
}
