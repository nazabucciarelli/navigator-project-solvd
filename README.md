# Navigator App

This application allows users to determine the shortest and second shortest routes from one station to another when navigating by car using the Floyd-Warshall Algorithm. Additionally, it is possible to select the bus as the means of transport, find out which bus the user has to board, and identify where to change buses.
The map of stations is as follows:
![image](https://github.com/nazabucciarelli/navigator-project-solvd/assets/84046180/24bf8946-a254-47e3-8bc5-57b4151b3709)

The routes the buses do are as follows:
![image](https://github.com/nazabucciarelli/navigator-project-solvd/assets/84046180/b4dfa6ad-04f2-47c7-af81-51e91b32cd8d)


## Explanation

In order to start working on this project, we first created a design plan that represents all the entities interacting within the application, along with the responsibilities assigned to each developer. This ensures that all team members have a clear understanding of our tasks. Subsequently, we initiated the development of the project itself. We applied the MVC pattern and adhered to good programming practices. The entity-relationship diagram we modeled is as follows:
![image](https://github.com/nazabucciarelli/navigator-project-solvd/assets/84046180/e8cd953e-2c2d-4eb8-8ee9-28e36064604f)


## Technologies

- Java
- MySQL
- Maven
- Git

## Set-Up

To run this project, you will need Java installed on your machine, specifically version 21. First, clone this repository into a folder on your PC. You need to enter the following command in a terminal:

```bash
  git clone https://github.com/nazabucciarelli/navigator-project-solvd.git
```

Afterward, open the SQL file stored in src/main/resources with MySQL Workbench or any other database tool and run the entire script to set up the project's database. Keep in mind that you will need the database to be running on your local machine. You can use XAMPP if you prefer.

Then, make sure to create a db.properties file in the folder src/main/resources and specify the following values:

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

