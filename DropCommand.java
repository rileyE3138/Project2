package main;

public class DropCommand implements UserInputCommand {
	/*
	 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	 */
private String ItemName = "";
/**
 * 
 * @param ItemName the name of the item you want dropped 
 */
DropCommand(String ItemName){
	this.ItemName = ItemName;
}
/**
 * @param ts The TourStatus for the command to be used on 
 */
public String carryOut(TourStatus ts){
	if(ts.DropItemFromBackpack(ItemName) != null) {
		ts.getCurLocation().addItem(ts.DropItemFromBackpack(ItemName));
		ts.removeFromBackpack(ts.DropItemFromBackpack(ItemName));
		return"Item Added to:\n" + ts.getInfo(ts.getCurLocation());
	}
	return "failed to drop up the Item named " + ItemName + " at " + ts.getCurLocation().getName();
}

}
