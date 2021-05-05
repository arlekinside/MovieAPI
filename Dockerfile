FROM arlekinside/java_alpine

RUN mkdir -p -m 777 /usr/src/myapp/src/main/resources

COPY . /usr/src/myapp

WORKDIR /usr/src/myapp

CMD ["./gradlew", "bootRun"]