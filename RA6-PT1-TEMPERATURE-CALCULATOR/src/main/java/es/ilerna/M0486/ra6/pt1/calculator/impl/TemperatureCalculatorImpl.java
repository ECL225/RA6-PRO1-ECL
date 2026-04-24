package es.ilerna.M0486.ra6.pt1.calculator.impl;

import es.ilerna.M0486.ra6.pt1.calculator.api.TemperatureCalculator;
import java.util.List;

public class TemperatureCalculatorImpl implements TemperatureCalculator {
    public double average(List<Double> TempsL) {
        if (TempsL != null) {
            if (!TempsL.isEmpty()) {
                double TSuma = 0.0F;
                for (int i = 0; i < TempsL.size(); i++) {
                    double t = TempsL.get(i);
                    TSuma += t;
                }
                return TSuma/TempsL.size();
            } else {
                return 0.0F;
            }
        } else {
            return 0.0F;
        }
    }

    public double max(List<Double> TempsL) {
        if (TempsL != null) {
            if (!TempsL.isEmpty()) {
                double TMax = TempsL.get(0);
                for (int i = 0; i < TempsL.size(); i++) {
                    double t = TempsL.get(i);
                    if (t > TMax) {
                        TMax = t;
                    }
                }
                return TMax;
            } else {
                return 0.0F;
            }
        } else {
            return 0.0F;
        }
    }

    public double min(List<Double> temperatures) {
        if (temperatures != null) {
            if (!temperatures.isEmpty()) {
                double min = temperatures.get(0);
                for (int i = 0; i < temperatures.size(); i++) {
                    double t = temperatures.get(i);
                    if (t < min) {
                        min = t;
                    }
                }
                return min;
            } else {
                return 0.0F;
            }
        } else {
            return 0.0F;
        }
    }

    public double celsiusToFahrenheit(double celsius) {
        return celsius * 9.0 / 5.0F + 32.0F;
    }

    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
}
