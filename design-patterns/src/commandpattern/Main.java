package commandpattern;

/**
 * 
 * The Command Pattern encapsulates a request as an object, thereby letting you
 * parameterize other objects with different requests, queue or log requests,
 * and support undoable operations.
 * 
 * A Command object is at the center of this decoupling and encapsulates a
 * receiver with an action (or set of actions) .
 * 
 * Commands may support undo by implementing an undo method that restores the
 * object to its previous state before the execute() method was last called.
 * 
 * @author Rakshith
 *
 */
public class Main {

	public static void main(String[] args) {
		SimpleRemoteControl remoteControl = new SimpleRemoteControl();
		Light light = new Light();
		LightOnCommand lightOncommand = new LightOnCommand(light);
		GarageDoor garageDoor = new GarageDoor();
		GarageDoorOpenCommand garageOpenCommand = new GarageDoorOpenCommand(garageDoor);
		remoteControl.setCommand(lightOncommand);
		remoteControl.buttonPressed();
		remoteControl.setCommand(garageOpenCommand);
		remoteControl.buttonPressed();
	}

}
