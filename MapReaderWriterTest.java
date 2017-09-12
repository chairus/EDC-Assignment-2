package com.classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

public class MapReaderWriterTest {
    public static void main(String[] args) {
        MapReaderWriter mprw = new MapReaderWriter();
        Map map = new MapImpl();
        try {
            FileReader fReader = new FileReader("exampleMap.map");
            Reader in = new BufferedReader(fReader);
            mprw.read(in, map);  
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (MapFormatException e) {
            e.getMessage();
            System.exit(1);
        }

        System.out.println(map);

        int totalDistance = map.getTripDistance();
        System.out.print("Total distance from " + map.getStartPlace() + " to " + map.getEndPlace());
        System.out.println(" " + totalDistance);

    }
}