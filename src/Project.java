package src;

import java.io.File;

public class Project {
    static public void main(String args[])
    {
        String csvPath = new File("").getAbsolutePath();
        csvPath += "\\data\\airport-codes_no_comma.csv";
        System.out.println(csvPath);
        World world = new World(csvPath);
    }
}
