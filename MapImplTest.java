/**
 * This is a testing frame for the implemented methods in the Map interface.
 * @author cyrusvillacampa
 */


package com.classes;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class MapImplTest {
    static int numberOfTestCases = 0;
    static int numberOfTestCasesPassed = 0;

    public static void main(String[] args) {
        MapImpl map = new MapImpl();

        /**
         * ==============================================
         * TEST newPlace() METHOD
         * ==============================================
         */
        System.out.println("==============================================");
        System.out.println("\t TEST newPlace() method");
        System.out.println("==============================================");
        testNewPlaceMethod(map);
        System.out.print("Current places stored in the map: ");
        map.printPlaces();
        /**
         * ==============================================
         * TEST deletePlace and findPlace() METHOD
         * ==============================================
         */
        System.out.println("==============================================");
        System.out.println("\t TEST deletePlace() and findPlace() method");
        System.out.println("==============================================");
        testDeleteAndFindPlaceMethod(map);
        System.out.print("Current places stored in the map: ");
        map.printPlaces();
        /**
         * ==============================================
         * TEST newRoad() METHOD
         * ==============================================
         */
        System.out.println("==============================================");
        System.out.println("\t TEST newRoad() method");
        System.out.println("==============================================");
        testNewRoadMethod(map);
        System.out.print("Current roads stored in the map: ");
        map.printRoads();
        /**
         * ==============================================
         * TEST deleteRoad() METHOD
         * ==============================================
         */
        System.out.println("==============================================");
        System.out.println("\t TEST deleteRoad() method");
        System.out.println("==============================================");
        testDeleteRoadMethod(map);
        System.out.print("Current roads stored in the map: ");
        map.printRoads();
        /**
         * ==============================================
         * TEST setStartPlace() METHOD
         * ==============================================
         */
        System.out.println("==============================================");
        System.out.println("\t TEST setStartPlace() method");
        System.out.println("==============================================");
        testSetStartPlaceMethod(map);
        System.out.print("Current places stored in the map: ");
        map.printPlaces();
        /**
         * ==============================================
         * TEST addListener() and deleteListener METHOD
         * ==============================================
         */
        // System.out.println("==============================================");
        // System.out.println("TEST addListener() and deleteListener() method");
        // System.out.println("==============================================");
        // testSetStartPlaceMethod(map);
        // System.out.print("Current places stored in the map: ");
        Place rundle = new PlaceImpl("Rundle", 11, 22);
        map.setEndPlace(rundle);
        System.out.println("Start place:" + map.getStartPlace());
        System.out.println("End place: " + map.getEndPlace());

        int totalDistance = map.getTripDistance();
        System.out.println("Total distance of the trip: " + totalDistance);

        
        // ArrayList<Place> tempPlaces = new ArrayList<>(map.getPlaces());
        // String str1 = "Adelaide";
        // String str2 = "Ade";
        // System.out.println(str1.compareTo(str2));
        // System.out.println(tempPlaces.indexOf(new PlaceImpl("Ade",12, 10)));
        // System.out.println(tempPlaces.indexOf(new PlaceImpl("Arndale",93, 21)));

        // System.out.println(map);

        printTestCasesResult(numberOfTestCases, numberOfTestCasesPassed);
    }

    private static void testNewPlaceMethod(Map map) {
        Place place;
        boolean result;
        
        System.out.println("Adding a valid place: Adelaide 12 10");
        numberOfTestCases += 1;
        place = addPlace(map, "Adelaide", 12, 10);
        System.out.print("Checking if place has been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("Adelaide", 12, 10));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid place: Arndale 93 21");
        numberOfTestCases += 1;
        place = addPlace(map, "Arndale", 93, 21);
        System.out.print("Checking if place has been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("Arndale", 93, 21));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid place: A 23 92");
        numberOfTestCases += 1;
        place = addPlace(map, "A", 23, 92);
        System.out.print("Checking if place has been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("A", 23, 92));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid place: A_ 23 92");
        numberOfTestCases += 1;
        place = addPlace(map, "A_", 23, 92);
        System.out.print("Checking if place has been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("A_", 23, 92));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
        System.out.println("Adding an invalid place: Ade+laide 23 92");
        numberOfTestCases += 1;
        place = addPlace(map, "Ade+laide", 23, 92);
        System.out.print("Checking if place has not been successfully added...");
        result = isEqualPlace(place, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
        System.out.println("Adding an invalid place: 2Adelaide 12 10");
        numberOfTestCases += 1;
        place = addPlace(map, "2Adelaide", 12, 10);
        System.out.print("Checking if place has not been successfully added...");
        result = isEqualPlace(place, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
        System.out.println("Adding a valid and existing place: A 23 92");
        numberOfTestCases += 1;
        place = addPlace(map, "A", 23, 92);
        System.out.print("Checking if place has not been successfully added...");
        result = isEqualPlace(place, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid place: Costco 23 30");
        numberOfTestCases += 1;
        place = addPlace(map, "Costco", 23, 30);
        System.out.print("Checking if place has been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("Costco", 23, 30));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid place: Rundle 11 22");
        numberOfTestCases += 1;
        place = addPlace(map, "Rundle", 11, 22);
        System.out.print("Checking if place has been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("Rundle", 11, 22));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
    }

    private static void testDeleteAndFindPlaceMethod(Map map) {
        Place place;
        boolean result;
        
        System.out.println("Removing the place: A, 23, 92");
        numberOfTestCases += 1;
        map.deletePlace(new PlaceImpl("A", 23, 92));
        System.out.print("Checking if place has been successfully removed...");
        place = findPlace(map, "A");
        result = isEqualPlace(place, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
    }

    private static void testNewRoadMethod(Map mp) {
        Road road;
        Place p1,p2;
        boolean result;

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, RoadToArndale, 2");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = addRoad(mp, p1, p2, "RoadToArndale", 2);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "RoadToArndale", 2));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Costco 23 30, Arndale 93 21, RoadToCostco, 3");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Arndale", 93, 21);
        p2 = new PlaceImpl("Costco", 23, 30);
        road = addRoad(mp, p1, p2, "RoadToCostco", 3);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "RoadToCostco", 3));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Costco 23 30, Rundle 11 22, RoadToRundle, 5");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Costco", 23, 30);
        p2 = new PlaceImpl("Rundle", 11, 22);
        road = addRoad(mp, p1, p2, "RoadToRundle", 5);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "RoadToRundle", 5));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Costco 23 30, RoadToCostco2, 10");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Costco", 23, 30);
        road = addRoad(mp, p1, p2, "RoadToCostco2", 10);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "RoadToCostco2", 10));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, LeHunte, 17");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = addRoad(mp, p1, p2, "LeHunte", 17);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "LeHunte", 17));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, [empty string], 32");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "", 32);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "", 32));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, LeHunte32lm, 21");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "LeHunte32lm", 21);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "LeHunte32lm", 21));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, a, 10");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "a", 10);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "a", 10));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a road that has a negative length: Adelaide 12 10, Arndale 93 21, b, -50");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "b", -50);
        System.out.print("Checking if road has not been successfully added...");
        result = isEqualRoad(road, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a road with invalid road name: Adelaide 12 10, Arndale 93 21, 9Camaro, 50");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "9Camaro", 50);
        System.out.print("Checking if road has not been successfully added...");
        result = isEqualRoad(road, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a road with invalid road name: Adelaide 12 10, Arndale 93 21, Cam+_r56o, 50");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "Cam+_r56o", 50);
        System.out.print("Checking if road has not been successfully added...");
        result = isEqualRoad(road, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a road that has a place that doesn't exist: Melbourne 123 54, Arndale 93 21, Chicko, 13");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Melbourne", 123, 54);
        road = addRoad(mp, p1, p2, "Chicko", 13);
        System.out.print("Checking if road has not been successfully added...");
        result = isEqualRoad(road, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a road that has both places that doesn't exist: Melbourne 123 54, Perth 93 21, Chicko, 13");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Melbourne", 123, 54);
        p2 = new PlaceImpl("Perth", 93, 21);
        road = addRoad(mp, p1, p2, "Chicko", 13);
        System.out.print("Checking if road has not been successfully added...");
        result = isEqualRoad(road, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
    }

    private static void testDeleteRoadMethod(Map mp) {
        Road road;
        Place p1,p2;
        boolean result;
        Set<Road> set = mp.getRoads();

        System.out.println("Deleting road: Adelaide 12 10, Arndale 93 21, LeHunte, 17");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = new RoadImpl(p1, p2, "LeHunte", 17);
        mp.deleteRoad(road);
        System.out.print("Checking if road has been successfully removed...");
        result = checkRoadExistInSet(mp.getRoads(), road);
        if (!result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(!result);

        System.out.println("Deleting road: Adelaide 12 10, Arndale 93 21, LeHunte32lm, 21");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = new RoadImpl(p1, p2, "LeHunte32lm", 21);
        mp.deleteRoad(road);
        System.out.print("Checking if road has been successfully removed...");
        result = checkRoadExistInSet(mp.getRoads(), road);
        if (!result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(!result);

        System.out.println("Deleting road: Adelaide 12 10, Arndale 93 21, a, 10");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = new RoadImpl(p1, p2, "a", 10);
        mp.deleteRoad(road);
        System.out.print("Checking if road has been successfully removed...");
        result = checkRoadExistInSet(mp.getRoads(), road);
        if (!result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(!result);

        System.out.println("Deleting road: Adelaide 12 10, Arndale 93 21, [empty], 32");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = new RoadImpl(p1, p2, "", 32);
        mp.deleteRoad(road);
        System.out.print("Checking if road has been successfully removed...");
        result = checkRoadExistInSet(mp.getRoads(), road);
        if (!result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(!result);

        System.out.println("Deleting road: Adelaide 12 10, Arndale 93 21, Piccolo, 38");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = new RoadImpl(p1, p2, "Piccolo", 38);
        mp.deleteRoad(road);
        System.out.print("Checking if no road has been removed...");
        result = set.containsAll(mp.getRoads());
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
    }

    private static void testSetStartPlaceMethod(Map mp) {
        Place p;
        boolean result;

        System.out.println("Setting start place: Adelaide 12 10");
        numberOfTestCases += 1;
        p = setStartPlace(mp, new PlaceImpl("Adelaide", 12, 10));
        System.out.print("Checking if start place has been set...");
        result = isEqualPlace(p, mp.getStartPlace());
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Setting start place: null");
        numberOfTestCases += 1;
        p = setStartPlace(mp, null);
        System.out.print("Checking if start place has been unset...");
        result = isEqualPlace(p, mp.getStartPlace());
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Setting start place: Adelaide 12 10");
        numberOfTestCases += 1;
        p = setStartPlace(mp, new PlaceImpl("Adelaide", 12, 10));
        System.out.print("Checking if start place has been set...");
        result = isEqualPlace(p, mp.getStartPlace());
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Setting start place that doesn't exist in the map: Adelaide 20 10");
        numberOfTestCases += 1;
        p = setStartPlace(mp, new PlaceImpl("Adelaide", 20, 10));
        System.out.print("Checking if start place has not been set...");
        result = isEqualPlace(p, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Setting start place that doesn't exist in the map: Churchill 20 10");
        numberOfTestCases += 1;
        p = setStartPlace(mp, new PlaceImpl("Churchill", 20, 10));
        System.out.print("Checking if start place has not been set...");
        result = isEqualPlace(p, null);
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);
    }

    private static Place setStartPlace(Map mp, Place p) {
        Place pl;

        try {
            mp.setStartPlace(p);
            pl = p;
        } catch (IllegalArgumentException e) {
            pl = null;
        }

        return pl;
    }

    private static boolean checkRoadExistInSet(Set<Road> rds, Road rd) {
        for (Road road: rds) {
            if (isEqualRoad(road, rd)) {
                return true;
            }
        }

        return false;
    }

    private static Road addRoad(Map mp, Place p1, Place p2, String name, int length) {
        Road r;
        try {
            r = mp.newRoad(p1, p2, name, length);
        } catch (IllegalArgumentException e) {
            r = null;
        }
        return r;
    }

    private static Place findPlace(Map mp, String name) {
        Place p;
        try {
            p = mp.findPlace(name);
        } catch (IllegalArgumentException e) {
            p = null;
        }
        return p;
    }

    private static Place addPlace(Map mp, String placeName, int xpos, int ypos) {
        Place p;
        try {
            p = mp.newPlace(placeName, xpos, ypos);
        } catch (IllegalArgumentException e) {
            p = null;
        }
        return p;
    }

    private static boolean isEqualRoad(Road r1, Road r2) {
        if (r1 == r2) {
            return true;
        }

        if ((r1 == null && r2 != null) || (r1 != null && r2 == null)) {
            return false;
        }

        if (r1.roadName().compareTo(r2.roadName()) != 0) {
            return false;
        }

        if (!isEqualPlace(r1.firstPlace(), r2.firstPlace())) {
            return false;
        }

        if (!isEqualPlace(r1.secondPlace(), r2.secondPlace())) {
            return false;
        }

        if (r1.length() != r2.length()) {
            return false;
        }
        
        return true;
    }

    private static boolean isEqualPlace(Place pl1, Place pl2) {
        if (pl1 == pl2) {
            return true;
        }

        if ((pl1 == null && pl2 != null) || pl1 != null && pl2 == null) {
            return false;
        }

        if (pl1.getName().compareTo(pl2.getName()) != 0) {
            return false;
        }

        if (!(pl1.getX() == pl2.getX())) {
            return false;
        }

        if (!(pl1.getY() == pl2.getY())) {
            return false;
        }

        return true;
    }

    private static void printResult(boolean res) {
        if (res) {
            System.out.println("SUCCESS");
            return;
        }
        System.out.println("FAILED");
    }

    private static void printTestCasesResult(int total, int passed) {
        System.out.println("Number of test cases: " + total);
        System.out.println("Test cases passed: " + passed);
        System.out.println("Test cases failed: " + (total - passed));
    }
}