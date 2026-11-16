@echo off
set "CATALINA_HOME=C:\apache-tomcat-10.1.44"
set "JAVA_HOME=C:\Program Files\Java\jdk-21"

echo Dang dung Apache Tomcat 10.1...
call "C:\apache-tomcat-10.1.44\bin\catalina.bat" stop
echo Da dung Tomcat thanh cong!
pause
