FROM arlekinside/java_alpine

RUN mkdir -p -m a+rwx /usr/src/myapp/src/main/resources

RUN mkdir -p -m a+rwx /etc/application-properties

COPY . /usr/src/myapp

WORKDIR /usr/src/myapp

CMD ["/bin/sh", "run.sh"]