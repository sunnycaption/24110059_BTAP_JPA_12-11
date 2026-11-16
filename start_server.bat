@echo off
title Khoi chay Server Embedded Tomcat & Tu dong mo trinh duyet
echo ==========================================================
echo    KHOI CHAY SERVER EMBEDDED TOMCAT VA MO TRINH DUYET
echo ==========================================================
echo.

:: 1. Mo trinh duyet sau 8 giay (chay ngam song song)
start /B powershell -Command "Start-Sleep -Seconds 8; Start-Process 'http://localhost:8080/login'"

:: 2. Build va khoi chay Tomcat nhung (khong dung clean de tranh loi khoa file access_log tren Windows)
echo Dang khoi chay Tomcat tai http://localhost:8080/ ...
mvn compile tomcat7:run

pause
