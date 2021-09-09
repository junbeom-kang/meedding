FROM openjdk:11-jdk

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} Meedding.jar

ENTRYPOINT ["java", "-jar", "/Meedding.jar"]