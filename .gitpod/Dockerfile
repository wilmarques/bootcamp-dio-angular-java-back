FROM gitpod/workspace-base
SHELL ["/bin/bash", "-c"]

# Open JDK
RUN wget https://github.com/adoptium/temurin18-binaries/releases/download/jdk-18.0.1%2B10/OpenJDK18U-jdk_x64_linux_hotspot_18.0.1_10.tar.gz
RUN tar -xvf OpenJDK18U-jdk_x64_linux_hotspot_18.0.1_10.tar.gz
RUN mkdir "$HOME/.java"
RUN mv jdk-18.0.1+10 "$HOME/.java/"
ENV JAVA_HOME="$HOME/.java/jdk-18.0.1+10"
ENV PATH="$PATH:$JAVA_HOME/bin"
RUN rm OpenJDK18U-jdk_x64_linux_hotspot_18.0.1_10.tar.gz

# Maven
RUN wget https://dlcdn.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz
RUN tar -xvf apache-maven-3.8.6-bin.tar.gz
RUN mkdir "$HOME/.maven"
RUN mv apache-maven-3.8.6 "$HOME/.maven/"
ENV M2_HOME="$HOME/.maven/apache-maven-3.8.6"
ENV PATH="$PATH:$M2_HOME/bin"
RUN rm apache-maven-3.8.6-bin.tar.gz
