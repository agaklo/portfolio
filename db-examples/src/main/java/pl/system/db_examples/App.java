package pl.system.db_examples;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.system.db_examples.entities.Invoice;

/**
 * Hello world!
 *
 */
public class App 
{
	
	public static final String USER = "root";
	public static final String PASSWORD = "12345678";

	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost/invoicedb?"
                                            + "user="+USER+"&password="+PASSWORD;
 
    
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Database jdbc test" );
    	DbConnection dbConn = new DbConnection(MYSQL_DRIVER, MYSQL_URL);
    	InvoiceDAO invoiceDAO = new InvoiceDAO();
    	List<Invoice> invoices = invoiceDAO.getInvoices(dbConn.getConnection());
    	
    	//zapisujemy do jsona
    	//Gson gson = new Gson();
    	
    	//tworzymy objekt gson z ustawionym ładnym wypisywaniem
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	
    	String json = gson.toJson(invoices);
    	System.out.println(json);
    	    
    }
}
