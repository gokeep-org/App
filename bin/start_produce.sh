#!/usr/bin/env bash
#ID=`ps -ef | grep "$NAME"`
#echo $ID
#echo "---------------"
#for id in $ID
#do
#kill -9 $id
#echo "killed $id"
#done
#echo "---------------"
cd ../
git checkout netty && git fetch && git merge origin/netty && mvn clean && mvn package
cd target
nohup java -jar App-0.0.1-SNAPSHOT.jar --spring.profiles.active=produce &