package pl.system.db_examples.entities;

import java.util.List;

public class Invoice {
	
	@Override
	public String toString() {
		return "Invoice [id=" + id + ", invoiceNo=" + invoiceNo + ", creationDate=" + creationDate + ", documentDate="
				+ documentDate + ", sellerId=" + sellerId + ", buyerId=" + buyerId + ", seller=" + seller + ", buyer="
				+ buyer + ", items=" + items + "]";
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
	public void setInvoiceNo(String invoice_no) {
		this.invoiceNo = invoice_no;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creation_date) {
		this.creationDate = creation_date;
	}
	public String getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(String document_date) {
		this.documentDate = document_date;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String seller_id) {
		this.sellerId = seller_id;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyer_id) {
		this.buyerId = buyer_id;
	}
	
	public Customer getSeller() {
		return seller;
	}
	public void setSeller(Customer seller) {
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

	private String id;
	private String invoiceNo;
	private String creationDate;
	private String documentDate;	
	private String sellerId;
	private String buyerId;	
	private Customer seller;
	private Customer buyer;
	private List<InvoiceItem> items;
	
	
}
