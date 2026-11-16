@echo off
echo ========================================================
echo KHOI DONG LAI DICH VU SQL SERVER (MSSQLSERVER)
echo De ap dung:
echo 1. Kich hoat giao thuc TCP/IP tren cong 1433
echo 2. Kich hoat che do xac thuc Mixed Mode (SQL Server and Windows Authentication)
echo ========================================================
net stop MSSQLSERVER
net start MSSQLSERVER
echo ========================================================
echo Da khoi dong lai thanh cong!
echo ========================================================
pause
