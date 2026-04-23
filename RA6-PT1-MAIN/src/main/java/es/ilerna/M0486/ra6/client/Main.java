package es.ilerna.M0486.ra6.client;

import es.ilerna.M0486.ra6.pt1.statistics.api.TemperatureRepository;
import es.ilerna.M0486.ra6.pt1.statistics.domain.TemperatureRecord;
import es.ilerna.M0486.ra6.pt1.statistics.impl.TemperatureRepositoryMySQL;
import es.ilerna.M0486.ra6.pt1.calculator.api.TemperatureCalculator;
import es.ilerna.M0486.ra6.pt1.calculator.impl.TemperatureCalculatorImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static  TemperatureCalculator calc = new TemperatureCalculatorImpl();
	
	public static void main(String[] args) {
		try (TemperatureRepository repo = new TemperatureRepositoryMySQL()) {
		
			Scanner sc = new Scanner(System.in);
			int opcio;
			
			do {
				System.out.println("\n===== MENU PRINCIPAL =====");
				System.out.println("1) Fase 1: Desar dades de prova");
				System.out.println("2) Fase 2: Cercar i mostrar registres");
				System.out.println("3) Fase 3: Calcular estadistiques");
				System.out.println("0) Sortir");
				System.out.print("Escull una opcio: ");
				
				opcio = sc.nextInt();
				sc.nextLine();
				
				switch (opcio) {
					case 1: fase1(repo); break;
					case 2: fase2(repo); break;
					case 3: fase3(repo); break;
					case 0: System.out.println("Fins aviat!"); break;
					default: System.out.println("Opcio incorrecta.");
				}
	
			} while (opcio != 0);
			
			sc.close();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	private static void fase1(TemperatureRepository repo) {
		System.out.println("\n--- Fase 1: Desant dades de prova ---");
		
		repo.save(new TemperatureRecord("Barcelona", LocalDate.of(2024, 1, 10), 14.0,  6.0));
		repo.save(new TemperatureRecord("Barcelona", LocalDate.of(2024, 1, 11), 16.5,  8.0));
		repo.save(new TemperatureRecord("Barcelona", LocalDate.of(2024, 1, 12), 12.0,  4.5));
		
		repo.save(new TemperatureRecord("Girona",    LocalDate.of(2024, 1, 10), 10.0,  1.0));
		repo.save(new TemperatureRecord("Girona",    LocalDate.of(2024, 1, 11), 11.5,  2.5));
		repo.save(new TemperatureRecord("Girona",    LocalDate.of(2024, 1, 12),  9.0, -1.0));
		
		repo.save(new TemperatureRecord("Lleida",    LocalDate.of(2024, 1, 10),  8.0, -2.0));
		repo.save(new TemperatureRecord("Lleida",    LocalDate.of(2024, 1, 11),  9.5, -0.5));
		repo.save(new TemperatureRecord("Lleida",    LocalDate.of(2024, 1, 12),  7.0, -3.5));
		
		repo.save(new TemperatureRecord("Tarragona", LocalDate.of(2024, 1, 10), 15.0,  7.0));
		repo.save(new TemperatureRecord("Tarragona", LocalDate.of(2024, 1, 11), 17.0,  9.0));
		repo.save(new TemperatureRecord("Tarragona", LocalDate.of(2024, 1, 12), 13.5,  5.5));
		
		repo.save(new TemperatureRecord("Barcelona", LocalDate.of(2024, 1, 10), 99.9, 99.9));
		
		System.out.println("\nFase 1 completada.");
	}
	
	private static void fase2(TemperatureRepository repo) {
		System.out.println("\n--- Fase 2: Cercant i mostrant registres ---");
		
		// Tots els registres
		System.out.println("\n>> Tots els registres:");
		List<TemperatureRecord> tots = repo.findAll();
		for (TemperatureRecord r : tots) {
			System.out.println("\t" + r);
		}
		
		// Registres d'una capital concreta
		System.out.println("\n>> Registres de Barcelona:");
		List<TemperatureRecord> barcelona = repo.findByCapital("Barcelona");
		for (TemperatureRecord r : barcelona) {
			System.out.println("\t" + r);
		}
		
		// Registre d'una capital i data concreta
		System.out.println("\n>> Registre de Lleida el 2024-01-10:");
		TemperatureRecord lleida = repo.findByCapitalAndDate("Lleida", LocalDate.of(2024, 1, 10));
		if (lleida != null) {
			System.out.println("\t" + lleida);
		} else {
			System.out.println("\tNo trobat.");
		}
		
		// Comprovacio d'existencia
		System.out.println(">> Existeix Girona 2024-01-11? " + repo.exists("Girona", LocalDate.of(2024, 1, 11)));
		System.out.println(">> Existeix Girona 2099-01-01? " + repo.exists("Girona", LocalDate.of(2099, 1,  1)));
		
		System.out.println("\nFase 2 completada.");
	}
	
	private static void fase3(TemperatureRepository repo) {
		System.out.println("\n--- Fase 3: Calculant estadistiques ---");
		
		String[] capitals = {"Barcelona", "Girona", "Lleida", "Tarragona"};
		
		for (String capital : capitals) {
			System.out.println("\n========== " + capital + " ==========");
			
			// Obtenim els registres de la capital
			List<TemperatureRecord> registres = repo.findByCapital(capital);
			
			// Separem les temperatures maximes i minimes
			List<Double> maximes = new ArrayList<>();
			List<Double> minimes = new ArrayList<>();
			for (TemperatureRecord r : registres) {
				maximes.add(r.getMaxTemp());
				minimes.add(r.getMinTemp());
			}
			
			System.out.printf("\tMaxima absoluta : %.1f C%n",  calc.max(maximes));
			System.out.printf("\tMinima absoluta : %.1f C%n",  calc.min(minimes));
			System.out.printf("\tMitjana maximes : %.1f C%n",  calc.average(maximes));
			System.out.printf("\tMitjana minimes : %.1f C%n",  calc.average(minimes));
			
			double mitjanaMax = calc.average(maximes);
			System.out.printf("\tMitjana max (F) : %.1f F%n",  calc.celsiusToFahrenheit(mitjanaMax));
			System.out.printf("\tMitjana max (K) : %.1f K%n",  calc.celsiusToKelvin(mitjanaMax));
		}
		
		System.out.println("\nFase 3 completada.");
	}
}