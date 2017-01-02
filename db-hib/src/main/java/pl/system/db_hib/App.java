package pl.system.db_hib;

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
		
    public static void main( String[] args )
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
        
        for(Customer c: customers){        	
        	List<Invoice> invoicesBySeller = invoiceDAO.getInvoiceBySeller(session,c);
        	for(Invoice i: invoicesBySeller){
        		System.out.println("Invoices by customer:"+c.getName()+", id:" +c.getId() );
        		System.out.println(gson.toJson( i.toJson() ) );
        	}
        }
        session.close();
    	sessionFactory.close();
    	
    }
}