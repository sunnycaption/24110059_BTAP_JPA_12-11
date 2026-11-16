@echo off
set "CATALINA_HOME=C:\apache-tomcat-10.1.44"
set "JAVA_HOME=C:\Program Files\Java\jdk-21"

echo ======================================================================
echo    KHOI DONG APACHE TOMCAT 10.1.44 (JAKARTA EE 10 / JDK 21)
echo    Ung dung: Java Web MVC 3 Tang su dung JPA + Hibernate + SQL Server
echo    Duong dan truy cap: http://localhost:8080/UserAuthProject/login
echo ======================================================================
call "C:\apache-tomcat-10.1.44\bin\catalina.bat" run
