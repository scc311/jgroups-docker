FROM ghcr.io/scc311/jgroups:v3.6.20

WORKDIR /src
COPY . .

RUN javac -Xlint *.java

ENTRYPOINT ["java", "-Djava.net.preferIPv4Stack=true", "Backend"]
