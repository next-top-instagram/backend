FROM openjdk:17.0.2-jdk


EXPOSE 80 443
# RUN rm /bin/sh && ln -s /bin/bash /bin/sh
FROM maven:3.8.6

RUN mkdir /app
WORKDIR /app
EXPOSE 8080
RUN keytool -genkeypair -alias baeldung -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore baeldung.p12 -validity 3650 -storepass pass123 -keypass pass123 -noprompt -dname "cn=Unknown, ou=Unknown, o=Unknown, c=Unknown"

COPY ./mvnw ./mvnw.cmd ./pom.xml /app/
# RUN mvn package
RUN mvn dependency:resolve
# CMD ["tail", "-f", "/dev/null"]
COPY ./ /app/
RUN mkdir -p /app/src/main/resources/keystore
RUN mv baeldung.p12 /app/src/main/resources/keystore/
# RUN mvn clean package
# RUN ./mvnw package && java -jar ./app.jar

CMD ["mvn", "spring-boot:run"]