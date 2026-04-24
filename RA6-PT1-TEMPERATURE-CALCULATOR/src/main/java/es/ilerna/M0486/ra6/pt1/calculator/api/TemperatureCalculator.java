package es.ilerna.M0486.ra6.pt1.calculator.api;

import java.util.List;

public interface TemperatureCalculator {
    double average(List<Double> TempList);

    double max(List<Double> TempList);

    double min(List<Double> TempList);

    double celsiusToFahrenheit(double TempC);

    double celsiusToKelvin(double TempC);
}
