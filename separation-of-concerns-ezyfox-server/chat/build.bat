:: set EZYFOX_SERVER_HOME=
mvn -pl . clean install & ^
mvn -pl chat-common -Pexport clean install & ^
mvn -pl chat-app-api -Pexport clean install & ^
mvn -pl chat-app-entry -Pexport clean install & ^
mvn -pl chat-plugin -Pexport clean install & ^
copy chat-zone-settings.xml %EZYFOX_SERVER_HOME%/settings/zones/
