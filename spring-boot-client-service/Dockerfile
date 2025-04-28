FROM adoptopenjdk:11-jre-hotspot
MAINTAINER diegoenriquez.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-boot-client-service-1.0.0.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/spring-boot-client-service-1.0.0.jar"]