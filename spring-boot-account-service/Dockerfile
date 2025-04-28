FROM adoptopenjdk:11-jre-hotspot
MAINTAINER diegoenriquez.com
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-boot-account-service-1.0.0.jar
EXPOSE 8087
ENTRYPOINT ["java","-jar","/spring-boot-account-service-1.0.0.jar"]