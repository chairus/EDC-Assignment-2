package com.classes;

public class RoadImpl implements Road {
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
        Place place = null;

        return place;
    }
    

    //Return the second place of this road
    //Note: The second place of a road is the place whose name
    //comes LATER in the alphabet.
    public Place secondPlace() {
        Place place = null;

        return place;
    }
    

    //Return true if this road is chosen as part of the current trip
    public boolean isChosen() {
        return false;
    }


    //Return the name of this road
    public String roadName() {
        String str = "";

        return str;
    }
    

    //Return the length of this road
    public int length() {
        int len = 0;

        return len;
    }

    
    //Return a string containing information about this road 
    //in the form (without quotes, of course!):
    //"firstPlace(roadName:length)secondPlace"
    public String toString() {
        String str = "";

        return str;
    }
}
