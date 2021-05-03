FROM arlekinside/java_alpine

RUN mkdir -p -m 777 /usr/src/myapp

COPY . /usr/src/myapp

WORKDIR /usr/src/myapp

RUN mkdir -p /src/main/resources

EXPOSE 8000

EXPOSE 5432

CMD ["/bin/sh", "run.sh"]