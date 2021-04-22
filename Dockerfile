FROM arlekinside/java_alpine

RUN mkdir -p /usr/src/myapp
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

CMD ["sh"]