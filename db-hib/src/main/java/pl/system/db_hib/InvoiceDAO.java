package pl.system.db_hib;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.system.db_hib.entities.Customer;
import pl.system.db_hib.entities.Invoice;
import pl.system.db_hib.entities.InvoiceItem;

public class InvoiceDAO {
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers(){
		Session session = sessionFactory.openSession();
		List<Customer> customers = session.createQuery(
        		"from "+Customer.class.getName()).list();        
        session.close();        
        return customers;
	}	
	@SuppressWarnings("unchecked")
	public List<InvoiceItem> getInvoiceItems(){
		Session session = sessionFactory.openSession();
		List<InvoiceItem> customers = session.createQuery(
        		"from "+InvoiceItem.class.getName()).list();        
        session.close();        
        return customers;
	}
	@SuppressWarnings("unchecked")
	public List<Invoice> getInvoices(){
		Session session = sessionFactory.openSession();
		List<Invoice> customers = session.createQuery(
        		"from "+Invoice.class.getName()).list();        
        session.close();        
        return customers;
	}
}