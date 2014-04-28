echo off
if [%1]==[] goto usage
echo Using profile '%1'
java -Dspring.profiles.active=%1 -jar target/buildstatus-1.0-SNAPSHOT.war
goto exit

:usage
echo Usage: buildrun TEAMNAME
echo (There must exist a application-TEAMNAME.yml configuration file for your team on the Java classpath)

:exit
