Write-Host "==========================================================" -ForegroundColor Cyan
Write-Host "   KHOI CHAY SERVER EMBEDDED TOMCAT & MO TRINH DUYET" -ForegroundColor Cyan
Write-Host "==========================================================" -ForegroundColor Cyan

# 1. Tac vu chay song song mo trinh duyet sau 8 giay
Start-Job -ScriptBlock {
    Start-Sleep -Seconds 8
    Start-Process "http://localhost:8080/login"
} | Out-Null

# 2. Build va khoi chay Tomcat nhung
mvn clean package tomcat7:run

