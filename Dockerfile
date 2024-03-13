FROM openjdk:17

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${ADDITIONAL_OPTS}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /apt/spring_boot

COPY /target/spring*.jar spring_mysql.jar

SHELL ["/bin/bash", "-c"]

EXPOSE 5885
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar spring_mysql.jar --spring.profiles.active=${PROFILE}
