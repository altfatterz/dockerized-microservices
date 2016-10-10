#!/bin/bash

# wait for 15 seconds until mysql is up
./wait-for-it.sh -t 15 mongodb:27017

if [ $? -eq 0 ]
then
  # To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
  java -Djava.security.egd=file:/dev/./urandom -jar app.jar
fi
