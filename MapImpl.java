/**
 * This is the implementation of the Map interface. This class holds a representation of a map
 * read from a map file.
 * @author cyrusvillacampa
 */

package com.classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


import java.util.LinkedHashSet;

public class MapImpl implements Map {
    private Set<Place> places;  // contains the places read from a map file
    private Set<Road> roads;    // contains the roads read from a map file
    private Place startPlace, endPlace;   // contains the starting and ending place
    private List<MapListener> listeners;    // contains the listeners of this map

    // Constructor
    public MapImpl() {
        this.places = new LinkedHashSet<>();
        this.roads = new LinkedHashSet<>();
        this.startPlace = null;
        this.endPlace = null;
        this.listeners = new ArrayList<>();
    }

    //Add the MapListener ml to this map.
    //Note: A map can have multiple listeners
    public void addListener(MapListener ml) {
        this.listeners.add(ml);
    }
    
    
    //Delete the MapListener ml from this map.
    public void deleteListener(MapListener ml) {
        this.listeners.remove(ml);
    }


    //Create a new Place and add it to this map
    //Return the new place
    //Throws IllegalArgumentException if:
    //  the name is not valid or is the same as that
    //  of an existing place
    //Note: A valid placeName begins with a letter, and is 
    //followed by optional letters, digits, or underscore characters
    public Place newPlace(String placeName, int xPos, int yPos)
        throws IllegalArgumentException {
        
        if (!checkPlaceName(placeName)) {
            throw new IllegalArgumentException();
        }

        Place place = new PlaceImpl(placeName, xPos, yPos);
        
        if (!places.add(place)) {
            throw new IllegalArgumentException();
        }

        // if (!addPlace(place)) {
        //     throw new IllegalArgumentException();
        // }
        
        return place;
    }


    //Remove a place from the map
    //If the place does not exist, returns without error
    public void deletePlace(Place s) {
        places.remove(s);
    }


    //Find and return the Place with the given name
    //If no place exists with given name, return NULL
    public Place findPlace(String placeName) {
        Place p = null;

        for (Place place: places) {
            if (place.getName().compareTo(placeName) == 0) {
                p = place;
                break;
            }
        }

        return p;
    }


    //Return a set containing all the places in this map
    public Set<Place> getPlaces() {
        return this.places;
    }
    

    //Create a new Road and add it to this map
    //Returns the new road.
    //Throws IllegalArgumentException if:
    //  the firstPlace or secondPlace does not exist or
    //  the roadName is invalid or
    //  the length is negative
    //Note: A valid roadName is either the empty string, or starts
    //with a letter and is followed by optional letters and digits
    public Road newRoad(Place from, Place to, String roadName, int length) 
        throws IllegalArgumentException {
        
        if (this.findPlace(from.getName()) == null ||
            this.findPlace(to.getName()) == null ||
            !checkRoadName(roadName) ||
            length < 0) {
            throw new IllegalArgumentException();
        }

        Road r = new RoadImpl(from, to, roadName, length);
        
        if (!roads.add(r)) {
            throw new IllegalArgumentException();
        }

        // if (!addRoad(r)) {
        //     throw new IllegalArgumentException();
        // }

        return r;
    }


    //Remove a road r from the map
    //If the road does not exist, returns without error
    public void deleteRoad(Road r) {
        roads.remove(r);
    }


    //Return a set containing all the roads in this map
    public Set<Road> getRoads() {
        return this.roads;
    }
    

    //Set the place p as the starting place
    //If p==null, unsets the starting place
    //Throws IllegalArgumentException if the place p is not in the map
    public void setStartPlace(Place p)
        throws IllegalArgumentException {
    
        if (p == null) {
            this.startPlace = null;
            return;
        }
        
        Place pl = this.findPlace(p.getName());

        if (pl == null) {
            throw new IllegalArgumentException();
        }

        if ((pl.getX() != p.getX()) || (pl.getY() != p.getY())) {
            throw new IllegalArgumentException();
        }

        this.startPlace = p;
        ((PlaceImpl)pl).setStartPlace(true);
        
    }


    //Return the starting place of this map
    public Place getStartPlace() {
        return this.startPlace;
    }


    //Set the place p as the ending place
    //If p==null, unsets the ending place
    //Throws IllegalArgumentException if the place p is not in the map
    public void setEndPlace(Place p)
        throws IllegalArgumentException {

        if (p == null) {
            this.endPlace = null;
            return;
        }
        
        Place pl = this.findPlace(p.getName());

        if (pl == null) {
            throw new IllegalArgumentException();
        }

        if ((pl.getX() != p.getX()) || (pl.getY() != p.getY())) {
            throw new IllegalArgumentException();
        }

        this.endPlace = p;
        ((PlaceImpl)pl).setEndPlace(true);
    }


    //Return the ending place of this map
    public Place getEndPlace() {
        return this.endPlace;
    }


