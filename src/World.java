package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.lang.Math;

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
                    list.add(new Aeroport(
                            fields[9], //IATA
                            fields[2], //name
                            fields[5], //Country
                            Double.parseDouble(fields[11]), // latitude
                            Double.parseDouble(fields[12])) // longitude
                    );
                }
                s = buf.readLine();
            }
        }
        catch (Exception e)
        {
            System.out.println("maybe the file isn't there?");
            System.out.println(list.get(list.size()-1));
            e.printStackTrace();
        }
    }

    protected double distance(double longitude, double latitude, Aeroport ap)
    {
        double apLat = ap.getLatitude();
        double apLong = ap.getLongitude();
        return Math.sqrt(Math.pow((apLat - latitude),2) +
                Math.pow(((apLong - longitude) * Math.cos((apLat + latitude)/2)),2)) * 300;

    }

    public Aeroport findNearestAirport(double longitude, double latitude)
    {
        int idx_min = 0;
        double distance_min = distance(latitude,longitude,list.get(0));
        for(int i=1; i < list.size(); i++)
        {
            if(distance(latitude,longitude,list.get(i)) < distance_min)
            {
                distance_min = distance(latitude,longitude,list.get(i));
                idx_min = i;
            }
        }
        return  list.get(idx_min);
    }

    public Aeroport findByCode(String iata_code)
    {
        Aeroport ap;
        int index = 0;
        for(int i=0; i < list.size(); i++)
        {
            if(list.get(i).getIATA().equals(iata_code))
            {
                System.out.println(list.get(i).getIATA());
                index = i;
            }
        }
        ap = list.get(index);
        return ap;
    }

    public ArrayList<Aeroport> getList()
    {
        return list;
    }
}
