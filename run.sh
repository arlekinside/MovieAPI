#!/bin/sh

#Generating application.properties file out of kubernetes secrets object
SECRETS_PATH="/etc/application-properties"
PORT=$(cat $SECRETS_PATH/port)
DB_MODE=$(cat $SECRETS_PATH/db_mode)
DB_DRIVER=$(cat $SECRETS_PATH/db_driver)
DB_URL=$(cat $SECRETS_PATH/db_url)
DB_USERNAME=$(cat $SECRETS_PATH/db_username)
DB_PASSWORD=$(cat $SECRETS_PATH/db_password)
IMBD_API_KEY=$(cat $SECRETS_PATH/imbd_api_key)
IMBD_API_URL=$(cat $SECRETS_PATH/imbd_api_url)
#SSL_KEY_STORE=$(cat $SECRETS_PATH/ssl_key_store)
#SSL_KEY_STORE_PASSWORD=$(cat $SECRETS_PATH/ssl_key_store_password)
#SSL_KEY_STORE_TYPE=$(cat $SECRETS_PATH/ssl_key_store_type)
#SSL_KEY_ALIAS=$(cat $SECRETS_PATH/ssl_key_alias)

FILE_PATH=/usr/src/myapp/src/main/resources/application.properties

echo "server.port=${PORT}" >>$FILE_PATH
echo "spring.jpa.hibernate.ddl-auto=${DB_MODE}" >>$FILE_PATH
echo "spring.datasource.driver-class-name=${DB_DRIVER}" >>$FILE_PATH
echo "spring.datasource.url=${DB_URL}" >>$FILE_PATH
echo "spring.datasource.username=${DB_USERNAME}" >>$FILE_PATH
echo "spring.datasource.password=${DB_PASSWORD}" >>$FILE_PATH
echo "imbd.api.key=${IMBD_API_KEY}" >>$FILE_PATH
echo "imbd.api.url=${IMBD_API_URL}" >>$FILE_PATH
#echo "server.ssl.key-store=${SSL_KEY_STORE}" >>$FILE_PATH
#echo "server.ssl.key-store-password=${SSL_KEY_STORE_PASSWORD}" >>$FILE_PATH
#echo "server.ssl.keyStoreType=${SSL_KEY_STORE_TYPE}" >>$FILE_PATH
#echo "server.ssl.keyAlias=${SSL_KEY_ALIAS}" >>$FILE_PATH
#Running the application
./gradlew bootRun