    //Causes the map to compute the shortest trip between the
    //"start" and "end" places
    //For each road on the shortest route, sets the "isChosen" property
    //to "true".
    //Returns the total distance of the trip.
    //Returns -1, if there is no route from start to end
    public int getTripDistance() {
        int totalDistance;

        if (this.startPlace == null || this.endPlace == null) {
            totalDistance = -1;
            return totalDistance;
        }

        if (isEqualPlace(this.startPlace, this.endPlace)) {
            totalDistance = 0;
            return totalDistance;
        }
        
        // Compute total distance of the trip
        totalDistance = computeTotalDistance();
        return totalDistance;
    }


    //Return a string describing this map
    //Returns a string that contains (in this order):
    //for each place in the map, a line (terminated by \n)
    //  PLACE followed the toString result for that place
    //for each road in the map, a line (terminated by \n)
    //  ROAD followed the toString result for that road
    //if a starting place has been defined, a line containing
    //  START followed the name of the starting-place (terminated by \n)
    //if an ending place has been defined, a line containing
    //  END followed the name of the ending-place (terminated by \n)
    public String toString() {
        String str = "";

        for (Place place: places) {
            str += "PLACE " + place.toString() + "\n";
        }

        for (Road road: roads) {
            str += "ROAD " + road.toString() + "\n";
        }

        if (startPlace != null) {
            str += "START " + startPlace.getName() + "\n";
        }

        if (endPlace != null) {
            str += "END " + endPlace.getName() + "\n";
        }

        return str;
    }

    /* ============================== DEFINED HELPER METHODS ============================== */

    /**
     * Attempts to add a place in the map. If the place already exists in the map the method will
     * return false, otherwise true and adds the place.
     */
    private boolean addPlace(Place place) {
        for (Place p: this.places) {
            if (isEqualPlace(place, p)) {
                return false;
            }
        }

        this.places.add(place);

        return true;
    }

