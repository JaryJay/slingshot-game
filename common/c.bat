call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse
cd ..\client
call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse
cd ..\server
call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse
cd ..\observer
call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse