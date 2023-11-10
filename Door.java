package main;
public class Door {
	/*
	 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	 */
private Location leavingLocation;
private Location enteringLocation;
//is set to X to make error detection easier
private String direction = "X";
//default constructor
Door(){}
/**
 * 
 * @param leavingLocation the location the door leaves
 * @param direction the direction the door is facing
 * @param enteringLocation the location the door arrives at
 */
Door(Location leavingLocation,String direction, Location enteringLocation){
	this.leavingLocation = leavingLocation;
	this.enteringLocation = enteringLocation;
	this.direction = direction;
}

/**
 * @return returns only the information needed for TourStatus
 */
public String describe() {
	return "\n if you go (" + direction + ") you will arrive at " + enteringLocation.getName();
}
/**
 *@return returns the leaving locations name
 */
public Location getLeaving() {
	return leavingLocation;
}
/**
 * @param leavingLocation sets the name of leavingLocation for the door
 */
public void setLeaving(Location leavingLocation) {
	this.leavingLocation = leavingLocation;
}
/**
 * @return returns the name of the ArrivalLocation
 */
public Location getEntering() {
	return enteringLocation;
}
/**
 * @param enteringLocation sets the name of the Arrival Location
 */
public void setEntering(Location enteringLocation) {
	this.enteringLocation = enteringLocation;
}
/**
 * @param dir sets the direction of the door
 */
public void setDirection(String dir) {
	direction = dir;
}
/**
 * @return returns the direction of the door
 */
public String getDirection() {
	return direction;
}
/**
 * @return returns all information saved in the door
 */
public String toString() {
	String toString = "";
	toString += " \n Leaving name: " + leavingLocation.getName() + " \n ";
	toString += "Direction: " + direction;
	toString += " \n Arrival name: " + enteringLocation.getName();
	return toString;
}

}
