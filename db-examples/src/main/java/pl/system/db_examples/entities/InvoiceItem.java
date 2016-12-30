package pl.system.db_examples.entities;

public class InvoiceItem {
	private String id;
	private String invoiceId;
	private String description;
	private String vatRate;
	private String vatAmount;
	private String amountNet;
	private String amountTotal;
	@Override
	public String toString() {
		return "InvoiceItem [id=" + id + ", invoiceId=" + invoiceId + ", description=" + description + ", vatRate="
				+ vatRate + ", vatAmount=" + vatAmount + ", amountNet=" + amountNet + ", amountTotal=" + amountTotal
				+ "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
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
		
}
