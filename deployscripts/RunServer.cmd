call Configuration.cmd

pushd "..\server\target"

java -jar %VATTENHUB_JAR%

popd