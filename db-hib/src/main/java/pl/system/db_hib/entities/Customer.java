package pl.system.db_hib.entities;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Entity
@Table(name = "customer")
public class Customer {

	public Customer(){
		super();
	}
	public Customer(String id, String name, String address1, String address2, String address3, String nip) {
		super();
		this.id = id;
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.nip = nip;
	}
	
	public Customer(String name, String address1, String address2, String address3, String nip) {
		super();
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.nip = nip;
	}
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;
	
	@Column(name = "address3")
	private String address3;
	
	@Column(name = "nip")
	private String nip;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address1=" + address1 + ", address2=" + address2
				+ ", address3=" + address3 + ", nip=" + nip + "]";
	}  
	public JsonElement toJson(){
		JsonObject object = new JsonObject();
	    
		object.addProperty("id", id);
	    object.addProperty("name", getName());
	    object.addProperty("address1", getAddress1());
	    object.addProperty("address2", getAddress2());
	    object.addProperty("address3", getAddress3());
	    object.addProperty("nip", getNip());
	    
	    return object;
	}
	
}