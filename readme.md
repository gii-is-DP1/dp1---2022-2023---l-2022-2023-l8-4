# Enlace video

[Gameplay dobble](https://youtu.be/KzSEJYlkOZE)

## Running petclinic locally
Petclinic is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:


```
git clone https://github.com/gii-is-DP1/spring-petclinic.git
cd spring-petclinic
./mvnw package
java -jar target/*.jar
```

You can then access petclinic here: http://localhost:8080/

<img width="1042" alt="petclinic-screenshot" src="https://cloud.githubusercontent.com/assets/838318/19727082/2aee6d6c-9b8e-11e6-81fe-e889a5ddfded.png">

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

### Steps:

1) On the command line
```
git clone https://github.com/gii-is-DP1/dp1-2022-2023-l8-4.git
```
2) Inside Eclipse or STS
```
File -> Import -> Maven -> Existing Maven project
```

Then either build on the command line `./mvnw generate-resources` or using the Eclipse launcher (right click on project and `Run As -> Maven install`) to generate the css. Run the application main method by right clicking on it and choosing `Run As -> Java Application`.

3) Inside IntelliJ IDEA

In the main menu, choose `File -> Open` and select the Petclinic [pom.xml](pom.xml). Click on the `Open` button.

CSS files are generated from the Maven build. You can either build them on the command line `./mvnw generate-resources`
or right click on the `spring-petclinic` project then `Maven -> Generates sources and Update Folders`.

A run configuration named `PetClinicApplication` should have been created for you if you're using a recent Ultimate
version. Otherwise, run the application by right clicking on the `PetClinicApplication` main class and choosing
`Run 'PetClinicApplication'`.

4) Navigate to Petclinic

Visit [http://localhost:8080](http://localhost:8080) in your browser.


## Looking for something in particular?

|Spring Boot Configuration | Class or Java property files  |
|--------------------------|---|
|The Main Class | [PetClinicApplication](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java) |
|Properties Files | [application.properties](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/resources) |
|Caching | [CacheConfiguration](https://github.com/gii-is-DP1/spring-petclinic/blob/master/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java) |

# License

The Spring PetClinic sample application is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).

[spring-petclinic]: https://github.com/spring-projects/spring-petclinic
[spring-framework-petclinic]: https://github.com/spring-petclinic/spring-framework-petclinic
[spring-petclinic-angularjs]: https://github.com/spring-petclinic/spring-petclinic-angularjs 
[javaconfig branch]: https://github.com/spring-petclinic/spring-framework-petclinic/tree/javaconfig
[spring-petclinic-angular]: https://github.com/spring-petclinic/spring-petclinic-angular
[spring-petclinic-microservices]: https://github.com/spring-petclinic/spring-petclinic-microservices
[spring-petclinic-reactjs]: https://github.com/spring-petclinic/spring-petclinic-reactjs
[spring-petclinic-graphql]: https://github.com/spring-petclinic/spring-petclinic-graphql
[spring-petclinic-kotlin]: https://github.com/spring-petclinic/spring-petclinic-kotlin
[spring-petclinic-rest]: https://github.com/spring-petclinic/spring-petclinic-rest

# Historias de usuario

## H1 - Crear partida
Como jugador quiero poder crear una partida y elegir un modo de juego (el foso, patata caliente, la torre infernal), mostrando un código de juego que otros jugadores podrán usar para acceder a la partida, para poder jugar en una partida propia.

### Escenarios Positivos:
* H1+E1- Creación exitosa

Dado que estamos logueados en el sistema como jugador, cuando elijamos un modo de juego de los 3 posibles, entonces seremos enviados a una página con el código de la partida, el modo de juego y una sala donde aparecerán el resto de jugadores.

### Escenarios Negativos:
* H1-E1- Número de jugadores inválido

Dado que estamos logueados en el sistema como jugador, cuando marquemos un número de jugadores que no esté entre 2 y 4, entonces se lanzará un mensaje indicando que no se puede hacer una partida con con más de 4 o menos de 2 personas.

# H2- Juego
Como jugador quiero poder acceder a una sala para poder jugar en base a las reglas del modo de juego.

## Escenarios Positivos:
* H2+E1- Acceso a la partida

Dado que estamos logueados en el sistema como jugador y ha introducido un código de partida correcto, cuando pulsamos el botón de unirse, entonces somos redirigidos a la sala deseada.

## Escenarios Negativos:
* H2-E2 - Partida no encontrada

Dado que estamos logueados en el sistema como jugador y ha introducido un código de partida incorrecto o inexistente, cuando pulsamos el botón de unirse, entonces somos notificados de que la sala que buscamos no existe.

* H2-E3- Partida comenzada

Dado que estamos logueados en el sistema como jugador y hemos introducido un código de partida ya empezada, cuando pulsamos el botón de unirse, entonces somos notificados de que la partida ya ha comenzado y no podremos jugar.