package main;
import java.util.*;
import java.io.*;

public class TourUMW {
	/*
	 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	 */
	public static void main(String[] args) {
		/*
		 * @author Riley M Erickson
		 * Project 2 CPSC 240 Section 3
		 * @version 2.1
		 */
		Scanner UserInput =  new Scanner(System.in);
		String input = "" ;
		TourStatus TourManager = new TourStatus();
		TourUMW tourUMW = new TourUMW();
		//s allows the user to try to load a file multiple times if the mess it up the first time
		Boolean s = false;
			while(!s){
			try {
				System.out.println("please enter the name of the tour File");
				input = UserInput.nextLine();
				//attempts to build the TourStatus Object
				TourManager.setCampus(tourUMW.setUpCampus(new Scanner(new File(input + ".txt "))));
				//sets the starting location
				TourManager.setCurLocation(TourManager.getCampus().getStartingLoc());
				s = true;
			}
			catch(Exception e) {
				//allows the user to exit before entering a file
				if(input.equals("q")) {
				System.out.println("Good Bye"); 
				System.exit(0);
				}
				else
				System.out.println("error setting up file");
			}
			}
			//we need to show the startingloc data before starting the tour;
			System.out.println(TourManager.getInfo(TourManager.getCurLocation()));
			//default is backPackCommand since it shows the backpack begins empty
			for(UserInputCommand command = new BackpackCommand(); command != null; command = tourUMW.PromptUser(UserInput)) {
				System.out.println(command.carryOut(TourManager) + "");	
			}
			
	}
	/**
	 * 
	 * @param Input the scanner used to get the input from the user.
	 * @return returns the command the user selected it then just needs to be carried out
	 */
public UserInputCommand PromptUser(Scanner Input) {
	//saves the input as a string so we can compare to it later
	// then prompt for the user is handled outside the method that way it can be controlled
	String input = Input.nextLine();
	//prevents crashes since if the length is 0 .charAt(0) will fail
	if(input.length() == 0) { return new InvalidCommand(input);}
	switch(input.charAt(0)+ "") {
	//if any of the movement directions are given we want the movement command
	case "n":
	case "e":
	case "s":
	case "w":
		return new MovementCommand(input.charAt(0) + "");	
	case "d":
		
		//ensures it's long enough for minimum length for using drop and not d. This is checked first to ensure subString does not run into problems
		if(input.length() >= 6) {
			//checks for "drop" because both d and drop needs to be accepted since it might just be "d Really Long Item Name"
			if(input.substring(0,4).equals("drop")) {
				return new DropCommand(input.substring(5,input.length()));
			}
		}
		
       /*
		* prevents errors by ensuring that it's the minimum length for parsing.
		* Uses invisible characters to increase length so Users don't see it when
		* they are showed their input mistake in DropCommand's fail condition
		* min length is 3 since (1 char for d / 1 char for " " / at least 1 char for the name of the Item
		*/
		if(input.length() < 3) {
			input += "\u200e\u200e\u200e";
		}
		return new DropCommand(input.substring(2,input.length()));
	case "p":
		
		//ensures it's long enough for minimum length for using pickup and not p. This is checked first to ensure subString does not run into problems
		if(input.length() >= 8) {
			//checks for "drop" because both d and drop needs to be accepted since it might just be "d Really Long Item Name"
			if(input.substring(0,6).equals("pickup")) {
				return new PickupCommand(input.substring(7,input.length()));
			}
		}
		/*
		* prevents errors by ensuring that it's the minimum length for parsing.
		* Uses invisible characters to increase lengths so Users don't see it when
		* they are showed their input mistake in PickupCommand's fail condition
		*/
		if(input.length() < 3) {
			input += "\u200e\u200e\u200e";
		}
		return new PickupCommand(input.substring(2,input.length()));
	case "b":
		return new BackpackCommand();
	case "q" :
		System.out.println("good bye");
	//returns null instead of a Command Object since no QuitCommand was specified for the UML 
		return null;
	//if none of the other case's were triggered then we alert the user to their mistake
	//with InvalidCommand
	default:
		return new InvalidCommand(input);
	}
}
/**
 * @param reader the scanner that holds the data for the campus
 * @return Campus returns all the extracted data for the campus as a Campus Object
 */
public Campus setUpCampus(Scanner reader) {
	//where we store the campus data before returning it
	Campus campus = new Campus();
	/*
	 * PROCESSES CAMPUS NAME + LOCATIONS/Sets Starting/Current Location
	 */
	campus.setName(reader.nextLine());
	//Skips separating characters + location tag
	reader.nextLine();
	reader.nextLine();
	//tempLoc is used to improve readability and to hold the location data before
	//adding it to the campus locList
	Location tempLoc = new Location(reader);
	//sets up the first location and sets it as the starting location
	campus.newLocation(tempLoc);
	campus.SetStartingLocation(campus.getLocation(tempLoc.getName()));
	//reads through all locations in the file
	while((!tempLoc.getName().equals(""))) {
		tempLoc = new Location(reader);
		/*
		* ensure that the data was extracted correctly before adding it to the campus
		* since if the name comes back as "" that means that there was no more locations to read
		*/
		if(!(tempLoc.getName().equals(""))) {
			campus.newLocation(tempLoc);
		}
	}
	/*
	 * PROCESSES DOORS
	 */
	//moves to the Door tag
	reader.nextLine();
	String leavingLoc = reader.nextLine();
	//if the leavingLoc name is the phrase ****** that represents the end of the doors that means all doors have been processed
	while(!leavingLoc.equals("*****")){
		//adds door
		campus.getLocation(leavingLoc).addDoor(new Door(campus.getLocation(leavingLoc),reader.nextLine(),campus.getLocation(reader.nextLine())));
		//skips separating text
		reader.nextLine();
		//saves leavingloc for the next door, OR saves the ending phrase ***** so we can exit the while loop
		leavingLoc = reader.nextLine();
	}
	/*
	 * PROCESSES ITEMS 
	 */
	//skips Item tag
	reader.nextLine();
	//holds the the data before adding it to the location
	Item temp = new Item();
	String loc = "";
	//sets the name before the first time entering the loop
	temp.setName(reader.nextLine());
	while(!(temp.getName().equals("*****"))) {
		//saves the location
		loc = reader.nextLine();
		//saves the message
		temp.setMessage(reader.nextLine());
		//adds data
		campus.getLocation(loc).addItem(temp);
		//preps for next Item or for the end of data gathering
		reader.nextLine();
		//resets temp
		temp = new Item();
		//will either be the name of the next Item or the fail text *****
		temp.setName(reader.nextLine());
	}
	return campus;
}
}

