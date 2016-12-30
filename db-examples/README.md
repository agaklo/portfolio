Prosty przykład wykorzystania jdbc
==================================

Na początek zakładamy bazę faktur invoicedb.

(najlepiej stanąć w terminalu w katalogu projektu w sql, wówczas nei sa potrzebne zadne scieżki w komendach poniżej).

1. mysql -uroot -p12345678 (user i hasło)
2. w myslq piszemy: source [scieżka/]create_db.sql
3. w myslq piszemy: source [scieżka/]insert_data.sql

sprawdzamy czy wszystko się dodało:
show tables;
select * from customer;
select * from invoice;
select * from invoice_item;

W skrypcie insert_data.sql do wygenerowania poprawnych numerów NIP używamy online generatorów np: http://www.bogus.ovh.org/generatory/all.html

Powtórka z MySql
----------------
* show databases        - pokazuje istniejace bazy
* use nazwabazy         - wybranie bazy do dalszego użytkowania
* SELECT database();    - zwraca aktywną baze (wybraną wczesniej z wykorzystaniem use
* show tables           - pokazuje tabele
* describe nazwa_tabeli - szczegóły tabeli;

Modyfikacje:

* usuwanie kolumny:  alter table invoice_item drop column amount;
* dodawanie kolumny: alter table invoice_item add column amount_net decimal(19,4) not null;

Usuwanie:
* delete from nazwa_tabeli; - danych z tabeli
* drop nazwa_tabeli - usuniecie calej tabeli wraz z definicja (trzeba bedzie od nowa zalozyc)

więcej np na:
http://andron.wszib.edu.pl/mysql-podstawowe-polecenia/