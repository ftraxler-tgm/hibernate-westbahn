# "Komponentenbasierte Programmierung"

## Aufgabenstellung
Die detaillierte [Aufgabenstellung](TASK.md) beschreibt die notwendigen Schritte zur Realisierung.



Bean Pojo + @



Um das Projekt als gradle Projekt zu initialisieren führen wir folgendes aus:

```bash
gradle idea
```

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



## Implementierung

## Quellen
