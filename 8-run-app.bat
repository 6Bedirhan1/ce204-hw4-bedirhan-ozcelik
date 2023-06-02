@echo off
@setlocal enableextensions
@cd /d "%~dp0"

echo Running Application
java -jar ce204-hw4/target/ce204-hw4-1.0-SNAPSHOT.jar

echo Operation Completed!
pause