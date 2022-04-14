FROM java:8-jdk
WORKDIR /usr/local/app
ADD /target/mira-user-client-1.0-SNAPSHOT.jar app.jar
EXPOSE 8081
CMD java -jar ./app.jar --spring.profiles.active=dev