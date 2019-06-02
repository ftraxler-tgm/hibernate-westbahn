# "Komponentenbasierte Programmierung"

## Aufgabenstellung
Die detaillierte [Aufgabenstellung](TASK.md) beschreibt die notwendigen Schritte zur Realisierung.



Bean Pojo + @



Um das Projekt als gradle Projekt zu initialisieren führen wir folgendes aus:

```bash
gradle idea
```
Das Programm startet man mit:
```bash
gradle run
```

Logging auf Info beschschränken indem in der Main Methode ganze oben folgendes einfügt ```Logger.getRootLogger().setLevel(Level.INFO);```

## Implementierung

#### PersistentUnit Definition

Dafür verwendet man die Annotation **@PersistenceUnit**:

```java
@PersistenceUnit(
			unitName = "westbahn"
)
```

Der UnitName muss mit der selbe sein wie der persistence-unit name im persistence.xml File. 

```xml
<persistence-unit name="westbahn">
```

#### Alle persistent Klassendefinition definieren

Dafür fügt man im **persistence.xml** File <class>model.Klassenname</class> hinzu:

```xml
<persistence-unit name="westbahn">
        <class>model.Bahnhof</class>
            .....
    <properties>
			....
    </properties>
  </persistence-unit>
```

#### Entities hinzufügen

```java
@Entity
@NamedQueries({@NamedQuery(name="Benutzer.getAll",query="SELECT b from Benutzer b")})
public class Benutzer {
```

* @Entity steht dafür das diese Klasse als Entität gesehen werden soll

* @NamedQueries damit können wir in der Klammer eigene Queries definieren mit @NamedQuery

#### Spalten hinzufügen

```java
@Id @GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private Long ID;

@Size(min=2,max=15)
@Column(name="vorName",unique=true)
private String vorName;
```

* @Id Daher weiß der EntityManager, dass es sich um eine ID(Primary Key) handelt
* @GeneratedValue Damit sich der Wert der ID vortlaufend erhöht
* @Column Die Bezeichnung der Spalte in der DB(Wenn man den Namen in der DB ändern möchte ansonsten braucht man die Annotation eigentlich nicht.)
* @Size Richtlinien für den String in dem Code darüber zum Beispiel mindestens 2 Zeichen lang und maximal 15 lang.

Für folgende Klassen die **Entity** Annotationen hinzufügen:

* Benutzer
* Preisstaffelung
* Ticket
* Zug
* Einzelticket
* Zeitkarte
* Strecke
* Sonderangebot

##### XML-Mapping

Wir wollen eine Enity Mappen deshalb verwenden wir **entity-mapping**.
```xml
<entity-mappings xmlns="http://java.sun.com/xml/ns/persistence/orm"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 xsi:schemaLocation="http://java.sun.com/xml/ns/persistence/orm
        http://java.sun.com/xml/ns/persistence/orm_1_0.xsd" version="1.0">
```

Die Queries kommen über den **entity Tag** weil sie wie bei den Annotation außerhalb der Klasse definiert werden.

```xml
 <named-query name="Reservierung.getAll">
        <query>SELECT r FROM Reservierung r</query>
    </named-query>
    <named-query name="Reservierung.getBenutzer">
        <query>from Reservierung r where r.benutzer.eMail= :eMail</query>
    </named-query>
```

Die eigentliche Entity definiert man dann wie folge:

```xml
<entity class="model.Reservierung" name="Reservierung">
        <attributes>
            <id name="ID">
                <generated-value strategy="IDENTITY"/>
            </id>

            <basic name="datum" optional="false">
                <column length="32"/>
            </basic>

            <many-to-one name="zug"/>
        </attributes>
    </entity>
```

* Das class Attribute in entity gibt an für welche Klasse das xml verwendet werden soll.
* **ID** definiert den Primary Key
* **Basic** ist ein normales Attribut und ist vergleichbar mit dem Hinzufügen eines Attributs.
* **Many-to-one** stellt eine Relation zwischen Reservierung und Zug dar.

##### Finde alle Reservierungen für einen bestimmten Benutzer, der durch die eMail-Adresse definiert wird.  

```xml
<named-query name="Reservierung.getBenutzer">
        <query>from Reservierung r where r.benutzer.eMail= :eMail</query>
    </named-query>
```

Hier wird eine select von der Reservierungs Tabelle gemacht und dadurch, dass wir eine Named Query verwenden kann ich hier dann r. benutzer verwenden und eine spalte aus der Benutzer Tabelle abfragen. Im Hintergrund macht Hibernate ein cross join um diese zwei Tabellen mit einander zu verbinden.

##### Liste alle Benutzer auf, die eine Monatskarte besitzen.  

```java
@NamedQuery(name="Benutzer.getMonatskarte",query = "select b from Benutzer b where b.tickets.typ=1")
```

Hier ist es genauso wie bei der 1. Query es wird ein im Hintergrund macht Hibernate ein cross join über Benutzer und Ticket.

##### Liste alle Tickets für eine bestimmte Strecke aus (durch Anfangs- und Endbahnhof definiert), wo keine Reservierungen durchgeführt wurden.  

```java
@NamedQuery(name="Ticket.getReservation",query = "select b from Ticket b left join Reservierung r on r.strecke=b.strecke where b.strecke.start.name=:start and b.strecke.ende.name=:ende")
```

Hier wird ein Left Join über strecke gemacht weil sowohl Reservierung als auch ticket ein Attribut Strecke haben. Dadurch ist es dann möglich das gewünschte Ticket ohne Reservierung abzufragen.

## Quellen
* [XML-Mapping(Query)](https://memorynotfound.com/hibernate-jpa-named-query-xml-annotation-example/)
* [Hibernate](http://hibernate.org/)
* [HQL Hibernate INNER JOIN](https://stackoverflow.com/questions/18379766/hql-hibernate-inner-join)
* [JPA enumerated types mapping. Best approach](https://stackoverflow.com/questions/16140282/jpa-enumerated-types-mapping-best-approach)
* [Joins in Hibernate Query Language](https://lishman.io/joins-in-hibernate-query-language)
* [JPA Query Structure (JPQL / Criteria)](https://www.objectdb.com/java/jpa/query/jpql/structure)

```

```