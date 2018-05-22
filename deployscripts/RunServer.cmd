call Configuration.cmd

pushd "..\server\target"

java -jar -Dspring.profiles.active=development %VATTENHUB_JAR%

popd