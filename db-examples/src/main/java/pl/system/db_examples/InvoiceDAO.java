package pl.system.db_examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.system.db_examples.entities.Customer;
import pl.system.db_examples.entities.Invoice;
import pl.system.db_examples.entities.InvoiceItem;


public class InvoiceDAO {
	public List<Invoice> getInvoices(Connection dbConn) throws Exception{
		Statement statement = dbConn.createStatement();
		ResultSet resultSet = statement.executeQuery(
				"select id, "
				+ "invoice_no, "
				+ "creation_date, "
				+ "document_date, "
				+ "seller_id, "
				+ "buyer_id "
				+ "from invoice;");
        List<Invoice> invoices = new ArrayList<Invoice>();
		while(resultSet.next()){
			Invoice i 		= new Invoice();
            i.setId(resultSet.getString("id"));
            i.setInvoiceNo( resultSet.getString("invoice_no") );
            i.setCreationDate( resultSet.getString("creation_date") );
            i.setDocumentDate( resultSet.getString("document_date") );
            i.setSellerId( resultSet.getString("seller_id") );
            i.setBuyerId( resultSet.getString("buyer_id") );
            
            i.setSeller( getCustomerById(dbConn, i.getSellerId() ) );
            i.setBuyer( getCustomerById(dbConn, i.getBuyerId() ) );
            i.setItems( getInvoiceItem(dbConn, i.getId() ));
            
            invoices.add(i);
        }
		
		return invoices;
	}	
	
	public Customer getCustomerById(Connection dbConn, String id) throws Exception{
		 PreparedStatement preparedStatement = dbConn.prepareStatement(
				"select id, name, address1, address2, address3, nip from customer where id=?");
         preparedStatement.setString(1,id);
         ResultSet resultSet = preparedStatement.executeQuery();
         Customer c = new Customer();
         if( resultSet.next() ){
        	 c.setId(resultSet.getString("id") );
        	 c.setName(resultSet.getString("name") );
        	 c.setAddress1(resultSet.getString("address1") );
        	 c.setAddress2(resultSet.getString("address2") );
        	 c.setAddress3(resultSet.getString("address3") );
        	 c.setNip(resultSet.getString("nip") );
        	 
             //tu wynik moze byc tylko jeden (id jest kluczem)!
        	 if( resultSet.next() ){
        		 throw new Exception("Non unique result for Customer with id:"+id);
        	 } 
         }else{
        	 throw new Exception("Cant find Customer by id:"+id);
         }
         return c;         
	}
	
	public List<InvoiceItem> getInvoiceItem(Connection dbConn, String invoiceId) throws Exception{
		 PreparedStatement preparedStatement = dbConn.prepareStatement(
				"select id,invoice_id,description,vat_rate,vat_amount,amount_net,amount_total from invoice_item where invoice_id=?");
        preparedStatement.setString(1,invoiceId);
        ResultSet resultSet = preparedStatement.executeQuery();
        Customer c = new Customer();
        List<InvoiceItem> items = new ArrayList<InvoiceItem>();
		while(resultSet.next()){
			InvoiceItem ii 		= new InvoiceItem();
			ii.setId(resultSet.getString("id"));
			ii.setInvoiceId(resultSet.getString("invoice_id"));
			ii.setDescription(resultSet.getString("description"));
			ii.setVatRate(resultSet.getString("vat_rate"));
			ii.setVatAmount(resultSet.getString("vat_amount"));
			ii.setAmountNet(resultSet.getString("amount_net"));
			ii.setAmountTotal(resultSet.getString("amount_total"));
			items.add(ii);
        }
		         
		return items;
	}
	
}
