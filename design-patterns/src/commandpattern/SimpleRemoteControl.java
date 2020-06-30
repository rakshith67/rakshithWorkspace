package commandpattern;

public class SimpleRemoteControl {

	protected Command command;

	public SimpleRemoteControl() {
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public void buttonPressed() {
		command.execute();
	}
}
