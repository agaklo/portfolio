Prosty przykład wykorzystania hibernate
=======================================

Na początek zakładamy bazę invoicedb

(najlepiej stanąć w terminalu w katalogu projektu w sql, wówczas nei sa potrzebne zadne scieżki w komendach poniżej).

1. mysql -uroot -p12345678 (user i hasło)
2. w myslq piszemy: source [scieżka/]create_db.sql
3. w myslq piszemy: source [scieżka/]insert_data.sql

Problemy, o których zawsze należy pamiętać:
------------------------------------------
1. w zapytaniach nalezy używać nazwy klasy kwalifikowanej pakietem, jesli encja nie znajduje sie w tym samym pakiecie: session.createQuery( "from "+Invoice.class.getName() )
2. W przypadku relacji i fetchowania opoznionego FetchType.LAZY nie można robić tak:

Session session = sessionFactory.openSession();
List<Invoice> customers = session.createQuery(
		"from "+Invoice.class.getName()).list();        
session.close();

for(Invoice i: invoices){
	System.out.println(i.toString());
}

z obiektami wyciąganymi w trybie lazy można pracować tylko przed komitem, czy zamknięciem sesji.
        
3. serializowanie do jsona w prosty sposob powoduje problemy ze wzgledu na cykliczną zależność (tu np Invoice i InvoiceItem). Dlatego chyba najlepiej samemu zaimplementować metode toJson na parencie relacji (np. Invoice).
