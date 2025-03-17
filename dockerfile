# Use the official Ubuntu 24.04 as the base image
FROM ubuntu:24.04

# Set environment variables to avoid interactive prompts during package installation
ENV DEBIAN_FRONTEND=noninteractive

# Update the package list and install Python 3.10
RUN apt-get update && \
    apt-get install -y python3.10 python3-pip

# Install Java 8
RUN apt-get install -y openjdk-8-jdk

# Install Kotlin using SDKMAN!
# Install required dependencies (curl, unzip, zip)
RUN apt-get install -y curl unzip zip && \
    curl -s https://get.sdkman.io | bash && \
    /bin/bash -c "source /root/.sdkman/bin/sdkman-init.sh && sdk install kotlin"

# Set environment variables for Java and Kotlin
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:/root/.sdkman/bin:$PATH"

# Verify installations in a single RUN command to ensure environment variables are applied
RUN python3 --version && \
    java -version && \
    /bin/bash -c "source /root/.sdkman/bin/sdkman-init.sh && kotlin -version"

# Default command
CMD ["bash"]