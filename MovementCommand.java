package main;

public class MovementCommand implements UserInputCommand {
	/*
	 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	 */
	private String dir = "";
	/**
	 * 
	 * @param dir the direction you want the movement command to move in
	 */
	MovementCommand(String dir){
		this.dir = dir;
	}
	/**
	 * @param v the TourStatus for the command to be executed on
	 */
	public String carryOut(TourStatus v) {
		//checks if anything exists in that direction
		if(!(v.getCurLocation().getArrivalLocation(dir) == null)) {
			//changes the CurLocation
			v.setCurLocation(v.getCurLocation().getArrivalLocation(dir));
		}
		else {
		//alerts the user to the problem and reshows the current locations data
		return "nothing is in that direction\n" + v.getInfo(v.getCurLocation());
		}
		return v.getInfo(v.getCurLocation());
	}
}
