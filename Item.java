package main;
import java.util.*;

public class Item {
	/*
	 * I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work
	 */
private String name;
private String message;
Item(){}
Item(Scanner S){
name = S.nextLine();
	S.nextLine();
message = S.nextLine();
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String toString() {
	return name + "\n("+ message +")";
}
public void addToMessage(String message) {
	this.message += "\n" + message;
}
public void removeLastLine() {
	int line = 0;
	for(int x = 0; x < message.length(); x++){
		if(message.charAt(x) == '\n') {
			line = x;
		}
	}
	message = message.substring(0, line);
}
}

