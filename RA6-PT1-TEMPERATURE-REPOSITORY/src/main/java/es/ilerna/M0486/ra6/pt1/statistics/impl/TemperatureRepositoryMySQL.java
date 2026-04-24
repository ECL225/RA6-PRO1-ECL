package es.ilerna.M0486.ra6.pt1.statistics.impl;

import es.ilerna.M0486.ra6.pt1.statistics.api.HibernateSession;
import es.ilerna.M0486.ra6.pt1.statistics.api.TemperatureRepository;
import es.ilerna.M0486.ra6.pt1.statistics.domain.TemperatureRecord;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TemperatureRepositoryMySQL implements TemperatureRepository {
    private Session session = HibernateSession.getSessionFactory().openSession();

    public void save(TemperatureRecord record) {
        if (this.exists(record.getCapital(), record.getDate())) {
            PrintStream ps = System.out;
            String capital = record.getCapital();
            ps.println("[ERROR] Record already exists: " + capital + " / " + String.valueOf(record.getDate()));
        } else {
            Transaction t = this.session.beginTransaction();

            try {
                this.session.persist(record);
                t.commit();
                System.out.println("Saved: " + String.valueOf(record));
            } catch (Exception e) {
                if (t != null) {
                    t.rollback();
                }

                System.err.println("Error saving: " + e.getMessage());
            }

        }
    }

    public List<TemperatureRecord> findAll() {
        return this.session.createQuery("FROM TemperatureRecord", TemperatureRecord.class).getResultList();
    }

    public List<TemperatureRecord> findByCapital(String capital) {
        return this.session.createQuery("FROM TemperatureRecord WHERE capital = :capital", TemperatureRecord.class).setParameter("capital", capital).getResultList();
    }

    public TemperatureRecord findByCapitalAndDate(String capital, LocalDate date) {
        return (TemperatureRecord)this.session.createQuery("FROM TemperatureRecord WHERE capital = :capital AND date = :date", TemperatureRecord.class).setParameter("capital", capital).setParameter("date", date).uniqueResult();
    }

    public boolean exists(String capital, LocalDate date) {
        return this.findByCapitalAndDate(capital, date) != null;
    }

    public void delete(String capital, LocalDate date) {
        TemperatureRecord record = this.findByCapitalAndDate(capital, date);
        if (record != null) {
            Transaction t = this.session.beginTransaction();
            try {
                this.session.delete(record);
                t.commit();
                System.out.println("Deleted: " + capital + " / " + String.valueOf(date));
            } catch (Exception e) {
                if (t != null) {
                    t.rollback();
                }
                System.err.println("Error deleting: " + e.getMessage());
            }

        } else {
            System.out.println("Record not found: " + capital + " / " + String.valueOf(date));
        }
    }

    public void close() {
        if (this.session != null && this.session.isOpen()) {
            this.session.close();
            System.out.println("Session closed.");
        }

    }
}