/**
 * This is the implementation of the MapIo interface. This class reads/writes from/to a map file.
 * @author cyrusvillacampa
 */

package com.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;
import java.util.Set;

public class MapReaderWriter implements MapIo {
    // Constructor
    public MapReaderWriter() {

    }

    //This class handles reading and writing map representations as 
    //described in the practical specification

    //Read the description of a map from the 
    //Reader r, and transfers it to Map, m.
    public void read (Reader r, Map m) throws IOException, MapFormatException {
        BufferedReader buffReader = new BufferedReader(r);
        String line;
        int lineNr = 1;
        String regex = "\\s+";
        while ((line = buffReader.readLine()) != null) {
            String[] strArr = line.split(regex);
            switch (strArr[0].toLowerCase()) {
                case "place":
                    addPlace(strArr, m, lineNr);        
                    break;
                case "road":
                    addRoad(strArr, m, lineNr);
                    break;
                case "start":
                    setStartPlace(strArr, m, lineNr);
                    break;
                case "end":
                    setEndPlace(strArr, m, lineNr);
                    break;
                default:
                    // Ignore comment and blank records
                    if (strArr[0].length() != 0) {
                        if (strArr[0].charAt(0) != '#') {
                            throw new MapFormatException(lineNr, "Invalid keyword.");
                        }
                    }
                    break;
            }
            lineNr += 1;
        }
    }
  
    //Write a representation of the Map, m, to the Writer w.
    public void write(Writer w, Map m) throws IOException {
        BufferedWriter buffWriter = new BufferedWriter(w);
        Set<Place> places = m.getPlaces();
        Set<Road> roads = m.getRoads();

        for (Place p: places) {
            writePlace(p, buffWriter);
        }

        buffWriter.newLine();
        buffWriter.flush();

        for (Road r: roads) {
            writeRoad(r, buffWriter);
        }

        if (m.getStartPlace() != null) {
            writeStartOrEndPlace("start ", m.getStartPlace(), buffWriter);
        }

        if (m.getEndPlace() != null) {
            writeStartOrEndPlace("end ", m.getEndPlace(), buffWriter);
        }
    }

    private void writeStartOrEndPlace(String keyword, Place p, BufferedWriter bfw) throws IOException {
        String line = keyword + p.getName();
        bfw.write(line);
        bfw.newLine();
        bfw.flush();
    }

    private void writePlace(Place p, BufferedWriter bfw) throws IOException {
        String line = "place " + 
                      p.getName() +
                      " " +
                      String.valueOf(p.getX()) +
                      " " +
                      String.valueOf(p.getY());

        bfw.write(line);
        bfw.newLine();
        bfw.flush();
    }

    private void writeRoad(Road r, BufferedWriter bfw) throws IOException {
        String line = "road " + 
                      r.firstPlace().getName() +
                      " " +
                      r.roadName() +
                      " " + 
                      String.valueOf(r.length()) +
                      " " +
                      r.secondPlace().getName();

        bfw.write(line);
        bfw.newLine();
        bfw.flush();
    }

    private void addPlace(String[] str, Map mp, int lineNr) throws MapFormatException {
        try {
            if (str.length != 4) {
                throw new MapFormatException(lineNr, "Missing place information.");
            }
            mp.newPlace(str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]));  
        } catch (IllegalArgumentException e) {
            throw new MapFormatException(lineNr, e.getMessage());
        }
        
    }

    private void addRoad(String[] str, Map mp, int lineNr) throws MapFormatException{
        int len = 0;

        if (str.length != 5) {
            throw new MapFormatException(lineNr, "Missing road information.");
        }

        String roadName = str[2];
        if (roadName.compareToIgnoreCase("-") == 0) {
            roadName = "";
        }

        Place from = mp.findPlace(str[1]);
        Place to = mp.findPlace(str[4]);

        try {
            mp.newRoad(from, to, roadName, len);    
        } catch (IllegalArgumentException e) {
            throw new MapFormatException(lineNr, e.getMessage());
        }
        
    }

    private void setStartPlace(String[] str, Map mp, int lineNr) throws MapFormatException {
        if (str.length != 2) {
            throw new MapFormatException(lineNr, "Missing start place information.");
        }

        try {
            Place startPlace = null;
            if (str[1].compareToIgnoreCase("null") != 0) {
                startPlace = mp.findPlace(str[1]);
                if (startPlace == null) {
                    startPlace = ((MapImpl)mp).new PlaceImpl(str[1], 0, 0);
                }
            }
            mp.setStartPlace(startPlace);
        } catch (IllegalArgumentException e) {
            throw new MapFormatException(lineNr, e.getMessage());
        }
    }

    private void setEndPlace(String[] str, Map mp, int lineNr) throws MapFormatException {
        if (str.length != 2) {
            throw new MapFormatException(lineNr, "Missing end place information.");
        }

        try {
            Place endPlace = null;
            if (str[1].compareToIgnoreCase("null") != 0) {
                endPlace = mp.findPlace(str[1]);
                if (endPlace == null) {
                    endPlace = ((MapImpl)mp).new PlaceImpl(str[1], 0, 0);
                }
            }
            mp.setEndPlace(endPlace);
        } catch (IllegalArgumentException e) {
            throw new MapFormatException(lineNr, e.getMessage());
        }
    }
}