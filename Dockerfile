FROM maven:3.6-openjdk-11 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java:11
COPY --from=debian:9.11 /usr/lib/locale /usr/lib/locale
ENV LANG=C.UTF-8
COPY --from=build /usr/src/app/target/frota-0.0.1-SNAPSHOT.jar /usr/app/app.jar
ENTRYPOINT ["java", "-jar", "/usr/app/app.jar"]