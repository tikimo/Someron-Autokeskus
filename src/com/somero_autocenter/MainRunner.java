package com.somero_autocenter;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.somero_autocenter.CellWriter;

public class MainRunner {
	final DecimalFormat formatter = new DecimalFormat("0.00€");

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner lukija = new Scanner(System.in);
		boolean usingSQL = false;
		ArrayList<String[]> tuotteet = new ArrayList<String[]>();
		String timeStamp = new SimpleDateFormat("yyyyMMddHH").format(Calendar.getInstance().getTime());

		if (usingSQL) {
			//TODO
		} else {
			// Aseta Auto
			Auto auto = new Auto();
			tulosta("Anna ajoneuvon rekisterinumero:");
			auto.setRekno(lukija.nextLine());
			tulosta("Sitten merkki:");
			auto.setMerkki(lukija.nextLine());
			tulosta("Ja vielä malli:");
			auto.setMalli(lukija.nextLine());
			
			// Aseta asiakas
			Asiakas asiakas = new Asiakas(auto);
			tulosta("Anna asiakkaan nimi:");
			asiakas.setNimi(lukija.nextLine());
			asiakas.setAsiakasNumero(timeStamp);
			tulosta("Asiakasnumeroksi asetettu: " + timeStamp);
		}
		
		boolean loop = true;
		while (loop) {
			if (tuotteet.size() > 13) {
				loop = false;
			}
			tulosta("Lisaa tuote antamalla tuotenumero. "
					+ "Lopeta tuotteiden syotto antamalla tuotenumeroksi 0.");
			String tnro = lukija.nextLine();
			switch(tnro) {
				case "0":
					loop = false;
				default:
					/**
					 * Tässä otetaan tuotteen tiedot ylös, tallennetaan listaan
					 * ja lista lisätään tuote ArrayListiin
					 */
					tulosta("Selvitys tuotteelle "+tnro);
					String selvitys = lukija.nextLine();
					
					tulosta("Kappalemaara tuotteelle "+tnro);
					String kpl = lukija.nextLine();
					
					tulosta("Hinta tuotteelle, erota eurot ja sentit pisteella:");
					String aHinta = lukija.nextLine();
					
					String alvios = ""+Double.parseDouble(aHinta)/1.24*0.24;
					String nettohinta = ""+Double.parseDouble(aHinta)/1.24;
					String kokonaishinta = ""+Double.parseDouble(aHinta)*Double.parseDouble(kpl);
					
					
					String[] tuote = {tnro, selvitys, kpl, aHinta, alvios, nettohinta, kokonaishinta};
					
					tuotteet.add(tuote);
					
			}
		
			for (String[] tuote : tuotteet) {
				int index = 11;
				for (int ci = 0; ci <= 4; ci++) {
					if (ci <= 3) {
						CellWriter.writeStringToCell("tyolasku.xls", index, ci, tuote[ci]);
					} else {
						CellWriter.writeStringToCell("tyolasku.xls", index, 4, tuote[4]);
						CellWriter.writeStringToCell("tyolasku.xls", index, 5, tuote[5]);
						CellWriter.writeStringToCell("tyolasku.xls", index, 6, tuote[6]);
					}
				}
				
			}
		
		}
		
		//TODO Laske summat, poista tyhjät rivit, aseta asiakas, aseta pvm
		//Laske summat
		double alviosTotal = 0, nettohintaTotal = 0, kokonaishintaTotal = 0;
		for (String[] tuote : tuotteet) {
			alviosTotal += Double.parseDouble(tuote[4]);
			nettohintaTotal += Double.parseDouble(tuote[5]);
			kokonaishintaTotal += Double.parseDouble(tuote[6]);
		}
		CellWriter.writeStringToCell("tyolasku.xls", 24, 4, ""+alviosTotal);
		CellWriter.writeStringToCell("tyolasku.xls", 24, 4, ""+nettohintaTotal);
		CellWriter.writeStringToCell("tyolasku.xls", 24, 4, ""+kokonaishintaTotal);
		
		
		//Tyhjät rivit
		for (int i = 11 + tuotteet.size(); i<23; i++) { // Ensimmäinen tyhjä rivi 
			for (int j = 0; j<6; j++) { // Iteroidaan solut
				CellWriter.writeStringToCell("tyolasku.xls", i, j, "");
			}
		}
		

		
		
		//TODO Tee tiedostosta kopio muualle

	}
	
	protected static void tulosta(String line) {
		System.out.println(line);
	}
}
