package facadepattern;

/**
 * 
 * The Facade Pattern provides a unified interface to a set of interfaces in a
 * subsystem. Facade defines a higher-level interface that makes the subsystem
 * easier to use
 * 
 * The intent of the Adapter Pattern is to alter an interface so that it matches
 * one a client is expecting. The intent of the Facade Pattern is to provide a
 * simplified interface to a subsystem and it decouples a client from a
 * subsystem of components
 * 
 * The Principle of Least Knowledge guides us to reduce the interactions between
 * objects to just a few close “friends.”
 * 
 * we should only invoke methods that belong to The object itself, Objects
 * passed in as a parameter to the method, Any object the method creates or
 * instantiates and on component of an object.Notice that these guidelines tell
 * us not to call methods on objects that were returned from calling other
 * methods!!
 * 
 * @author rakshim1
 *
 */
public class Main {

	public static void main(String[] args) {
		Amplifier amp = new Amplifier("Top-O-Line Amplifier");
		Tuner tuner = new Tuner("Top-O-Line AM/FM Tuner", amp);
		DvdPlayer dvd = new DvdPlayer("Top-O-Line DVD Player", amp);
		CdPlayer cd = new CdPlayer("Top-O-Line CD Player", amp);
		Projector projector = new Projector("Top-O-Line Projector", dvd);
		TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
		Screen screen = new Screen("Theater Screen");
		PopcornPopper popper = new PopcornPopper("Popcorn Popper");

		HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner, dvd, cd, projector, screen, lights, popper);

		homeTheater.watchMovie("Raiders of the Lost Ark");
		homeTheater.endMovie();
	}
}
