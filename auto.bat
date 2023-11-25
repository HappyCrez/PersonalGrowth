@echo off
if %1==start (
    call mvn clean
    call mvn package
)
call mvn exec:java "-Dexec.mainClass=com.Launcher"