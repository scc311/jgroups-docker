# using a minimal base image
FROM alpine:latest

LABEL maintainer="Will Fantom <w.fantom@lancs.ac.uk>"

# install dependencies 
RUN apk add --no-cache \
    openjdk8 \
    wget
ENV JAVA_HOME=/usr/lib/jvm/java-1.8-openjdk
ENV PATH="$JAVA_HOME/bin:${PATH}"

# download jgroups
ARG JGROUPS_VERSION=3.6.20
WORKDIR /javalibs
RUN echo "This might take a while to get a response! Retry if needed!" \
 && wget "https://sourceforge.net/projects/javagroups/files/JGroups/${JGROUPS_VERSION}.Final/jgroups-${JGROUPS_VERSION}.Final.jar/download" -O jgroups-${JGROUPS_VERSION}.jar
ENV CLASSPATH=$CLASSPATH:/javalibs/jgroups-${JGROUPS_VERSION}.jar:/usr/lib/jvm/java-1.8-openjdk/lib:.
