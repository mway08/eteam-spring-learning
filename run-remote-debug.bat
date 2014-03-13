@echo off
@REM set MAVEN_OPTS=-noverify -javaagent:"%JREBEL_HOME%\jrebel.jar" -Drebel.stats=true -Drebel.log=true -Drebel.log.file=C:/TEMP/beyo-erp/jrebel.log
set MAVEN_OPTS=-noverify -javaagent:"jrebel\jrebel.jar" -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000 -Xms256m -Xmx512m -XX:MaxPermSize=512m -Dfile.encoding=UTF-8
call mvn tomcat:run -Pdev