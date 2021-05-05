#!/bin/sh

#Generating application.properties file out of kubernetes secrets object
SECRETS_PATH="/etc/application-properties"
PORT=$(cat $SECRETS_PATH/port)
DB_MODE=$(cat $SECRETS_PATH/db_mode)
DB_DRIVER=$(cat $SECRETS_PATH/db_driver)
DB_URL=$(cat $SECRETS_PATH/db_url)
DB_USERNAME=$(cat $SECRETS_PATH/db_username)
DB_PASSWORD=$(cat $SECRETS_PATH/db_password)
FILE_PATH=/usr/src/myapp/src/main/resources/application.properties

echo "server.port=${PORT}" >> $FILE_PATH
echo "spring.jpa.hibernate.ddl-auto=${DB_MODE}" >> $FILE_PATH
echo "spring.datasource.driver-class-name=${DB_DRIVER}" >> $FILE_PATH
echo "spring.datasource.url=${DB_URL}" >> $FILE_PATH
echo "spring.datasource.username=${DB_USERNAME}" >> $FILE_PATH
echo "spring.datasource.password=${DB_PASSWORD}" >> $FILE_PATH

#Running the application

./gradlew bootRun