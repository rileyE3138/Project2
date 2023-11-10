package main;

import java.util.*;

public class Campus {
	/*
	 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	 */
	private String campusName;
	/*
	 * for holding the list of locations
	 * the key String should always match the name that has been saved for the location
	 * it's public so each other class does not have to create their own copy of this hash table
	 */
	private Hashtable<String, Location> Locations = new Hashtable<String, Location>();

	private Location startingLocation = null;

//default constructor not used for this situation 
public Campus() {}


/** 
 *
 * @param newLoc the Location you want to add to the Campus Object
 * 
 * For directly adding new Locations
 */
public void newLocation(Location newLoc) {
	Locations.put(newLoc.getName(), newLoc);
}
/**
 * @param locName the name of the location you want to get
 * @return a specific locations
 */
public Location getLocation(String locName) {
	return Locations.get(locName);
}
/**
 * @return the list of all locations saved for this campus
 */
public Hashtable<String, Location> getAllLocations() {
	return Locations;
}
/**
 * @param newLoc sets which location will be saved as the starting location
 */
public void SetStartingLocation(Location newLoc) {
	startingLocation = newLoc;
}
/**
 * @return returns the location that is saved as the Starting Location
 */
public Location getStartingLoc() {
	return startingLocation;
}
/**
 * 
 * @param campusName sets the name of the campus
 */
public void setName(String campusName) {
	this.campusName = campusName;
}
/**
 * 
 * @return returns the name of the campus
 */
public String getName() {
	return campusName;
}

}

