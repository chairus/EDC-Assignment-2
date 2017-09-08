/**
 * This is a testing frame for the implemented methods in the Map interface.
 * @author cyrusvillacampa
 */


package com.classes;

import java.util.Set;

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

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, LeHunte, 50");
        numberOfTestCases += 1;
        p1 = new PlaceImpl("Adelaide", 12, 10);
        p2 = new PlaceImpl("Arndale", 93, 21);
        road = addRoad(mp, p1, p2, "LeHunte", 50);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "LeHunte", 50));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, [empty string], 50");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "", 50);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "", 50));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, LeHunte32lm, 50");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "LeHunte32lm", 50);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "LeHunte32lm", 50));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a valid road: Adelaide 12 10, Arndale 93 21, a, 50");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "a", 50);
        System.out.print("Checking if road has been successfully added...");
        result = isEqualRoad(road, new RoadImpl(p1, p2, "a", 50));
        if (result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(result);

        System.out.println("Adding a road that has a negative length: Adelaide 12 10, Arndale 93 21, b, -50");
        numberOfTestCases += 1;
        road = addRoad(mp, p1, p2, "b", -50);
        System.out.print("Checking if road has been successfully added...");
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