# Gestionnaire de tâches

Petite application console en Java pour gérer une liste de tâches.

## Structure

```
pom.xml                         configuration Maven
mvnw, mvnw.cmd, .mvn/           Maven Wrapper (télécharge Maven automatiquement)
src/main/java/com/kabo/         code de l'application
src/test/java/com/kabo/         tests JUnit
target/                         résultat de la compilation (généré)
```

## Commandes

```bash
./mvnw test                     # lance les tests
./mvnw package                  # compile, teste et crée target/taches-1.0.jar
java -jar target/taches-1.0.jar # lance l'application
./mvnw clean                    # supprime target/
```
