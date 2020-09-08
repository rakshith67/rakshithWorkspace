package practics;

public class Operations {

	public int getDivision(int a, int b) throws RakshithException {
		try {
			return a / b;
		} catch (Exception e) {
			throw new RakshithException("Customised Excpetion thrown and arithmetic exception occured", "abc");
		}
	}
}
