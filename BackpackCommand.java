package main;
/*
 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
 */
public class BackpackCommand implements UserInputCommand {
//default constructor no parameters are needed for the backpack
BackpackCommand(){}
/**
 * @param v The TourStatus for the command to be used on 
 */
	public String carryOut(TourStatus v) {
		//shows all items in the backpack
		return v.listItemsInBackpack();
	}

}
