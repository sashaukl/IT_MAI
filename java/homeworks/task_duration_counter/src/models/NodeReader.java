package models;

import impls.MyFileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NodeReader implements MyFileReader {

    private NodeConverter nodeConverter = new NodeConverter();


    List<Integer> lastResources;

    @Override
    public List<Integer> getLastResources() {
        return lastResources;
    }

    @Override
    public List<Node> read(String filename) {
        try {
            File myObj = new File(filename );
            Scanner myReader = new Scanner(myObj);
            myReader.nextInt();
            List<Node> list = new ArrayList<Node>();

            int resourcesCount = myReader.nextInt();
            myReader.nextLine();
            lastResources = new ArrayList<Integer>();
            for (String data: myReader.nextLine().trim().split(" +") ){
                lastResources.add(Integer.parseInt(data));
            }
            while (myReader.hasNextLine()) {
                list.add(
                    nodeConverter.convert(myReader.nextLine(), resourcesCount)
                );
            }
            myReader.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}
