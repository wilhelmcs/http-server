#http-server

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

### Class diagram ###

![class](https://github.com/IC-6821/ic-6821-2021i-p2-uncle-munguia-software-design/blob/master/diagrams/Dise%C3%B1o%20de%20Software%20Proyecto-UML%20clases.png)
### Sequence diagram ###

![sequence](https://github.com/IC-6821/ic-6821-2021i-p2-uncle-munguia-software-design/blob/master/diagrams/Dise%C3%B1o%20de%20Software%20Proyecto-UML%20secuencia.png)


---
## Original authors

- Jeremy Madrigal @tocapb
- Joseph Valenciano @JosephV27
- Randall Zumbado @randox19
- Randy Conejo @RandyCJ
- Wilhelm Carstens @wolam