#docker build -t [microservice-name]-[version] -f Dockerfile .
FROM openjdk:8-jre
# Microservicio port
EXPOSE 8080

ADD build/libs/ms-movimientoscuenta-personasmobile-exp.jar /app/app.jar

# Fix zona horaria de Chile
RUN apt-get update && apt-get install -y tzdata

WORKDIR /app
CMD java -Djava.security.egd=file:/dev/./urandom ${JAVA_OPTS} -jar app.jar
