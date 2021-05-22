call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse
cd ..\jamhacks-v-client
call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse
cd ..\jamhacks-v-server
call mvn clean install
call mvn eclipse:clean
call mvn eclipse:eclipse