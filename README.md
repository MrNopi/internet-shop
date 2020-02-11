# _Project purpose_

This is a template for creating an e-store.

It has login and registration forms as well as main page of the store.

There are controllers for working with items, users, orders and buckets:

1. _Inject - for injection mock data,_

2. _Registration - for registering new users,_

3. _Login - for user authentication and authorization,_

4. _Users - for displaying and manipulating users, which was registered(such as deleting). Available for an ADMIN only,_

5. _Items - for manipulating items in stock(such as creating or deleting specify item),_

6. _Bucket - for displaying and manipulating items in user’s bucket. Available for common USER only,_

7. _Orders - for displaying user’s order history. Available for a common USER only,_

8. _Logout - for logging out._

# _Project Structure_
- Java 11
- Maven 4.0.0
- javax.servlet 3.1.0
- jstl 1.2
- log4j 1.2.17
- maven-checkstyle-plugin
- JDBC

### For developer

Open the project in your IDE.

Add it as maven project.

#### Configure Tomcat:
- add artifact;
- add sdk 11.0.3 or later

#### Configure the project
1. _Add sdk 11.0.3 in project struсture._

2. _Initialize data base copying code from init_db.sql, which located in project_name/src/main/resources/init_db.sql_

3. _At internet-shop.src.main.java.Factory class use username and password for your DB to create a Connection._

4. _Change a path in internet-shop.src.main.java.resources.log4j.properties. It has to reach your logFile._

5. _fill up database with items, users and everything you need...._

6. _Run the project._
