# norwegian-vessels
Application testing kotlin and ktor-mustache

## Technologies used
* Kotlin
* Gradle
* Docker
* Ktor
* Handlebars

#### Requirements

* JDK 14
* Docker

### Building the application
#### Compile and package application
To build locally and run the integration tests you can simply run `./gradlew shadowJar` or on windows 
`gradlew.bat shadowJar`

#### Creating a docker image
Creating a docker image should be as simple as `docker build -t norwegian-vessels .`

#### Running a docker image
`docker run --rm -it -p 8080:8080 norwegian-vessels`

### Web site with vessel info is avaliabele at
http://localhost:8080/vesselData

## Contact
### Code/project related questions can be sent to
* Joakim Taule Kartveit, `joakimkartveit@gmail.com`

