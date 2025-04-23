#!/bin/bash
mvn package -Dmaven.test.skip=true

make_image() {
  set -x
  JAR=$1
  NAME=$2
  cp $JAR app.jar
  docker build -t $NAME .
  set +x
}

make_image user/target/user-0.9.0.jar           app-user
make_image order/target/order-0.9.0.jar         app-order
make_image discovery/target/discovery-0.9.0.jar app-discovery
