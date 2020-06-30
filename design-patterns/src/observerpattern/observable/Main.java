package observerpattern.observable;

/**
 * 
 * The Observer Pattern defines a one-to-many dependency between objects so that
 * when one object changes state, all of its dependents are notified and updated
 * automatically.
 * 
 * @author Rakshith
 *
 */
@SuppressWarnings("unused")
public class Main {

	public static void main(String[] args) {
		WeatherData weatherData = new WeatherData();
		CurrentConditionsDisplay display = new CurrentConditionsDisplay(weatherData);
		ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
		StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
		weatherData.setMeasurements(85, 65, 20.5f);
		weatherData.setMeasurements(80, 60, 20f);
		weatherData.setMeasurements(90, 70, 21f);
	}
}
