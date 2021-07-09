FROM java:8
WORKDIR /
ADD target/book-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/book-0.0.1-SNAPSHOT.jar"]