/**
 * This is the implementation of the Place interfce. This class contains a particular place and also
 * the details of it's position and the roads the leads to it.
 * @author cyrusvillacampa
 */

package com.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Objects;

public class PlaceImpl implements Place {
    Set<Road> incomingRoads;    // contains all roads that reach this place
    String placeName;   // contains the name of the place
    int xPos, yPos; // contains the x and y coordinate of the place
    List<PlaceListener> placeListeners;
    boolean isStart, isEnd;

    // Constructor
    public PlaceImpl(String placeName, int xPos, int yPos) {
        this.placeName = placeName;
        this.xPos = xPos;
        this.yPos = yPos;
        this.placeListeners = new ArrayList<>();
        this.isStart = this.isEnd = false;
    }

    //Add the PlaceListener pl to this place. 
    //Note: A place can have multiple listeners
    public void addListener(PlaceListener pl) {
        placeListeners.add(pl);
    }


    //Delete the PlaceListener pl from this place.
    public void deleteListener(PlaceListener pl) {
        placeListeners.remove(pl);
    }


    //Return a set containing all roads that reach this place
    public Set<Road> toRoads() {
        Set<Road> roads = new LinkedHashSet<>();

        return roads;
    }


    //Return the road from this place to dest, if it exists
    //Returns null, if it does not
    public Road roadTo(Place dest) {
        Road road = null;

        return road;
    }
    

    //Move the position of this place 
    //by (dx,dy) from its current position
    public void moveBy(int dx, int dy) {
        this.xPos += dx;
        this.yPos += dy;
    }
    

    //Return the name of this place 
    public String getName() {
        return placeName;
    }
    

    //Return the X position of this place
    public int getX() {
        return xPos;
    }
    

    //Return the Y position of this place
    public int getY() {
        return yPos;
    }

    public void setStartPlace(boolean val) {
        isStart = val;
    }

    public void setEndPlace(boolean val) {
        isEnd = val;
    }


    //Return true if this place is the starting place for a trip
    public boolean isStartPlace() {
        return isStart;
    }


    //Return true if this place is the ending place for a trip
    public boolean isEndPlace() {
        return isEnd;
    }


    //Return a string containing information about this place 
    //in the form (without the quotes, of course!) :
    //"placeName(xPos,yPos)"  
    public String toString() {
        String str;

        str = new String(this.placeName + "(" + this.xPos + "," + this.yPos + ")");

        return str;
    }

    @Override
    public boolean equals(Object p) {
        if (p == null) {
            return false;
        }

        if (this == p) {
            return true;
        }

        if (!(p instanceof PlaceImpl)) {
            return false;
        }

        return equalsHelper(p);
    }

    private boolean equalsHelper(Object p) {
        PlaceImpl pl = (PlaceImpl)p;

        if (pl.getName().compareTo(this.placeName) != 0 ||
            pl.getX() != this.xPos ||
            pl.getY() != this.yPos) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(placeName, xPos, yPos);
    }
}
