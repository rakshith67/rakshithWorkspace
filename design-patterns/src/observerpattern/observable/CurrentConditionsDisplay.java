package observerpattern.observable;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("unused")
public class CurrentConditionsDisplay implements DisplayElement, Observer {

	private float temperature;
	private float humidity;
	private Observable observable;

	public CurrentConditionsDisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Temperature: " + temperature + " Humidity: " + humidity);
	}

	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof WeatherData) {
			WeatherData weatherData = (WeatherData) observable;
			this.temperature = weatherData.getTemperature();
			this.humidity = weatherData.getHumidity();
			display();
		}
	}

}
