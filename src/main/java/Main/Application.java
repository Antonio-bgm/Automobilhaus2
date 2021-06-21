package Main;

import CRUD.DBStatement;
import Guinew.FahrzeugCrudUi;
import Guinew.KundeCrudUi;
import SAX.Fahrzeug;
import SAX.Kunde;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class Application {

    private static ArrayList<Kunde> Kunden;
    private static ArrayList<Fahrzeug> Fahrzeuge;

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DBStatement dbStatement=new DBStatement();
        

            //Properties props= new Properties();
            //String pathFahrzeug=props.getProperty("path1");
            //String pathKunde=props.getProperty("path2");
            //System.out.println(path1);

            //File inputFileFahrzeug = new File(pathFahrzeug);
            //File inputFileKunde = new File(pathKunde);
            //File inputFile = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Fahrzeug.xml");
            //File inputFile2 = new File("C:\\Users\\anton\\IdeaProjects\\Automobilhaus\\src\\main\\java\\XML\\Kunde.xml");

            //UserHandlerFahrzeug userhandlerFahrzeug = new UserHandlerFahrzeug();
            //UserHandlerKunde userhandlerKunde = new UserHandlerKunde();

            //saxParser.parse(inputFileFahrzeug, userhandlerFahrzeug);
            //saxParser.parse(inputFileKunde,userhandlerKunde);
            //ArrayList<Fahrzeug> Fahrzeuge = userhandlerFahrzeug.getFList();
            //ArrayList<Kunde> Kunden =userhandlerKunde.getKList();
            //ImportKunde.importKunde();


            try {

                dbStatement.checkDBConnection();
                System.out.println("Verbindung aufgebaut");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            try{
                dbStatement.createTable();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try{
                dbStatement.showtables();
            } catch (Exception e) {
                e.printStackTrace();
            }

        try{
            dbStatement.showtables2();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String hashFahrzeug = ImportFahrzeug.getHashImportFahrzeug();
            if (!(dbStatement.returnDuplicateImportFahrzeug(hashFahrzeug)))
            {
                Fahrzeuge = ImportFahrzeug.importFahrzeug();
                System.out.println("Hier wurde geimported");
                for (Fahrzeug emp : Fahrzeuge)
                    try {
                        dbStatement.insertDataFahrzeug(emp.getFahrzeugtyp(), emp.getFahrzeugbezeichnung(), emp.getHersteller(), emp.getLeistung(), emp.getVerkaufspreise(),hashFahrzeug);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } }else{
                System.out.println("Import ist schon auf der DB. Siehe Hash "+hashFahrzeug);
            }

        } catch(Exception e){e.printStackTrace();}

        try{ String hashKunde=ImportKunde.getHashImportKunde();
            if(!(dbStatement.returnDuplicateImportKunde(hashKunde)))
            {
            Kunden=ImportKunde.importKunde();
            for (Kunde emp: Kunden)
                try {
                    dbStatement.insertDataKunde(emp.getNachname(), emp.getVorname(), emp.getAnschrift(),hashKunde);

                } catch(Exception e){
                    e.printStackTrace();
                }}else{
                System.out.println("Import ist schon auf der DB. Si" +
                        "ehe Hash "+hashKunde);
            }
        }
        catch(Exception e){e.printStackTrace();}






			 /*
            try {
				dbStatement.getDataFahrzeug();
			} catch (Exception e) {
				e.printStackTrace();
			}

            try {
                dbStatement.searchKunde("jas");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                dbStatement.getAllKunden();
            } catch (Exception e) {
                e.printStackTrace();
            }
*/
            try {KundeCrudUi kundeCrudUi=new KundeCrudUi();
                kundeCrudUi.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                FahrzeugCrudUi fahrzeugCrudUi=new FahrzeugCrudUi();
                fahrzeugCrudUi.start1();
            } catch (Exception e) {
                e.printStackTrace();
            }







    }
}