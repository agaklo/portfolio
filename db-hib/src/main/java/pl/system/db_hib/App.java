package pl.system.db_hib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import pl.system.db_hib.entities.Customer;
import pl.system.db_hib.entities.Invoice;
import pl.system.db_hib.entities.InvoiceItem;


public class App 
{
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}	
		
    public static void main( String[] args ) throws ParseException
    {
        System.out.println( "Hibernate test" );
        
        SessionFactory sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
		
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        
        List<Customer> customers = invoiceDAO.getCustomers(session);
        for(Customer c: customers){
        	System.out.println(c.toString());
        }
        
        List<InvoiceItem> invoiceItems = invoiceDAO.getInvoiceItems(session);
        for(InvoiceItem i: invoiceItems){
        	System.out.println(i.toString());
        }
        
        JsonArray jA = new JsonArray();
        List<Invoice> invoices = invoiceDAO.getInvoices(session);
        for(Invoice i: invoices){        	
        	jA.add(i.toJson());
        }
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(jA);
        System.out.println(prettyJson);
        
        System.out.println( "=================================================" );
		System.out.println( "==                Additional tests             ==" );
		System.out.println( "=================================================" );
		        
        for(Customer c: customers){        	
        	List<Invoice> invoicesBySeller = invoiceDAO.getInvoiceBySeller(session,c);
        	for(Invoice i: invoicesBySeller){
        		System.out.println("Invoices by customer:"+c.getName()+", id:" +c.getId() );
        		System.out.println(gson.toJson( i.toJson() ) );
        	}
        }
        
        Date from 	= new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-30");
        Date to 	= new SimpleDateFormat("yyyy-MM-dd").parse("2017-01-01");
        System.out.println("Searching for invoices by date from:"+from.toString()+", to:" +to.toString() );
        
        List<Invoice> invoicesByDate = invoiceDAO.getInvoiceByDocumentDate(session, from, to);
        if( invoicesByDate.isEmpty() ){
        	System.out.println("No invoices found for document date from:"+from.toString()+", to:" +to.toString() );
        }
        
        for(Invoice i: invoicesByDate){
    		System.out.println("Invoices by date from:"+from.toString()+", to:" +to.toString() );
    		System.out.println(gson.toJson( i.toJson() ) );
    	}
                
        session.close();
    	sessionFactory.close();
    	
    }
}