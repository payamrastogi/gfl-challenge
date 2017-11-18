#!/bin/bash
SNAPSHOT=0.0.1-SNAPSHOT-jar-with-dependencies.jar

#mvn clean install

nohup java -cp ./gfl-elastic/gfl-elastic-${SNAPSHOT} com.gfl.elastic.resource.GflElasticResource > elastic.log &
nohup java -cp ./gfl-sfbay/gfl-sfbay-${SNAPSHOT}  com.gfl.sfbay.resource.GflSfBayResource > sfbay.log &
nohup java -cp ./gfl-service/gfl-service-${SNAPSHOT}  com.gfl.service.resource.GflServiceResource > service.log &
nohup java -cp ./gfl-client/gfl-client-${SNAPSHOT}  com.gfl.client.resource.GflClientResource > client.log &
