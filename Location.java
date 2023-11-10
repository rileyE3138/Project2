package main;

import java.util.*;

 public class Location {
	 /*
	  * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	  */
//used for holding the name and description for the location
private String name = "";
private String description = "";
//by default is true, will be changed to false, Once the location has been visited.
//Hashtable for holding door data
private Hashtable<String, Door> Doors = new Hashtable<String, Door>();
//used for telling if it is the first time this location has been visited
private Boolean FirstTime = true;
private ArrayList<Item> items = new ArrayList<Item>();
Location(){};
/**
 * 
 * @param name sets the Location
 * @param description sets the description of the location
 * @deprecated is replaced with better Location(Scanner r) and is no longer used
 */
Location(String name, String description){
	this.name = name;
	this.description = description;
}
/**
 * 
 * @param reader the scanner containing the text file with the location data
 */
Location(Scanner reader){
	String i = reader.nextLine();
	String descCompiler = "";
	//checks if there is a location to create
	if(!i.equals("*****")) {
		//sets the name
		name = i;
		i = reader.nextLine();
		while(!i.equals("+++")) {
			descCompiler += "\n" +i;
			i = reader.nextLine();
		}
		description = descCompiler;
	}
}
/**
 * adds a door with all 3 parts of the door filled out
 * @param tempD,door adds the Door information to the DoorHashTable
 */
public void addDoor( Door tempD) {
	//Sets the Door for the correct Direction
		Doors.put("" + tempD.getDirection(), tempD);
}
/**
 * @param name, used to set the name of the location
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return returns the name of the location
 */
public String getName() {
	return name;
}
/**
 * @param desc sets it as the description of the current
 */
public void setDesc(String desc) {
	this.description = desc;
}
/**
 * @return String returns the description of the location
 */
public String getDesc() {
	return description;
}
/**
 * @param first used for setting if it is the first time the location has been visited
 */
public void setFirstTime(Boolean first) {
	FirstTime = first;
}
/**
 * @return returns a boolean representing if this location has been visited before
 */
public Boolean getFirstTime() {
	return FirstTime;
}
/**
 * @param direction selects which door to get the ArrivalLocation for\
 * @return returns the name of the Arrival location for the selected direction, or returns null if it failed to find a ArrivalLocation
 */
public Location getArrivalLocation(String direction) {
	//ensures good input before moving direction
	if(Doors.get(direction) != null){
		return Doors.get(direction).getEntering();
	}
	return null;
}
/**
 * @return returns a String containing the arrival location and direction value for all doors contained inside this Location
 */
public String getDoors() {
	String toString = "";
	//we use 4 try catches since there might not be doors, in all four directions
	try {
		toString += Doors.get("n").describe();
	}catch(Exception e) {}
	try {
		toString += Doors.get("e").describe();
	}catch(Exception e) {}
	try {                                   
		toString += Doors.get("s").describe();    
	}catch(Exception e) {} 
	try {                    
		toString += Doors.get("w").describe(); 
    }catch(Exception e) {}  
	return toString;
}
/**
 * 
 * @param item the item you want removed from the location
 */
public void removeItem(Item item) {
	 Enumeration<Item> e = Collections.enumeration(items);
	 int count = 0;
	 while(e.hasMoreElements()) {
		 if(e.nextElement() == item) {
		 items.remove(count);
		 //stops us from continuing to scan for no reason
		 break;
		 }
		 count++;
	 }
}
/**
 * 
 * @param item the item you would like added
 */
public void addItem(Item item) {
	items.add(item);
}

/**
 * @return String returns all information saved in this location
 */
public String toString() {
	String toString = "";
	toString += "LOC NAME: " + name + " \n ";
	toString += "DESC:" + description + " \n ";
	toString += "FirstTime : " + FirstTime + " \n ";
	//Uses 4 try catches since all four doors, might not exist
	try {
		toString += Doors.get("n").toString();
	}catch(Exception e) {}
	try {
		toString += Doors.get("e").toString();
	}catch(Exception e) {}
	try {                                   
		toString += Doors.get("s").toString();    
	}catch(Exception e) {} 
	try {                    
   		toString += Doors.get("w").toString(); 
    }catch(Exception e) {}  
	toString += "/n available items ";
	for(int x = 0; x < items.size(); x++)
		toString += "\n" + items.get(x).toString();
	return toString;
}
/**
 * 
 * @return returns a string containing all items in the location
 */
public String getItemsInLocation() {
	String toString = "\nAvailable Items: ";
	//combines all items toString into one String
	for(int x = 0; x < items.size(); x++) {
		toString += "\n" + items.get(x).toString();
	}
	return toString;
}
/**
 * 
 * @param name used to select which item you would like returned 
 * @return returns the item by that name or null if there were none by that name
 */
public Item getItemNamed(String name) {
	//scans till the Item is found
	for(int x = 0; x < items.size(); x++) {
		if(items.get(x).getName().equals(name))
			return items.get(x);
	}
	return null;
}
}