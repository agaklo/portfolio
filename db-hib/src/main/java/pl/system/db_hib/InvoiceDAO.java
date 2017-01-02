package pl.system.db_hib;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.system.db_hib.entities.Customer;
import pl.system.db_hib.entities.Invoice;
import pl.system.db_hib.entities.InvoiceItem;

public class InvoiceDAO {
	
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers(Session session){
		return session.createQuery(
        		"from "+Customer.class.getName()).list();                
	}	
	
	@SuppressWarnings("unchecked")
	public List<InvoiceItem> getInvoiceItems(Session session){
		return session.createQuery(
        		"from "+InvoiceItem.class.getName()).list();                
	}
	
	@SuppressWarnings("unchecked")
	public List<Invoice> getInvoices(Session session){
		return session.createQuery(
        		"from "+Invoice.class.getName()).list();                
	}
	
	@SuppressWarnings("unchecked")
	public List<Invoice> getInvoiceBySeller(Session session, Customer seller) {
        return session.createQuery("from "+Invoice.class.getName()+" invoice where invoice.seller=?")
                .setParameter(0, seller)
                .list();
    }
}