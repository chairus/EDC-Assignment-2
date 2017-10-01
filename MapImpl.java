/**
 * This is the implementation of the Map interface. This class holds a representation of a map
 * read from a map file.
 * @author cyrusvillacampa
 */

package com.classes;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
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
            throw new IllegalArgumentException("Invalid place name.");
        }

        if (findPlace(placeName) != null) {
            throw new IllegalArgumentException("Place already exist.");
        }

        Place place = new PlaceImpl(placeName, xPos, yPos);
        
        places.add(place);
        
        // Invoke the method on each listener
        for (MapListener ml: listeners) {
            ml.placesChanged();
        }

        return place;
    }


    //Remove a place from the map
    //If the place does not exist, returns without error
    public void deletePlace(Place s) {
        if (places.remove(s)) {
            // Invoke the method on each listener
            for (MapListener ml: listeners) {
                ml.placesChanged();
            }
            // And remove all roads associated with this place
            ArrayList<Road> roadsToBeRemoved = new ArrayList<>();
            Iterator it = roads.iterator();
            while (it.hasNext()) {
                Road road = (Road)it.next();
                if (road.firstPlace().equals(s) || road.secondPlace().equals(s)) {
                    roadsToBeRemoved.add(road);
                }
            }
            while (!roadsToBeRemoved.isEmpty()) {
                roads.remove(roadsToBeRemoved.get(0));
                roadsToBeRemoved.remove(0);
            }
        }
    }


    //Find and return the Place with the given name
    //If no place exists with given name, return NULL
    public Place findPlace(String placeName) {
        Place p = null;

        for (Place place: places) {
            if (place.getName().compareToIgnoreCase(placeName) == 0) {
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

        if (from == null || to == null) {
            throw new IllegalArgumentException("Place does not exist.");
        }

        if (this.findPlace(from.getName()) == null ||
            this.findPlace(to.getName()) == null) {
            throw new IllegalArgumentException("Place does not exist.");
        }

        if (!checkRoadName(roadName)) {
            throw new IllegalArgumentException("Invalid road name.");
        }

        if (length < 0) {
            throw new IllegalArgumentException("Negative road length.");
        }

        Road r = new RoadImpl(from, to, roadName, length);
        roads.add(r);

        // Invoke the method on each listener
        for (MapListener ml: listeners) {
            ml.roadsChanged();
        }

        return r;
    }


    //Remove a road r from the map
    //If the road does not exist, returns without error
    public void deleteRoad(Road r) {
        if (roads.remove(r)) {
            // Invoke the method on each listener
            for (MapListener ml: listeners) {
                ml.roadsChanged();
            }
        }
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
            if (this.startPlace != null) {
                PlaceImpl placeImpl = (PlaceImpl)this.findPlace(this.startPlace.getName());
                placeImpl.setStartPlace(false);
            }
            this.startPlace = null;
            return;
        }
        
        Place pl = this.findPlace(p.getName());

        if (pl == null) {
            throw new IllegalArgumentException("Place not found.");
        }

        if ((pl.getX() != p.getX()) || (pl.getY() != p.getY())) {
            throw new IllegalArgumentException("Place not found.");
        }

        this.startPlace = p;
        ((PlaceImpl)pl).setStartPlace(true);
        
        // Invoke the method on each listener
        for (MapListener ml: listeners) {
            ml.otherChanged();
        }
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
            if (this.endPlace != null) {
                PlaceImpl placeImpl = (PlaceImpl)this.findPlace(this.endPlace.getName());
                placeImpl.setEndPlace(false);
            }
            this.endPlace = null;
            return;
        }
        
        Place pl = this.findPlace(p.getName());

        if (pl == null) {
            throw new IllegalArgumentException("Place not found.");
        }

        if ((pl.getX() != p.getX()) || (pl.getY() != p.getY())) {
            throw new IllegalArgumentException("Place not found.");
        }

        this.endPlace = p;
        ((PlaceImpl)pl).setEndPlace(true);

        // Invoke the method on each listener
        for (MapListener ml: listeners) {
            ml.otherChanged();
        }
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
        if (this.startPlace == null || this.endPlace == null) {
            return -1;
        }

        if (this.startPlace.equals(this.endPlace)) {
            return 0;
        }
        
        // Compute total distance of the trip
        return computeTotalDistance();
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

    /* ==================================================================================== */
    /* ============================ PLACE IMPLEMENTATION(START) =========================== */
    /* ==================================================================================== */

    /**
     * This is the implementation of the Place interfce. This class contains a particular place and also
     * the details of it's position and the roads the leads to it.
     * @author cyrusvillacampa
     */
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
            Set<Road> toRoads = new LinkedHashSet<>();
            
            for (Road r: roads) {
                if (r.firstPlace().equals(this) || r.secondPlace().equals(this)) {
                    toRoads.add(r);
                }
            }
    
            return toRoads;
        }
    
    
        //Return the road from this place to dest, if it exists
        //Returns null, if it does not
        public Road roadTo(Place dest) {
            Road road = null;
    
            for (Road r: roads) {
                if (r.firstPlace().equals(this) || r.secondPlace().equals(this)) {
                    if (r.firstPlace().equals(dest) || r.secondPlace().equals(dest)) {
                        road = r;
                    }
                }
            }

            return road;
        }
        
    
        //Move the position of this place 
        //by (dx,dy) from its current position
        public void moveBy(int dx, int dy) {
            this.xPos += dx;
            this.yPos += dy;

            // Invoke the method on each listener
            for (PlaceListener pl: placeListeners) {
                pl.placeChanged();
            }
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

            // Invoke the method on each listener
            for (PlaceListener pl: placeListeners) {
                pl.placeChanged();
            }
        }
    
        public void setEndPlace(boolean val) {
            isEnd = val;

            // Invoke the method on each listener
            for (PlaceListener pl: placeListeners) {
                pl.placeChanged();
            }
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

    /* ==================================================================================== */
    /* ============================ PLACE IMPLEMENTATION(END) ============================= */
    /* ==================================================================================== */

    /* ==================================================================================== */
    /* ============================ ROAD IMPLEMENTATION(START) ============================ */
    /* ==================================================================================== */

    /**
     * This is the implementation of the Road interfce. This class contains a particular road, two
     * places(this road connects them together) and also the details of it's length and road name.
     * @author cyrusvillacampa
     */
    public class RoadImpl implements Road {
        private Place firstPlace, secondPlace;
        private String roadName;
        private int length; // kilometres
        public boolean isChosen;
        private List<RoadListener> roadListeners;
    
        public RoadImpl(Place place1, Place place2, String roadName, int length) {
            storePlaces(place1, place2);
            this.roadName = roadName;
            this.length = length;
            this.isChosen = false;
            this.roadListeners = new ArrayList<>();
        }
    
        //Add the RoadListener rl to this place.
        //Note: A road can have multiple listeners
        public void addListener(RoadListener rl) {
            roadListeners.add(rl);
        }
    
    
        //Delete the RoadListener rl from this place.
        public void deleteListener(RoadListener rl) {
            roadListeners.remove(rl);
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
        
        public void setIsChosen(boolean val) {
            isChosen = val;

            // Invoke the method on each listener
            for (RoadListener rl: roadListeners) {
                rl.roadChanged();
            }
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
    
            if (rd.roadName().compareTo(this.roadName) != 0 ||
                !rd.firstPlace().equals(this.firstPlace) ||
                !rd.secondPlace().equals(this.secondPlace) ||
                rd.length() != this.length) {
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
    /* ==================================================================================== */
    /* ============================= ROAD IMPLEMENTATION(END) ============================= */
    /* ==================================================================================== */


    /* ==================================================================================== */
    /* ========================== DEFINED HELPER METHODS(START) =========================== */
    /* ==================================================================================== */

    /**
     * Checks if the given place name satisfies the requirements set out in the specification.
     * @param placeName - The name of the place
     * @return boolean - True if the name of the place is valid, False otherwise
     */
    private boolean checkPlaceName(String placeName) {
        if (placeName.compareToIgnoreCase(new String("")) == 0) {  // check if the placeName is empty
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
        // Places where their trip distance leading to it has already been calculated
        List<Place> finishedPlaces = new ArrayList<>();
        // Roads that are in the SSSP set
        List<Road> roadsInSSSPSet = new ArrayList<>();

        SSSP(finishedPlaces, roadsInSSSPSet);

        return findAndCalculateTrip(roadsInSSSPSet);
    }

    /**
     * This method finds the roads that leads from start place to end place and returns the total distance.
     * It uses the depth-first-search(DFS) algorithm to find the roads.
     * @param roadsInSSSPSet - These are the roads that are in the single-source-shortest-path set
     * @return int - The total distance of the trip
     */
    public int findAndCalculateTrip(List<Road> roadsInSSSPSet) {
        int totalTrip = 0;
        List<Road> foundRoads;
        List<Road> exploredRoads = new ArrayList<>();
        List<Road> unexploredRoads = new ArrayList<>(roadsInSSSPSet);
        List<Road> currentRoads = new ArrayList<>();    // Current roads being explored
        List<Place> currentPlaces = new ArrayList<>();  // Current places being explored

        currentPlaces.add(startPlace);
        while (!unexploredRoads.isEmpty() || !currentRoads.isEmpty()) {
            foundRoads = findRoadsWithPlace(unexploredRoads, currentPlaces.get(currentPlaces.size() - 1));
            if (foundRoads.size() > 0) {
                Road road = foundRoads.get(0);
                // System.out.println("Found road: " + road);
                unexploredRoads.remove(road);
                currentRoads.add(road);
                // System.out.println("Unexplored roads: " + unexploredRoads);
                // Check if one end of the found road is the end place
                if (road.firstPlace().equals(endPlace) || road.secondPlace().equals(endPlace)) {
                    break;
                } else {
                    // Add the other end of the found road at the end of the current places being explored
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

        for (Road r: currentRoads) {
            RoadImpl rImpl = (RoadImpl)r;
            totalTrip += r.length();
            rImpl.setIsChosen(true);
            this.roads.remove(r);
            this.roads.add(rImpl);
        }

        if (totalTrip == 0) {
            totalTrip = -1;
        }

        // Iterator it = currentRoads.iterator();
        // while (it.hasNext()) {
        //     RoadImpl r = (RoadImpl)it.next();
        //     totalTrip += r.length();
        //     r.isChosen = true;
        // }

        return totalTrip;
    }

    /**
     * This method invokes the methods to run the Dijkstra's algorithm.
     */
    private void SSSP (List<Place> finishedPlaces, List<Road> roadsInSSSPSet) {
        // A priority queue that stores a pair (place, estimatedDistance).
        Queue<PlaceNode> placeNodePriorityQueue = new PriorityQueue<>(valueComparator);

        initializeSingleSource(placeNodePriorityQueue, this.startPlace);
        runSSSPAlgorithm(placeNodePriorityQueue, finishedPlaces, roadsInSSSPSet);
    }

    /**
     * 
     */
    private void initializeSingleSource(Queue<PlaceNode> priorityQueue, Place sourceNode) {
        for (Place p: this.places) {
            if (p.equals(sourceNode)) {
                priorityQueue.offer(new PlaceNode(sourceNode, 0));
                continue;
            }
            priorityQueue.offer(new PlaceNode(p, Integer.MAX_VALUE));
        }
    }

    /**
     * This method 
     */
    private void runSSSPAlgorithm(Queue<PlaceNode> priorityQueue, 
                                  List<Place> finishedPlaces,
                                  List<Road> roadsInSSSPSet) {
        // Stores the nodes where their shortest path from the source has already been determined.
        List<PlaceNode> finishedPlaceNodes = new ArrayList<>();
        // System.out.println("Initialized nodes: " + priorityQueue);
        
        while (!priorityQueue.isEmpty()) {
            PlaceNode v = priorityQueue.poll();
            // System.out.println("Smallest estimate: " + v);
            finishedPlaces.add(v.getPlaceNode());
            finishedPlaceNodes.add(v);
            relaxEdge(v,priorityQueue, finishedPlaces, roadsInSSSPSet);
            // System.out.println("Relaxed nodes: " + priorityQueue);
            v = priorityQueue.peek();   // Check out the next node that has the smallest path estimate
            if (v != null) {
                if (v.getPlaceNodeValue() < Integer.MAX_VALUE) {
                    List<Road> foundRoads = findRoadsWithPlace(new ArrayList<Road>(this.roads), v.getPlaceNode());
                    
                    // Find out the road that connects the node that has the next smallest path estimate
                    // and a node that has its path estimate already determined.
                    for (Road r: foundRoads) {
                        PlaceNode otherNode = new PlaceNode(r.firstPlace(), 0);
                        
                        if (r.firstPlace().equals(v.getPlaceNode())) {
                            otherNode = new PlaceNode(r.secondPlace(), 0);
                        }
                        
                        if (finishedPlaceNodes.contains(otherNode)) {
                            PlaceNode pn = finishedPlaceNodes.get(finishedPlaceNodes.indexOf(otherNode));
                            if ((pn.getPlaceNodeValue() + r.length()) == v.getPlaceNodeValue()) {
                                roadsInSSSPSet.add(r);
                                break;
                            }
                        }
                    }
                } else {
                    // This means that the graph has partitions and that the algorithm has already found
                    // the shortest path in the partition
                    break;
                }   
            }
            
            // System.out.println("Roads in SSSP set: " + roadsInSSSPSet);
        }
    }

    /**
     * This method relaxes the edges and returns a boolean value if all the nodes adjacent to the
     * current node is already in the SSSP set.
     * @param pn - 
     * @param pq - Priority queue
     * @param finishedPlaces - A set that contains the places where a path distance to them has already determined 
     * @param roadsInSSSPSet - A set that contains the roads that are in the single-source-shortest-path set
     */
    private void relaxEdge(PlaceNode pn, 
                           Queue<PlaceNode> pq, 
                           List<Place> finishedPlaces,
                           List<Road> roadsInSSSPSet) {
        List<Road> foundRoads = findRoadsWithPlace(new ArrayList<Road>(this.roads), pn.getPlaceNode());

        for (Road r: foundRoads) {
            PlaceNode adjPlace = new PlaceNode(r.firstPlace(), 0);
            if (r.firstPlace().equals(pn.getPlaceNode())) {
                adjPlace = new PlaceNode(r.secondPlace(), 0);
            }

            // Check if the place is already in the current SSSP set, if not then relax
            if (!finishedPlaces.contains(adjPlace.getPlaceNode())) {
                adjPlace.setPlaceNodeValue(r.length() + pn.getPlaceNodeValue());
                
                for (PlaceNode u: pq) {
                    if (u.equals(adjPlace)) {
                        if (u.getPlaceNodeValue() >= adjPlace.getPlaceNodeValue()) {
                            pq.remove(adjPlace);
                            pq.offer(adjPlace);
                        }
                        break;
                    } 
                }
            }
        }
    }

    /**
     * This method finds a subset of roads in the given list of roads where one end of it is equal to 
     * the given place argument. This method returns the found road otherwise null.
     * @param rList - The list of roads
     * @param p - The place to search for
     * @return foundRoads - The found roads
     */
    private List<Road> findRoadsWithPlace(List<Road> rList, Place p) {
        List<Road> foundRoads = new ArrayList<>();
        for (Road r: rList) {
            if (r.firstPlace().equals(p) || r.secondPlace().equals(p)) {
                foundRoads.add(r);
            }
        }
        
        return foundRoads;
    }

    /**
     * A class that holds a name/key and it's value. This class is used to represet a node and its
     * current path estimate in Dijkstra's algorithm.
     */
    private class PlaceNode {
        Place place;
        int value;
        public PlaceNode(Place place, int val) {
            this.place = place;
            this.value = val;
        }

        public Place getPlaceNode() {
            return this.place;
        }

        public void setPlaceNodeValue(int val) {
            this.value = val;
        }

        public int getPlaceNodeValue() {
            return this.value;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) {
                return false;
            }
    
            if (this == o) {
                return true;
            }
    
            if (!(o instanceof PlaceNode)) {
                return false;
            }

            PlaceNode pn = (PlaceNode)o;
            return this.place.equals(pn.getPlaceNode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(place);
        }

        public String toString() {
            return place.toString() + String.valueOf(value);
        }
    }

    /**
     * This is used for the priority queue to sort the node by their value/priority in ascending order.
     */
    public Comparator<PlaceNode> valueComparator  = new Comparator<PlaceNode>() {
        public int compare(PlaceNode a, PlaceNode b) {
            return a.getPlaceNodeValue() - b.getPlaceNodeValue();
        }
    };

    /* ========== DEBUGGING PURPOSES ========== */
    public void printPlaces() {
        System.out.println(this.places);
    }

    public void printRoads() {
        System.out.println(this.roads);
    }

    /* ==================================================================================== */
    /* ============================ DEFINED HELPER METHODS(END) =========================== */
    /* ==================================================================================== */

}