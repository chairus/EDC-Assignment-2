package com.classes;

import java.util.Objects;

public class RoadImpl implements Road {
    Place firstPlace, secondPlace;
    String roadName;
    int length; // kilometres
    boolean isChosen;

    public RoadImpl(Place place1, Place place2, String roadName, int length) {
        storePlaces(place1, place2);
        this.roadName = roadName;
        this.length = length;
    }

    //Add the RoadListener rl to this place.
    //Note: A road can have multiple listeners
    public void addListener(RoadListener rl) {

    }


    //Delete the RoadListener rl from this place.
    public void deleteListener(RoadListener rl) {

    }


    //Return the first place of this road
    //Note: The first place of a road is the place whose name
    //comes EARLIER in the alphabet.
    public Place firstPlace() {
        return this.firstPlace;
    }
    

    //Return the second place of this road
    //Note: The second place of a road is the place whose name
    //comes LATER in the alphabet.
    public Place secondPlace() {
        return this.secondPlace;
    }
    

    //Return true if this road is chosen as part of the current trip
    public boolean isChosen() {
        return this.isChosen;
    }


    //Return the name of this road
    public String roadName() {
        return this.roadName;
    }
    

    //Return the length of this road
    public int length() {
        return this.length;
    }

    
    //Return a string containing information about this road 
    //in the form (without quotes, of course!):
    //"firstPlace(roadName:length)secondPlace"
    public String toString() {
        String str = new String(this.firstPlace.getName() +
                        "(" + this.roadName + ":" + this.length + ")" + 
                                this.secondPlace.getName());

        return str;
    }

    @Override
    public boolean equals(Object r) {
        if (r == null) {
            return false;
        }

        if (this == r) {
            return true;
        }

        if (!(r instanceof RoadImpl)) {
            return false;
        }

        return equalsHelper(r);
    }

    private boolean equalsHelper(Object r) {
        RoadImpl rd = (RoadImpl)r;

        if (rd.roadName().compareTo(this.roadName) != 0 &&
            placesAreEqual(rd.firstPlace(), rd.secondPlace())) {
            return false;
        }

        return true;
    }

    private boolean placesAreEqual(Place p1, Place p2) {
        if (p1.getName().compareTo(p2.getName()) != 0 &&
            p1.getX() != p2.getX() &&
            p1.getY() != p2.getY()) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.roadName, this.firstPlace, this.secondPlace, this.length);
    }

    private void storePlaces(Place p1, Place p2) {
        if (p1.getName().compareTo(p2.getName()) < 0) {
            this.firstPlace = p1;
            this.secondPlace = p2;
        } else {
            this.firstPlace = p2;
            this.secondPlace = p1;
        }
    }
}
