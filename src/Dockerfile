FROM adoptopenjdk:11.0.8_10-jre-hotspot-bionic

ENV JAVA_OPTS -Xms128M -Xmx512M -XX:MaxMetaspaceSize=256M -XX:+UseStringDeduplication -XX:+UseG1GC

RUN mkdir /opt/app
COPY target/resource-ws.jar /opt/app

EXPOSE 8082/tcp

CMD ["sh", "-c", "java -jar /opt/app/resource-ws.jar ${JAVA_OPTS}"]
