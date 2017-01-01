use invoicedb;

insert into customer(name, address1, address2, nip) 
       values ('Seller It corp', 'Radosna 1', '01-234 Warszawa', '9311083719');
SET @seler_id = LAST_INSERT_ID();

insert into customer(name, address1, address2, nip) 
       values ('Buyer Ltd.', 'Wesoła 3', '07-432 Warszawa', '2590343250');
SET @buyer_id = LAST_INSERT_ID();

insert into invoice (invoice_no, document_date, seller_id, buyer_id) values('0001/2016/D1', '2016-12-29', @seler_id, @buyer_id);
SET @invoice_id = LAST_INSERT_ID();

insert into invoice_item(invoice_id, description, vat_rate, vat_amount, amount_net, amount_total) 
values 
(@invoice_id, 'instalacja komputera', '8.00', '8.00', '100.00', '108.00'),
(@invoice_id, 'oprogramowanie', '23.00', '23.00', '100.00', '123.00');

commit;