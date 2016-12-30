CREATE DATABASE IF NOT EXISTS invoicedb;
use invoicedb;

create table customer (
    id int NOT NULL AUTO_INCREMENT primary key,
    name varchar(255) not null,
    address1 varchar(255) not null,
    address2 varchar(255),
    address3 varchar(255),
    nip varchar(11)    
);

create table invoice  (
	id 				int NOT NULL AUTO_INCREMENT primary key,
	invoice_no 		varchar(255) not null,
	creation_date   datetime not null default CURRENT_TIMESTAMP,
	document_date   date not null,	
	seller_id 		int not null,
	buyer_id 		int not null,
	FOREIGN KEY fk_seller_id(seller_id) REFERENCES customer(id),
	FOREIGN KEY fk_buyer_id(buyer_id) REFERENCES customer(id)
);

create table invoice_item  (
    id 				int NOT NULL AUTO_INCREMENT primary key,
    invoice_id      int not null,
    description 	varchar(255) not null,
	vat_rate 		decimal(5,2) not null,
	vat_amount 		decimal(19,4) not null,
	amount_net 		decimal(19,4) not null,
	amount_total 	decimal(19,4) not null,
	FOREIGN KEY fk_invoice_id(invoice_id) REFERENCES invoice(id)
);

commit;
