# norwegian-vessels 🚢
Application testing kotlin and ktor-mustache

## Technologies used
* Kotlin
* Gradle
* Docker
* Ktor
* Handlebars

#### Requirements

* JDK 17
* Docker

### Building the application
#### Compile and package application
To build locally and run the integration tests you can simply run `./gradlew shadowJar` or on windows 
`gradlew.bat shadowJar`

### Upgrading the gradle wrapper
Find the newest version of gradle here: https://gradle.org/releases/ Then run this command:

```./gradlew wrapper --gradle-version $gradleVersjon```

Remeber to also update the gradle version in the build.gradle.kts file
```gradleVersion = "$gradleVersjon"```

#### Creating a docker image
Creating a docker image should be as simple as `docker build -t norwegian-vessels .`

#### Running a docker image
`docker run --rm -it -p 8080:8080 norwegian-vessels`

### Web site with vessel info is avaliabele at
#### All vessels
http://localhost:8080/api/allVessels
#### Vessels by municipality
http://localhost:8080/api?municipality=austevoll

## A snapshot of the Web site with vessel info, can be seen here:
https://mikaojk.github.io/norwegian-vessels/

## Inquiries
Questions related to the code or project can be asked as issues here on GitHub.
