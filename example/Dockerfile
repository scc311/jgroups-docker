FROM ghcr.io/scc311/jgroups:v3.6.20

WORKDIR /src
COPY . .

RUN javac *.java

ENTRYPOINT ["java", "Server"]
