FROM openjdk:17.0.2-jdk


EXPOSE 80 443
# RUN rm /bin/sh && ln -s /bin/bash /bin/sh
FROM maven:3.8.6

RUN mkdir /app
WORKDIR /app
EXPOSE 8080
COPY ./mvnw ./mvnw.cmd ./pom.xml /app/
# RUN mvn package
RUN mvn dependency:resolve
# CMD ["tail", "-f", "/dev/null"]
COPY ./ /app/

# RUN mvn clean package
# RUN ./mvnw package && java -jar ./app.jar

CMD ["mvn", "spring-boot:run"]