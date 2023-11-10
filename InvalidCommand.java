package main;

public class InvalidCommand implements UserInputCommand {
	/*
	 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	 */
	String badCommand;
	/**
	 * 
	 * @param badCommand saves the problematic command given by the user so it can be shown later
	 */
	InvalidCommand(String badCommand){
		this.badCommand = badCommand;
	}
	/**
	 * @param ts The TourStatus for the command to be used on 
	 */
	 public String carryOut(TourStatus ts){
		 //tries to help the user with selecting a command
		return "Am sorry I could not understand the command {" + badCommand + "}"
				+ "\nA list of all possible commands are below"
				+ "\n d [name of item](use to drop an item)"
				+ "\n p [name of item](use to pickup an item)"
				+ "\n b (use to show items in your backpack)"
				+ "\n (n,e,s,w) use to move to a new location"
				+ "\n q(use to exit the tour)" 
				+ "\n\n" + ts.getInfo(ts.getCurLocation());
	}
}
