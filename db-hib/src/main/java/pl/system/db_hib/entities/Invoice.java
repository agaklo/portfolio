package pl.system.db_hib.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Entity
@Table(name = "invoice")
public class Invoice {
	public Invoice() {
		super();
	}
	
	public Invoice(String id, String invoiceNo, String creationDate, String documentDate, Customer seller, Customer buyer) {
		super();
		this.id = id;
		this.invoiceNo = invoiceNo;
		this.creationDate = creationDate;
		this.documentDate = documentDate;
		this.seller = seller;
		this.buyer = buyer;
	}
	
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceNo=" + invoiceNo + ", creationDate=" + creationDate + ", documentDate="
				+ documentDate + ", seller=" + seller + ", buyer=" + buyer + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	public Customer getSeller() {
		return seller;
	}

	public void setSeller(Customer seler) {
		this.seller = seller;
	}

	public Customer getBuyer() {
		return buyer;
	}

	public void setBuyer(Customer buyer) {
		this.buyer = buyer;
	}

	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}

	
	
	
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "id")		
	private String id;
	
	@Column(name = "invoice_no")
	private String invoiceNo;
	
	@Column(name = "creation_date")
	private String creationDate;
	
	@Column(name = "document_date")
	private String documentDate;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="seller_id")
	private Customer seller;
		
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="buyer_id")
	private Customer buyer;

	@OneToMany(mappedBy = "invoice", fetch=FetchType.EAGER)
	List<InvoiceItem> items;
	
	public JsonElement toJson(){
		JsonObject object = new JsonObject();
	    object.addProperty("id", id);
	    object.addProperty("invoice_no", invoiceNo);
	    object.addProperty("creation_date", creationDate);
	    object.addProperty("document_date", documentDate);
	    
	    object.add("seller", seller.toJson() );
	    object.add("buyer", buyer.toJson() );
	    
	    JsonArray jA = new JsonArray(); 
	    for(InvoiceItem i: items){
	    	jA.add(i.toJson());	
	    }
	    
	    object.add("items", jA);
	    
	    return object;	    
	}
	
}