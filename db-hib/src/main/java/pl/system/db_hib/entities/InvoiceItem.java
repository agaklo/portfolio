package pl.system.db_hib.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Entity
@Table(name = "invoice_item")
public class InvoiceItem {
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "id")	
	private String id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "invoice_id", nullable = false)
	private Invoice invoice;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "vat_rate")
	private String vatRate;
	
	@Column(name = "vat_amount")
	private String vatAmount;
	
	@Column(name = "amount_net")
	private String amountNet;
	
	@Column(name = "amount_total")
	private String amountTotal;
		
	
	public InvoiceItem(){}
		
	public InvoiceItem(Invoice invoice, String description, String vatRate, String vatAmount,
			String amountNet, String amountTotal) {
		super();
		this.invoice = invoice;
		this.description = description;
		this.vatRate = vatRate;
		this.vatAmount = vatAmount;
		this.amountNet = amountNet;
		this.amountTotal = amountTotal;
	}
	
	public InvoiceItem(String id, Invoice invoice, String description, String vatRate, String vatAmount,
			String amountNet, String amountTotal) {
		super();
		this.id = id;
		this.invoice = invoice;
		this.description = description;
		this.vatRate = vatRate;
		this.vatAmount = vatAmount;
		this.amountNet = amountNet;
		this.amountTotal = amountTotal;
	}
	@Override
	public String toString() {
		return "InvoiceItem [id=" + id + ", invoice=" + invoice.toString() + ", description=" + description + ", vatRate="
				+ vatRate + ", vatAmount=" + vatAmount + ", amountNet=" + amountNet + ", amountTotal=" + amountTotal
				+ "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVatRate() {
		return vatRate;
	}
	public void setVatRate(String vatRate) {
		this.vatRate = vatRate;
	}
	public String getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}
	public String getAmountNet() {
		return amountNet;
	}
	public void setAmountNet(String amountNet) {
		this.amountNet = amountNet;
	}
	public String getAmountTotal() {
		return amountTotal;
	}
	public void setAmountTotal(String amountTotal) {
		this.amountTotal = amountTotal;
	}
	
	public JsonElement toJson(){
		JsonObject object = new JsonObject();
	    object.addProperty("id", id);
	    object.addProperty("invoice_no", getInvoice().getInvoiceNo());
	    object.addProperty("description", getDescription());
	    object.addProperty("vat_rate", getVatRate());
	    object.addProperty("vat_amount", getVatAmount());
	    object.addProperty("amount_net", getAmountNet());
	    object.addProperty("amount_total", getAmountTotal());
	    return object;
	}	
}