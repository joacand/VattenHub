call Configuration.cmd

@start RunMongo.cmd

ping 127.0.0.1 -n %SLEEP_BETWEEN_MONGO_AND_SERVER% > nul

@start RunServer.cmd