    private boolean isEqualPlace(Place pl1, Place pl2) {
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

    /**
     * Attempts to add a road in the map. If the road already exists in the map the method will
     * return false, otherwise true and adds the road.
     */
    private boolean addRoad(Road road) {
        for (Road r: this.roads) {
            if (isEqualRoad(road, r)) {
                return false;
            }
        }

        this.roads.add(road);

        return true;
    }

    /**
     * This method checks two roads if they are equal.
     * @param r1 - The first road
     * @param r2 - The second road
     * @return boolean - True if the arguments are equal, False otherwise
     */
    private boolean isEqualRoad(Road r1, Road r2) {
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

    /**
     * Checks if the given place name satisfies the requirements set out in the specification.
     * @param placeName - The name of the place
     * @return boolean - True if the name of the place is valid, False otherwise
     */
    private boolean checkPlaceName(String placeName) {
        if (placeName.compareTo(new String("")) == 0) {  // check if the placeName is empty
            return false;
        }

        String regex = "([a-zA-Z]([a-zA-Z]|\\d|_)*)";
        // check if the placeName starts with a letter and followed by zero or more letter, digit
        // or underscore.
        if (!placeName.matches(regex)) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the given road name satisfies the requirements set out in the specification.
     * @param roadName - The name of the road
     * @return boolean - True if the name of the road is valid, False otherwise
     */
    private boolean checkRoadName(String roadName) {
        String regex = "([a-zA-Z]([a-zA-Z]|\\d)*)";
        // check if the roadName starts with a letter and followed by zero or more letters and digits.
        if (roadName != "") {
            if (!roadName.matches(regex)) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method compute the total distance of the trip by first finding the roads the lead from start
     * place to end place by calling another method that finds the minimum spanning tree(MST) of the
     * graph.
     * @return int - The total distance of the trip
     */
    private int computeTotalDistance() {
        int totalDistance = -1;
        List<ArrayList<Place>> setOfPlaces = new ArrayList<ArrayList<Place>>();
        for (Place p: this.places) {    // Initialize each place to belong to it's own set
            ArrayList<Place> tempList = new ArrayList<>();
            tempList.add(p);
            setOfPlaces.add(tempList); 
        }
        
        List<Road> setOfRoads = new ArrayList<>(); // The roads that belong to the MST
        List<Road> sortedRoads = new ArrayList<>(this.roads);
        Collections.sort(sortedRoads, SortByLength);

        MST(sortedRoads, setOfPlaces, setOfRoads);        

        if (setOfPlaces.get(0).contains(this.startPlace) &&
            setOfPlaces.get(0).contains(this.endPlace)) {
            totalDistance = findAndCalculateTrip(setOfRoads);
        }

        return totalDistance;
    }

    /**
     * This method finds a road in the given list of roads where one end of it is equal to the given
     * place argument. This method returns the found road otherwise null.
     * @param rList - The list of roads
     * @param p - The place to search for
     * @return Road - The found road
     */
    private Road findRoadWithPlace(List<Road> rList, Place p) {
        Road foundRoad = null;
        for (Road r: rList) {
            if (r.firstPlace().equals(p) || r.secondPlace().equals(p)) {
                foundRoad = r;
                break;
            }
        }
        
        return foundRoad;
    }

    /**
     * This method is an implementation of Kruskal's algorithm in finding the MST of a bidirected
     * graph. It returns a list of lists where the the first list is also a list of lists where
     * each list is a set of places(there could possibly be more than two sets of places if there
     * is a partition in the graph) and the second list is the set of roads that belong to the 
     * MST set.
     */
    private void MST(List<Road> sortedRoads, List<ArrayList<Place>> setOfPlaces, List<Road> setOfRoads) {
        int numberOfPlaces = this.places.size();

        for (Road r: sortedRoads) {
            findSetAndMergePlace(r, setOfPlaces, setOfRoads);
        }

        // System.out.println("MST roads: " + setOfRoads);
        // System.out.println("Set of places: " + setOfPlaces);
    }

    /**
     * This method merges the two set that the two places belong to if they belong to different
     * sets and returns true else false.
     */
    private void findSetAndMergePlace(Road r, List<ArrayList<Place>> sp, List<Road> sr) {
        // i is the index of the set where p1 belongs to and j is the index of the set where p2
        // belongs to in set s, and k is just a counter
        int i = 0, j = 0, k = 0;
        Place p1 = r.firstPlace();
        Place p2 = r.secondPlace();
        boolean foundSetForP1 = false;
        boolean foundSetForP2 = false;
        
        while (k < sp.size()) {
            if (sp.get(k).contains(p1) && !foundSetForP1) {
                i = k;
                foundSetForP1 = true;
            }

            if (sp.get(k).contains(p2) && !foundSetForP2) {
                j = k;
                foundSetForP2 = true;
            }

            if (foundSetForP1 && foundSetForP2) {
                break;
            }
            k++;
        }

        ArrayList<Place> newSet;
        // Merge two sets s1 and s2, if they belong to different sets and add the road to the 
        // MST set.
        if (i != j) {
            newSet = sp.get(i);
            newSet.addAll(sp.get(j));
            sp.set(i, newSet);
            sp.remove(j);
            sr.add(r);
        }
    }

    /**
     * This method finds the roads that leads from start place to end place and returns the total distance.
     * It uses the depth-first-search(DFS) algorithm to find the roads.
     * @param MSTroad - These are the roads that are in the minimum spanning tree set
     * @return int - The total distance of the trip
     */
    public int findAndCalculateTrip(List<Road> MSTroad) {
        int totalTrip = 0;
        Road road;
        List<Road> exploredRoads = new ArrayList<>();
        List<Road> unexploredRoads = new ArrayList<>(MSTroad);
        List<Road> currentRoads = new ArrayList<>();    // Current roads being explored
        List<Place> currentPlaces = new ArrayList<>();  // Current places being explored

        currentPlaces.add(startPlace);
        // System.out.println("Current places: " + currentPlaces);
        while (!unexploredRoads.isEmpty()) {
            road = findRoadWithPlace(unexploredRoads, currentPlaces.get(currentPlaces.size() - 1));
            // System.out.println("Found road: " + road);
            if (road != null) {
                unexploredRoads.remove(road);
                currentRoads.add(road);
                // System.out.println("Unexplored roads: " + unexploredRoads);
                // Check if one end of the found road is the end place
                if (road.firstPlace().equals(endPlace) || road.secondPlace().equals(endPlace)) {
                    break;
                } else {
                    // Add the other end of the found road into the end of the current places being explored
                    if (road.firstPlace().equals(currentPlaces.get(currentPlaces.size() - 1))) {
                        currentPlaces.add(road.secondPlace());
                    } else {
                        currentPlaces.add(road.firstPlace());
                    }
                }
            } else {   // We have reached the end of the road. There are no more roads that leads to a place
                currentPlaces.remove(currentPlaces.size() - 1);
                exploredRoads.add(currentRoads.remove(currentRoads.size() - 1));
            }
            // System.out.println("Current roads: " + currentRoads);
            // System.out.println("Current places: " + currentPlaces);
        }
        // System.out.println("Exited while loop.");
        // System.out.println("Current places: " + currentPlaces);
        // System.out.println("Current roads: " + currentRoads);

        for (Road r: currentRoads) {
            RoadImpl rImpl = (RoadImpl)r;
            totalTrip += r.length();
            rImpl.isChosen = true;
            this.roads.remove(r);
            this.roads.add(rImpl);
        }

        return totalTrip;
    }

    /**
     * This is used to sort the roads by their length in ascending order.
     */
    private Comparator<Road> SortByLength  = new Comparator<Road>() {
        public int compare(Road a, Road b) {
            return a.length() - b.length();
        }
    };

    /* ========== DEBUGGING PURPOSES ========== */
    public void printPlaces() {
        System.out.println(this.places);
    }

    public void printRoads() {
        System.out.println(this.roads);
    }
}