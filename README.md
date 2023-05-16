# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)


docker start my_postgres

Zaliczenie przedmiotu następuje po oddaniu projektu zrealizowanego w czasie trwania semestru. Tematem projektu jest aplikacja klient serwer zrealizowana zgodnie z wzorcem RESTful (Java API for RESTful Web Services JAX-RS), z wykorzystaniem technologii WebSocket (Java API for WebSocket) lub z interfejsem GraphQL. Część serwerowa wykorzystuje serwer bazy danych (relacyjny bądź NoSQL). Klient może być zrealizowany z wykorzystaniem serwisu WWW i języka Javascript lub może to być aplikacja mobilna. Temat projektu należy uzgodnić z prowadzącym laboratorium.
Zaliczenie projektu następuje po zrealizowaniu następujących elementów:

serwer - aplikacja serwerowa uruchomiona w ramach chmur: IBM Cloud, Microsoft Azure, Google Cloud, AWS Cloud lub z wykorzystaniem konteneryzacji;
serwer - realizacja w technologii platformy Jakarta EE lub dodatkowo z wykorzystaniem technologii Spring i technologii Spring Boot;
serwer - dostęp do danych zrealizowany w oparciu o odwzorowanie obiektowo-relacyjne JPA lub Spring Data;
serwer - baza danych w rozwiązaniu chmurowym DBaaS;
serwer - obsługa zdarzeń z wykorzystaniem programowania aspektowego - język AspectJ lub Spring AOP;
klient - opracowany z wykorzystaniem serwisu WWW i języka JavaScript, aplikacja typu SPA lub jako aplikacja mobilna;
klient - w ramach aplikacji można wykorzystać odpowiednie frameworki (np. AngularJS, React, Vue.js czy Cordova);
dokumentacja - opis funkcjonalności opracowanej aplikacji zarówno części serwerowej jak i klienta;
dokumentacja - dołączone odpowiednie diagramy UML dla części serwerowej i klient;
dokumentacja - prezentacja wybranych testów jednostkowych i wdrożeniowych dla części serwerowej i klienta;
dokumentacja - informacja uruchomieniowa (wdrożeniowa);
dokumentacja - kod aplikacji należy udokumentować przy pomocy JavaDoc (w przypadku aplikacji mobilnej również);
dokumentacja - podstawowy podręcznik użytkownika.


założenia funkcjonalne:
możliwość zobaczenia listy aktualnych rekordów - done
możliwość sprawdzenia historii edycji danego rekordu (gdzie rekord to na przykład location) - done
możliwość zobaczenia stanu listy rekordów aktualnych w wybranym danym dniu i czasie - done
możliwość sprawdzenia ilości rekordów zmodyfikowanych pomiędzy dwoma datami 
oraz szczegółów zmian dla wybranego rekordu
możliwość dodania i edycji rekordów oraz deaktywacji i reaktywacji - done


## to produce a jar:
click Maven on the right sidebar
then navigate to zti_projekt_try0
then navigate to Lifecycle
click package

## then you can copy the jar to remote server:
scp zti_projekt_try0-0.0.1-SNAPSHOT.jar  root@143.42.61.153:~/


## backup and restore postgres database:
first backup:
docker exec -t my_postgres pg_dump -U postgres -d zti_projekt_try_with_artificial_id > database_backup.sql

then copy this database_backup.sql file to remote linode server:
scp ./database_backup.sql root@143.42.61.153:~/try2/

then create a postgresql database docker container with mapped volume to arbitrarly selected directory (in this case /docker-storage/postgres_data):
docker run --name my_postgres -v /docker-storage/postgres_data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=QSmQKbAumfeQL0kxX82Wk1qRqpEIbXyTWMswc0fhythuGCX -p 5432:5432 -d postgres

then copy the database_backup.sql file from remote host into the newly created docker container
docker cp ./database_backup.sql my_postgres:/

then get shell into that docker:
docker exec -it my_postgres bash

and run the sql script that will restore the database:
psql -U postgres -d postgres -f /database_backup.sql

