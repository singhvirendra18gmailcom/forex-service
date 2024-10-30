FROM maven:3.9.9-amazoncorretto-17-alpine AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17.0-jdk
VOLUME /tmp
EXPOSE 8765
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]