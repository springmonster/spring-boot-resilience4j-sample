FROM gradle:7.4-jdk17-jammy
ARG GIT_COMMIT=unspecified
LABEL git_commit=$GIT_COMMIT
ADD --chown=gradle . /code
WORKDIR /code
COPY build/libs/spring-boot-resilience4j-sample-0.0.1-SNAPSHOT.jar /code
CMD java -jar spring-boot-resilience4j-sample-0.0.1-SNAPSHOT.jar
