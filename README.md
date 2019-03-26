# "Komponentenbasierte Programmierung"

## Aufgabenstellung
Die detaillierte [Aufgabenstellung](TASK.md) beschreibt die notwendigen Schritte zur Realisierung.



Bean Pojo + @



Um das Projekt als gradle Projekt zu initialisieren führen wir folgendes aus:

```bash
gradle idea
```

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

* @Id Daher weiß der EntityManager, dass es sich um eine ID handelt
* @GeneratedValue Damit sich der Wert der ID vortlaufend erhöht
* @Column Die Bezeichnung der Spalte in der DB
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



## Quellen
