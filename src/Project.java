package src;
import java.io.File;

public class Project{

    static public void main(String args[])
    {
        String csvPath = new File("").getAbsolutePath();
        csvPath += "\\data\\airport-codes_no_comma.csv";
        System.out.println(csvPath);
        World world = new World(csvPath);
        System.out.println("Found " + world.getList().size() + " airports");
        Aeroport paris = world.findNearestAirport(2.316,48.866);
        Aeroport cdg = world.findByCode("CDG");
        double distance = world.distance(2.316,48.866,paris);
        System.out.println(paris);
        System.out.println(distance);
        double distanceCDG = world.distance(2.316,48.866,cdg);
        System.out.println(cdg);
        System.out.println(distanceCDG);
    }
}
