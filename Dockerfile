FROM openjdk:8-jdk-alpine

COPY target/*.jar /home/storage-service.jar

EXPOSE 7443

CMD java -jar /home/storage-service.jar