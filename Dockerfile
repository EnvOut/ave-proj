FROM openjdk:11.0.6-jre
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=compose", "/usr/share/app/lib/demo-0.0.1-SNAPSHOT.jar"]
ADD build/libs /usr/share/app/lib
