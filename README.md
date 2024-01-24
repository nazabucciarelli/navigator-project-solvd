# Navigator App

This application allows users to know what is the shortest and the second shortest ways from a station to another navigating by car using the Floyd-Marshall Algorithm. Also it is possible to select bus as mean of transport, know what bus the user has to get on and where to change of bus.
The map of stations is the following:
![image](https://github.com/nazabucciarelli/navigator-project-solvd/assets/84046180/8b34b14c-20c3-41fe-9e56-6eb1f24f3fb8)


## Explanation

In order to start doing this project, we firstly did a design plan representing all the entities that will interact within the application
and also the responsibilities of each developer, so all the team members can understand what we are doing. Then, we started with the development of the project itself. We applied the MVC pattern and used good programming practices. The entity-relation diagram we model is the following:
![image](https://github.com/nazabucciarelli/navigator-project-solvd/assets/84046180/e8cd953e-2c2d-4eb8-8ee9-28e36064604f)


## Technologies

- Java
- MySQL
- Maven
- Git

## Set-Up

To run this project you will need Java installed in your machine, precisely the version 21.
First, clone this repository in a folder of your PC.
You have to put the following command in a terminal:

```bash
  git clone this-repo-url
```
After, open the SQL file stored in src/main/resources with MySQL Workbench or any other database tool and run the whole statement to setup the project's database.
Keep in mind that you will need the database to be running in you local machine. You can use XAMPP if you want it.

Then, make sure of creating a db.properties file in the folder src/main/resources and specify the following values:

```
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/navigator
db.username=<your_username>
db.password=<your_password>
```

Finally, you will need an IDE to open the project folder and run the Main.java
file to see the program's terminal interface.

## Authors

- [@Nazareno Bucciarelli](https://github.com/nazabucciarelli)
- [@Atilio Boher](https://github.com/AtilioBoher)
- [@Mateo Chutt](https://github.com/chuttmateo)
- [@Lucas Gragera](https://github.com/lucasgragera)
- [@Julian Riedinger](https://github.com/JulianRiedinger7)

