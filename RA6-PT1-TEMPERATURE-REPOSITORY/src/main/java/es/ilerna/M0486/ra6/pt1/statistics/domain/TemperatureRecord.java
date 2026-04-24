package es.ilerna.M0486.ra6.pt1.statistics.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "temperature_record")
public class TemperatureRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "capital", nullable = false)
    private String capital;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "max_temp", nullable = false)
    private double MaxTemp;

    @Column(name = "min_temp", nullable = false)
    private double MinTemp;

    public TemperatureRecord() {
    }

    public TemperatureRecord(String capital, LocalDate date, double MaxTemp, double MinTemp) {
        this.capital = capital;
        this.date = date;
        this.MaxTemp = MaxTemp;
        this.MinTemp = MinTemp;
    }

    public String getCapital() {
        return this.capital;
    }
    public void setCapital(String capital) {
        this.capital = capital;
    }

    public LocalDate getDate() {
        return this.date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMaxTemp() {
        return this.MaxTemp;
    }
    public void setMaxTemp(double maxT) {
        this.MaxTemp = maxT;
    }

    public double getMinTemp() {
        return this.MinTemp;
    }
    public void setMinTemp(double minT) {
        this.MinTemp = minT;
    }

    @Override
    public String toString() {
        return "TemperatureRecord [Capital=" + this.capital + ", Date=" + String.valueOf(this.date) + ", MaxTemp=" + this.MaxTemp + ", MinTemp=" + this.MinTemp + "]";
    }
}
