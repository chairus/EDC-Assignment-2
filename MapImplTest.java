/**
 * This is a testing frame for the implemented methods in the Map interface.
 * @author cyrusvillacampa
 */


package com.classes;

public class MapImplTest {
    public static void main(String[] args) {
        int numberOfTestCases = 0;
        int numberOfTestCasesPassed = 0;
        Map map = new MapImpl();
        Place place;
        boolean result;

        /**
         * ==============================================
         * TEST newPlace() METHOD
         * ==============================================
         */
        System.out.println("==============================================");
        System.out.println("\t TEST newplace() method");
        System.out.println("==============================================");
        System.out.println("Adding a valid place: Adelaide 12 10");
        numberOfTestCases += 1;
        place = addPlace(map, "Adelaide", 12, 10);
        System.out.print("Checking if place has been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("Adelaide", 12, 10));
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
        result = isEqualPlace(place, new PlaceImpl("Ade+laide", 23, 92));
        if (!result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(!result);
        System.out.println("Adding an invalid place: 2Adelaide 12 10");
        numberOfTestCases += 1;
        place = addPlace(map, "2Adelaide", 12, 10);
        System.out.print("Checking if place has not been successfully added...");
        result = isEqualPlace(place, new PlaceImpl("2Adelaide", 12, 10));
        if (!result) {
            numberOfTestCasesPassed += 1;
        }
        printResult(!result);




        printTestCasesResult(numberOfTestCases, numberOfTestCasesPassed);
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