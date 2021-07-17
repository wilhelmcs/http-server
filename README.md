# http-server #

Build your own http-server on Java

## Compile ##

```bash
./gradlew clean build
```

## Execution ##

```bash
./gradlew run --args='{PORT} {SERVER RESOURCES PATH} {MIME TYPES ALLOWED}'
```

For example

```bash
./gradlew run --args='80 /var/www src/main/resources/MIMETypes.csv'
```

## Tests ##

```bash
./gradlew test
```

## Design ##

Both the [class diagram](https://github.com/Wolam/http-server/blob/master/docs/Dise%C3%B1o%20de%20Software%20Proyecto-UML%20clases.png) and [sequence diagram](https://github.com/Wolam/http-server/blob/master/docs/Dise%C3%B1o%20de%20Software%20Proyecto-UML%20secuencia.png) were made using the SOLID principles




---
## Original authors

- Jeremy Madrigal @tocapb
- Joseph Valenciano @JosephV27
- Randall Zumbado @randox19
- Randy Conejo @RandyCJ
- Wilhelm Carstens @wolam
