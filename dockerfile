FROM ubuntu:24.04

ENV DEBIAN_FRONTEND=noninteractive

#python 3.10 and Java version 8

RUN apt-get update && \
    apt-get install -y \
    wget \
    curl \
    unzip \
    zip \
    python3.10 \
    python3-pip \
    openjdk-8-jdk


# Installing gradle
RUN wget https://services.gradle.org/distributions/gradle-8.6-bin.zip -O /tmp/gradle.zip && \
    unzip /tmp/gradle.zip -d /opt && \
    rm /tmp/gradle.zip && \
    mv /opt/gradle-* /opt/gradle
    
ENV GRADLE_HOME=/opt/gradle
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$GRADLE_HOME/bin:$PATH"

# Installing Kotlin
RUN curl -s https://get.sdkman.io | bash && \
    /bin/bash -c "source /root/.sdkman/bin/sdkman-init.sh && sdk install kotlin"

WORKDIR /app

COPY . /app

RUN gradle build

CMD ["gradle", "run"]