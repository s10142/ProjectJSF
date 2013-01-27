#!/bin/sh

echo "************ UNDEPLOYING *******************"
asadmin undeploy jsf
echo "************ BUILDING **********************"
mvn package
echo "************ DEPLOYING *********************"
asadmin deploy target/jsf.war
