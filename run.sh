echo "---Creating secrets file---"
echo -e $SECRETS > $PWD/src/main/resources/application.properties
echo "---Starting the application---"
./gradlew bootRun