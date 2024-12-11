package it.addestramento;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestMain {
	private final static String url = "jdbc:mariadb://localhost:3306/lucianodb";
	private final static String user = "root";
	private final static String pass = "root";
	private static void leggiPosizioni(){
		Connection con = null;
		try {

			// Registering drivers
			// Reference to connection interface
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from posizioni");
			while (rs.next()) {
				System.out.println("tipo " + rs.getString("tipo"));
			}
			
			// Closing the connections
			//con.close();
		}
		 catch (SQLException e) {
	         e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void deletePosizioniById(int id) {
		Connection con = null;
		try {

			// Registering drivers
			// Reference to connection interface
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement stmt = con.createStatement();
			PreparedStatement psDel = con.prepareStatement("DELETE from posizioni WHERE id = ?");
			psDel.setInt(1, id);
			psDel.executeUpdate();
			System.out.println("linea numero " + id + " cancellata");
		//	CRUD = create, read, update e delete
			// Closing the connections
			//con.close();
		}
		 catch (SQLException e) {
	         e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void aggiornaPosizioniById(int id) {
		Connection con = null;
		 PreparedStatement pstmt = null;
		try {

			// Registering drivers
			// Reference to connection interface
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			String query = "UPDATE posizioni set simbolo = ? where id = ? ";
	        pstmt = con.prepareStatement(query); // create a statement
	        pstmt.setString(1, "USDJPY"); // set input parameter 1
	        pstmt.setInt(2, id); // set input parameter 2
	        pstmt.executeUpdate(); // execute update statement

	        pstmt.close();
			System.out.println("linea numero " + id + " aggiornata");
		//	CRUD = create, read, update e delete
			// Closing the connections
			//con.close();
		}
		 catch (SQLException e) {
	         e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createTablePosizioni() {
		Connection con = null;
		try {

			// Registering drivers
			// Reference to connection interface
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE POSIZIONI (ID int not null primary key, ORA_OPEN varchar(20), POSIZIONE varchar(20), SIMBOLO varchar(20), TIPO varchar(20), VOLUME varchar(20),PREZZO varchar(20), SL_PREZZO varchar(20), TP_PREZZO varchar(20),ORA_CLOSE varchar(20), PREZZO_CLOSE varchar(20), COMMISSIONI varchar(20), SWAP varchar(20), PROFITTO varchar(20))");
			// Closing the connections
			con.close();
		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}

	private static void createTableOrdini() {
		Connection con = null;
		try {

			// Registering drivers
			// Reference to connection interface
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE ORDINI (ID int not null primary key, ORARIO_APERTURA varchar(20), ORDINE varchar(20), SIMBOLO varchar(20), TIPO varchar(20), VOLUME varchar(20),PREZZO varchar(20), SL_PREZZO varchar(20), TP_PREZZO varchar(20),ORA varchar(20), STATO varchar(20), COMMENTO varchar(20))");
			// Closing the connections
			con.close();
		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}

	private static void createTableAffari() {
		Connection con = null;
		try {

			// Registering drivers
			// Reference to connection interface
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			// Creating a statement
			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE AFFARI (ID int not null primary key, ORA varchar(20), AFFARE varchar(20), SIMBOLO varchar(20), TIPO varchar(20), DIREZIONE varchar(20),VOLUME varchar(20), PREZZO varchar(20), ORDINE varchar(20), COMMISSIONI varchar(20), SPESE varchar(20), SWAP varchar(20), PROFITTO varchar(20), BILANCIO varchar(20), COMMENTO varchar(20))");
			// Closing the connections
			con.close();
		}

		// Catch block to handle exceptions
		catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}

	public static void main(String[] args) throws IOException {
		// createTablePosizioni();
		// createTableOrdini();
		// createTableAffari();
		//leggiPosizioni();
		//deletePosizioniById(8);
		//aggiornaPosizioniById(11);
		Connection con = null;

		// Try block to check for exceptions
		try {

			// Registering drivers
			// Reference to connection interface
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			leggiPosizioni();

			FileInputStream file = new FileInputStream(
					new File("C:\\Luciano\\addestramento\\ReportHistory-11182143.xlsx"));
			XSSFWorkbook wbOrigine = new XSSFWorkbook(file);
			XSSFSheet sheet = wbOrigine.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			XSSFWorkbook wbPosizioni = new XSSFWorkbook();
			Sheet sheet1 = wbPosizioni.createSheet("Foglio 1");
			XSSFWorkbook wbOrdini = new XSSFWorkbook();
			Sheet sheet2 = wbOrdini.createSheet("Foglio 1");
			XSSFWorkbook wbAffari = new XSSFWorkbook();
			Sheet sheet3 = wbAffari.createSheet("Foglio 1");

			int x = 1;
			Row rowTitoli = sheet1.createRow(0);
			XSSFCellStyle style = wbPosizioni.createCellStyle();
			XSSFFont defaultFont = wbPosizioni.createFont();
			defaultFont.setBold(true);
			style.setFont(defaultFont);
			style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setAlignment(HorizontalAlignment.CENTER);

			int y = 1;
			Row rowTitoli1 = sheet2.createRow(0);
			XSSFCellStyle style1 = wbOrdini.createCellStyle();
			XSSFFont defaultFont1 = wbOrdini.createFont();
			defaultFont.setBold(true);
			style1.setFont(defaultFont1);
			style1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style1.setAlignment(HorizontalAlignment.CENTER);

			int z = 1;
			Row rowTitoli2 = sheet3.createRow(0);
			XSSFCellStyle style2 = wbAffari.createCellStyle();
			XSSFFont defaultFont2 = wbAffari.createFont();
			defaultFont.setBold(true);
			style2.setFont(defaultFont2);
			style2.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style2.setAlignment(HorizontalAlignment.CENTER);

			Cell titoloOra = rowTitoli.createCell(0);
			titoloOra.setCellValue("Ora");
			titoloOra.setCellStyle(style);
			Cell titoloPosizione = rowTitoli.createCell(1);
			titoloPosizione.setCellValue("Posizione");
			titoloPosizione.setCellStyle(style);
			Cell titoloSimbolo = rowTitoli.createCell(2);
			titoloSimbolo.setCellValue("Simbolo");
			titoloSimbolo.setCellStyle(style);
			Cell titoloTipo = rowTitoli.createCell(3);
			titoloTipo.setCellValue("Tipo");
			titoloTipo.setCellStyle(style);
			Cell titoloVolume = rowTitoli.createCell(4);
			titoloVolume.setCellValue("Volume");
			titoloVolume.setCellStyle(style);
			Cell titoloPrezzo = rowTitoli.createCell(5);
			titoloPrezzo.setCellValue("Prezzo");
			titoloPrezzo.setCellStyle(style);
			Cell titoloSL = rowTitoli.createCell(6);
			titoloSL.setCellValue("S/L");
			titoloSL.setCellStyle(style);
			Cell titoloTP = rowTitoli.createCell(7);
			titoloTP.setCellValue("T/P");
			titoloTP.setCellStyle(style);
			Cell titoloOra1 = rowTitoli.createCell(8);
			titoloOra1.setCellValue("Ora");
			titoloOra1.setCellStyle(style);
			Cell titoloPrezzo1 = rowTitoli.createCell(9);
			titoloPrezzo1.setCellValue("Prezzo");
			titoloPrezzo1.setCellStyle(style);
			Cell titoloCommissioni = rowTitoli.createCell(10);
			titoloCommissioni.setCellValue("Commissioni");
			titoloCommissioni.setCellStyle(style);
			Cell titoloSwap = rowTitoli.createCell(11);
			titoloSwap.setCellValue("Swap");
			titoloSwap.setCellStyle(style);
			Cell titoloProfitto = rowTitoli.createCell(12);
			titoloProfitto.setCellValue("Profitto");
			titoloProfitto.setCellStyle(style);

			Cell titoloOrarioDiApertura = rowTitoli1.createCell(0);
			titoloOrarioDiApertura.setCellValue("Orario di Apertura");
			titoloOrarioDiApertura.setCellStyle(style1);
			Cell titoloOrdine = rowTitoli1.createCell(1);
			titoloOrdine.setCellValue("Ordine");
			titoloOrdine.setCellStyle(style1);
			Cell titoloSimbolo1 = rowTitoli1.createCell(2);
			titoloSimbolo1.setCellValue("Simbolo");
			titoloSimbolo1.setCellStyle(style1);
			Cell titoloTipo1 = rowTitoli1.createCell(3);
			titoloTipo1.setCellValue("Tipo");
			titoloTipo1.setCellStyle(style1);
			Cell titoloVolume1 = rowTitoli1.createCell(4);
			titoloVolume1.setCellValue("Volume");
			titoloVolume1.setCellStyle(style1);
			Cell titoloPrezzo2 = rowTitoli1.createCell(5);
			titoloPrezzo2.setCellValue("Prezzo");
			titoloPrezzo2.setCellStyle(style1);
			Cell titoloSL1 = rowTitoli1.createCell(6);
			titoloSL1.setCellValue("S/L");
			titoloSL1.setCellStyle(style1);
			Cell titoloTP1 = rowTitoli1.createCell(7);
			titoloTP1.setCellValue("T/P");
			titoloTP1.setCellStyle(style1);
			Cell titoloOra2 = rowTitoli1.createCell(8);
			titoloOra2.setCellValue("Ora");
			titoloOra2.setCellStyle(style1);
			Cell titoloStato = rowTitoli1.createCell(9);
			titoloStato.setCellValue("Stato");
			titoloStato.setCellStyle(style1);
			Cell titoloCommento = rowTitoli1.createCell(10);
			titoloCommento.setCellValue("Commento");
			titoloCommento.setCellStyle(style1);

			Cell titoloOra3 = rowTitoli2.createCell(0);
			titoloOra3.setCellValue("Ora");
			titoloOra3.setCellStyle(style2);
			Cell titoloAffare = rowTitoli2.createCell(1);
			titoloAffare.setCellValue("Affare");
			titoloAffare.setCellStyle(style2);
			Cell titoloSimbolo2 = rowTitoli2.createCell(2);
			titoloSimbolo2.setCellValue("Simbolo");
			titoloSimbolo2.setCellStyle(style2);
			Cell titoloTipo2 = rowTitoli2.createCell(3);
			titoloTipo2.setCellValue("Tipo");
			titoloTipo2.setCellStyle(style2);
			Cell titoloDirezione = rowTitoli2.createCell(4);
			titoloDirezione.setCellValue("Direzione");
			titoloDirezione.setCellStyle(style2);
			Cell titoloVolume2 = rowTitoli2.createCell(5);
			titoloVolume2.setCellValue("Volume");
			titoloVolume2.setCellStyle(style2);
			Cell titoloPrezzo3 = rowTitoli2.createCell(6);
			titoloPrezzo3.setCellValue("Prezzo");
			titoloPrezzo3.setCellStyle(style2);
			Cell titoloOrdine1 = rowTitoli2.createCell(7);
			titoloOrdine1.setCellValue("Ordine");
			titoloOrdine1.setCellStyle(style2);
			Cell titoloCommissioni1 = rowTitoli2.createCell(8);
			titoloCommissioni1.setCellValue("Commissioni");
			titoloCommissioni1.setCellStyle(style2);
			Cell titoloSpese = rowTitoli2.createCell(9);
			titoloSpese.setCellValue("Spese");
			titoloSpese.setCellStyle(style2);
			Cell titoloSwap1 = rowTitoli2.createCell(10);
			titoloSwap1.setCellValue("Swap");
			titoloSwap1.setCellStyle(style2);
			Cell titoloProfitto1 = rowTitoli2.createCell(11);
			titoloProfitto1.setCellValue("Profitto");
			titoloProfitto1.setCellStyle(style2);
			Cell titoloBilancio = rowTitoli2.createCell(12);
			titoloBilancio.setCellValue("Bilancio");
			titoloBilancio.setCellStyle(style2);
			Cell titoloCommento1 = rowTitoli2.createCell(13);
			titoloCommento1.setCellValue("Commento");
			titoloCommento1.setCellStyle(style2);
			
			
			while (rowIterator.hasNext()) { 
				Row row = (Row) rowIterator.next(); // Legge riga file originale
				if (row.getRowNum() >= 8 && row.getRowNum() <= 144) { // Range di lettura dal file originale
					Row row1 = sheet1.createRow(x); // Scrittura alla linea Xesima del file di destinazione
					PreparedStatement posStmt = con.prepareStatement("INSERT INTO POSIZIONI (ORA_OPEN, POSIZIONE, SIMBOLO, TIPO, VOLUME, PREZZO, SL_PREZZO, TP_PREZZO, ORA_CLOSE, PREZZO_CLOSE, COMMISSIONI, SWAP, PROFITTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); //DA CONTINUARE

					Cell cellOra = row.getCell(0); // Lettura delle cell 0 (Colonna A) dal file di lettura
					Cell ora = row1.createCell(0); // Scrittura nella cella 0 (colonna A) di destinazione
					ora.setCellValue(cellOra.getStringCellValue());
					posStmt.setString(1, cellOra.getStringCellValue());
					Cell cellPosizione = row.getCell(1);
					Cell posizione = row1.createCell(1);
					posizione.setCellValue(cellPosizione.getNumericCellValue());
					posStmt.setString(2, "" + cellPosizione.getNumericCellValue());
					Cell cellSimbolo = row.getCell(2);
					Cell simbolo = row1.createCell(2);
					simbolo.setCellValue(cellSimbolo.getStringCellValue());
					posStmt.setString(3, cellSimbolo.getStringCellValue());
					Cell cellTipo = row.getCell(3);
					Cell tipo = row1.createCell(3);
					tipo.setCellValue(cellTipo.getStringCellValue());
					posStmt.setString(4, cellTipo.getStringCellValue());
					Cell cellVolume = row.getCell(4);
					Cell volume = row1.createCell(4);
					volume.setCellValue(cellVolume.getStringCellValue());
					posStmt.setString(5, cellVolume.getStringCellValue());
					Cell cellPrezzo = row.getCell(5);
					Cell prezzo = row1.createCell(5);
					prezzo.setCellValue(cellPrezzo.getNumericCellValue());
					posStmt.setString(6, "" + cellPrezzo.getNumericCellValue());
					Cell cellSL = row.getCell(6);
					Cell sl = row1.createCell(6);
					sl.setCellValue(cellSL.getNumericCellValue());
					posStmt.setString(7, "" + cellSL.getNumericCellValue());
					Cell cellTP = row.getCell(7);
					Cell tp = row1.createCell(7);
					tp.setCellValue(cellTP.getNumericCellValue());
					posStmt.setString(8, "" + cellTP.getNumericCellValue());
					Cell cellOra1 = row.getCell(8);
					Cell ora1 = row1.createCell(8);
					ora1.setCellValue(cellOra1.getStringCellValue());
					posStmt.setString(9, cellOra1.getStringCellValue());
					Cell cellPrezzo1 = row.getCell(9);
					Cell prezzo1 = row1.createCell(9);
					prezzo1.setCellValue(cellPrezzo1.getNumericCellValue());
					posStmt.setString(10, "" + cellPrezzo1.getNumericCellValue());
					Cell cellCommissioni = row.getCell(10);
					Cell commissioni = row1.createCell(10);
					commissioni.setCellValue(cellCommissioni.getNumericCellValue());
					posStmt.setString(11, "" +  cellCommissioni.getNumericCellValue());
					Cell cellSwap = row.getCell(11);
					Cell swap = row1.createCell(11);
					swap.setCellValue(cellSwap.getNumericCellValue());
					posStmt.setString(12, "" +  cellSwap.getNumericCellValue());
					Cell cellProfitto = row.getCell(12);
					Cell profitto = row1.createCell(12);
					profitto.setCellValue(cellProfitto.getNumericCellValue());
					posStmt.setString(13, "" +  cellProfitto.getNumericCellValue());
					
					posStmt.executeUpdate();
					x++;

				}
				if (row.getRowNum() >= 147 && row.getRowNum() <= 484) { // Qui inizia il ciclo degli ordini
					Row row1 = sheet2.createRow(y);
					PreparedStatement orStmt = con.prepareStatement("INSERT INTO ORDINI (ORARIO_APERTURA, ORDINE, SIMBOLO, TIPO, VOLUME, PREZZO, SL_PREZZO, TP_PREZZO, ORA, STATO, COMMENTO ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					Cell cellOrarioDiApertura = row.getCell(0); // Lettura delle cell 0 (Colonna A) dal file di lettura
					Cell orarioDiApertura = row1.createCell(0); // Scrittura nella cella 0 (colonna A) di destinazione
					orarioDiApertura.setCellValue(cellOrarioDiApertura.getStringCellValue());
					orStmt.setString(1, cellOrarioDiApertura.getStringCellValue());
					Cell cellOrdine = row.getCell(1); // Lettura delle cell 0 (Colonna A) dal file di lettura
					Cell ordine = row1.createCell(1); // Scrittura nella cella 0 (colonna A) di destinazione
					ordine.setCellValue(cellOrdine.getNumericCellValue());
					orStmt.setString(2, "" +  cellOrdine.getNumericCellValue());
					Cell cellSimbolo1 = row.getCell(2);
					Cell simbolo1 = row1.createCell(2);
					simbolo1.setCellValue(cellSimbolo1.getStringCellValue());
					orStmt.setString(3, cellSimbolo1.getStringCellValue());
					Cell cellTipo1 = row.getCell(3);
					Cell tipo1 = row1.createCell(3);
					tipo1.setCellValue(cellTipo1.getStringCellValue());
					orStmt.setString(4, cellTipo1.getStringCellValue());
					Cell cellVolume1 = row.getCell(4);
					Cell volume1 = row1.createCell(4);
					volume1.setCellValue(cellVolume1.getStringCellValue());
					orStmt.setString(5, cellVolume1.getStringCellValue());
					try {
						Cell cellPrezzo2 = row.getCell(5);
						Cell prezzo2 = row1.createCell(5);
						prezzo2.setCellValue(cellPrezzo2.getNumericCellValue());
						orStmt.setString(6, "" +  cellPrezzo2.getNumericCellValue());
					} catch (Exception e) {
						Cell cellPrezzo2 = row.getCell(5);
						Cell prezzo2 = row1.createCell(5);
						prezzo2.setCellValue(cellPrezzo2.getStringCellValue());
						orStmt.setString(6, cellPrezzo2.getStringCellValue());
					}
					Cell cellSL1 = row.getCell(6);
					Cell sl1 = row1.createCell(6);
					sl1.setCellValue(cellSL1.getNumericCellValue());
					orStmt.setString(7, "" + cellSL1.getNumericCellValue());
					Cell cellTP1 = row.getCell(7);
					Cell tp1 = row1.createCell(7);
					tp1.setCellValue(cellTP1.getNumericCellValue());
					orStmt.setString(8, "" +  cellTP1.getNumericCellValue());
					Cell cellOra2 = row.getCell(8);
					Cell ora2 = row1.createCell(8);
					ora2.setCellValue(cellOra2.getStringCellValue());
					orStmt.setString(9, cellOra2.getStringCellValue());
					Cell cellStato = row.getCell(9);
					Cell stato = row1.createCell(9);
					stato.setCellValue(cellStato.getStringCellValue());
					orStmt.setString(10, cellStato.getStringCellValue());
					Cell cellCommento = row.getCell(11);
					Cell commento = row1.createCell(10);
					commento.setCellValue(cellCommento.getStringCellValue());
					orStmt.setString(11, cellCommento.getStringCellValue());
					
					orStmt.executeUpdate();
					y++;

				}

				if (row.getRowNum() >= 487 && row.getRowNum() <= 765) {
					Row row1 = sheet3.createRow(z);
					PreparedStatement afStmt = con.prepareStatement("INSERT INTO AFFARI (ORA, AFFARE, SIMBOLO, TIPO, DIREZIONE, VOLUME, PREZZO, ORDINE, COMMISSIONI, SPESE, SWAP, PROFITTO, BILANCIO, COMMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

					Cell cellOra3 = row.getCell(0);
					Cell ora3 = row1.createCell(0);
					ora3.setCellValue(cellOra3.getStringCellValue());
					afStmt.setString(1, cellOra3.getStringCellValue());
					Cell cellAffare = row.getCell(1);
					Cell affare = row1.createCell(1);
					affare.setCellValue(cellAffare.getNumericCellValue());
					afStmt.setString(2, "" + cellAffare.getNumericCellValue());
					Cell cellSimbolo2 = row.getCell(2);
					Cell simbolo2 = row1.createCell(2);
					simbolo2.setCellValue(cellSimbolo2.getStringCellValue());
					afStmt.setString(3, cellSimbolo2.getStringCellValue());
					Cell cellTipo2 = row.getCell(3);
					Cell tipo2 = row1.createCell(3);
					tipo2.setCellValue(cellTipo2.getStringCellValue());
					afStmt.setString(4, cellTipo2.getStringCellValue());
					Cell cellDirezione = row.getCell(4);
					Cell direzione = row1.createCell(4);
					direzione.setCellValue(cellDirezione.getStringCellValue());
					afStmt.setString(5, cellDirezione.getStringCellValue());
					Cell cellVolume2 = row.getCell(5);
					Cell volume2 = row1.createCell(5);
					volume2.setCellValue(cellVolume2.getStringCellValue());
					afStmt.setString(6, cellVolume2.getStringCellValue());
					Cell cellPrezzo3 = row.getCell(6);
					Cell prezzo3 = row1.createCell(6);
					prezzo3.setCellValue(cellPrezzo3.getNumericCellValue());
					afStmt.setString(7, "" +  cellPrezzo3.getNumericCellValue());
					Cell cellOrdine1 = row.getCell(7);
					Cell ordine1 = row1.createCell(7);
					ordine1.setCellValue(cellOrdine1.getNumericCellValue());
					afStmt.setString(8, "" + cellOrdine1.getNumericCellValue());
					Cell cellCommissioni1 = row.getCell(8);
					Cell commissioni1 = row1.createCell(8);
					commissioni1.setCellValue(cellCommissioni1.getNumericCellValue());
					afStmt.setString(9, "" + cellCommissioni1.getNumericCellValue());
					Cell cellSpese = row.getCell(9);
					Cell spese = row1.createCell(9);
					spese.setCellValue(cellSpese.getNumericCellValue());
					afStmt.setString(10, "" + cellSpese.getNumericCellValue());
					Cell cellSwap1 = row.getCell(10);
					Cell swap1 = row1.createCell(10);
					swap1.setCellValue(cellSwap1.getNumericCellValue());
					afStmt.setString(11, "" + cellSwap1.getNumericCellValue());
					Cell cellProfitto1 = row.getCell(11);
					Cell profitto1 = row1.createCell(11);
					profitto1.setCellValue(cellProfitto1.getNumericCellValue());
					afStmt.setString(12, "" + cellProfitto1.getNumericCellValue());
					Cell cellBilancio = row.getCell(12);
					Cell bilancio = row1.createCell(12);
					bilancio.setCellValue(cellBilancio.getNumericCellValue());
					afStmt.setString(13, "" + cellBilancio.getNumericCellValue());
					Cell cellCommento1 = row.getCell(13);
					Cell commento1 = row1.createCell(13);
					commento1.setCellValue(cellCommento1.getStringCellValue());
					afStmt.setString(14, cellCommento1.getStringCellValue());
					
					afStmt.executeUpdate();
					z++;
				}

			}

			System.out.print("file creato correttamente");
			file.close();
			try {
				FileOutputStream filePosizioni = new FileOutputStream("C:\\Luciano\\addestramento\\posizioni.xlsx");
				wbPosizioni.write(filePosizioni);
				FileOutputStream fileOrdini = new FileOutputStream("C:\\Luciano\\addestramento\\ordini.xlsx");
				wbOrdini.write(fileOrdini);
				FileOutputStream fileAffari = new FileOutputStream("C:\\Luciano\\addestramento\\affari.xlsx");
				wbAffari.write(fileAffari);

				System.out.println("Files creati con successo");
			} catch (IOException e) {
				e.printStackTrace();
			}
			con.close();
		} catch (Exception ex) {
			// Display message when exceptions occurs
			System.err.println(ex);
		}
	}
}
