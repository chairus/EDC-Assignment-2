/**
 * This is the implementation of the Map interface. This class holds a representation of a map
 * read from a map file.
 * @author cyrusvillacampa
 */

package com.classes;

import java.util.ArrayList;
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

        PlaceImpl place = new PlaceImpl(placeName, xPos, yPos);
        
        if (!places.add(place)) {
            throw new IllegalArgumentException();
        }
        
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

        return -1;
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

        return str;
    }

    /**
     * Checks if the given place name satisfies the requirements set out in the specification.
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

    /* ========== DEBUGGIN PURPOSES ========== */
    public void printPlaces() {
        System.out.println(this.places);
    }

    public void printRoads() {
        System.out.println(this.roads);
    }
}