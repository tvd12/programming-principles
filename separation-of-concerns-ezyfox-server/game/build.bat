:: set EZYFOX_SERVER_HOME=
mvn -pl . clean install & ^
mvn -pl game-common -Pexport clean install & ^
mvn -pl game-app-api -Pexport clean install & ^
mvn -pl game-app-entry -Pexport clean install & ^
mvn -pl game-plugin -Pexport clean install & ^
copy game-zone-settings.xml %EZYFOX_SERVER_HOME%/settings/zones/
