package commandpattern.remote;

public class LivingroomLightOffCommand implements Command {
	Light light;

	public LivingroomLightOffCommand(Light light) {
		this.light = light;
	}

	public void execute() {
		light.off();
	}
	
	@Override
	public void undo() {
		light.on();
	}
}
