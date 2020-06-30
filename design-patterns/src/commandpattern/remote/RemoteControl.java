package commandpattern.remote;

public class RemoteControl {

	Command[] onCommands;
	Command[] offCommands;
	Command undoCommand;

	public RemoteControl() {
		onCommands = new Command[7];
		offCommands = new Command[7];

		Command noCommand = new NoCommand();
		for (int i = 0; i < 7; i++) {
			onCommands[i] = noCommand;
			offCommands[i] = noCommand;
		}
		undoCommand = noCommand;
	}

	public void setCommand(int index, Command onCommand, Command offCommand) {
		onCommands[index] = onCommand;
		offCommands[index] = offCommand;
	}

	public void onButtonWasPushed(int index) {
		undoCommand = onCommands[index];
		onCommands[index].execute();
	}

	public void offButtonWasPushed(int index) {
		undoCommand = onCommands[index];
		offCommands[index].execute();
	}

	public void undoButtonWasPushed() {
		undoCommand.execute();
	}

	public String toString() {
		StringBuffer stringBuff = new StringBuffer();
		stringBuff.append("\n------ Remote Control -------\n");
		for (int i = 0; i < onCommands.length; i++) {
			stringBuff.append("[slot " + i + "] " + onCommands[i].getClass().getName() + "    "
					+ offCommands[i].getClass().getName() + "\n");
		}
		return stringBuff.toString();
	}
}
