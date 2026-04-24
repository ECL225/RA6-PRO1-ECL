package es.ilerna.M0486.ra6.pt1.statistics.api;

import es.ilerna.M0486.ra6.pt1.statistics.domain.TemperatureRecord;
import java.time.LocalDate;
import java.util.List;

public interface TemperatureRepository extends AutoCloseable {
    void save(TemperatureRecord tempRec);

    List<TemperatureRecord> findAll();

    List<TemperatureRecord> findByCapital(String capital);

    TemperatureRecord findByCapitalAndDate(String capital, LocalDate fecha);

    boolean exists(String capital, LocalDate fecha);

    void delete(String capital, LocalDate fecha);
}
