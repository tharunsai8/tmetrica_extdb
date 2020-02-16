## Tmetrica
5. Система Time-Tracking. Администратор закрепляет за пользователем
   Активность. У пользователя может быть одна или несколько Активностей.
   Пользователь отмечает кол-во затраченного времени на каждую активность.
   Пользователь может отправить запрос на добавление/удаление Активности.

### Setup 
* JDK 1.8 or higher
* Apache Maven 3.6.1 or higher
* PostgreSQL 11 or higher
* Apache Tomcat 8.0 or higher

### Installation
* Clone project from GitHub (_git clone https://github.com/solosuic1de/servletTmetrica_)
* Specify connection keys of in _../src/main/resources/database/db.properties_
* Execute all scripts in _../src/main/resources/databaseScripts/_ to create and fill database
* cd to root project folder and execute command _mvn clean install_
* copy artifact ROOT.war from _target_ folder to _%TOMCAT%\webapps_

### Running
* Start Tomcat server by running the script _%TOMCAT%\bin\startup_ .bat for Windows or .sh for Unix based OS.
* After server start, application will be available by URL _http://localhost:8080/tmetrica_war/_
* Use email _**"admin@test.com"**_ and password _**"password"**_ to log in with administrator rights.
* Use login _**"user@test.com"**_ and password _**"password"**_ to log in with user rights.
* To stop server run script _%TOMCAT%\bin\shutdown_ .bat for Windows or .sh for Unix based OS.