##### Main idea
A secured online shop with basic functionality based on the MySQL. The project represents client - server architecture.
The shop is available only for authenticated users. Authorization and authentication are performed in web filters. 
Default role for registered user is `USER`, admins must be added by system administrator.

_____________

##### Client capabilities:
- registration, logging in and out;
- looking through the store items;
- adding items to the shopping cart;
- deleting items from the cart;
- placing orders.

_____________

##### Admin capabilities:
- logging in and out;
- looking through the users list;
- deleting users;
- looking through the orders list, marking orders complete;
- managing store's assortment;

_____________

##### Technologies Used
- Java 11
- Maven Checkstyle Plugin
- Javax Servlet API 3.1.0
- JSTL 1.2
- JSP
- Apache Tomcat
- Mysql Connector Java 8.0.21
- Bootstrap

____________

##### To run the project, you need:
- clone the project into your local folder and open the project in an IDE;
- configure Tomcat: deployment: `war_exploded`, context address: `"/"`;
- copy the script from `init_db.sql` to the MySQL Workbench;
- insert your MySQL username and login in the `ConnectionUtil` class.

##### For authorization as an ADMIN:
- login: admin
- password: 12345
- visit page `localhost:8080/admin`