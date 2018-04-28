call Configuration.cmd

pushd "..\client\portal-app"

ng serve --host %CLIENT_HOST_IP%

popd