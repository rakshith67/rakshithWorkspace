package practics;

public class RakshithException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	private String code;

	public RakshithException() {
		super();
	}

	public RakshithException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return "code " + code + " " + message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
