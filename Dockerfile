FROM adoptopenjdk:11.0.8_10-jre-hotspot-bionic

ENV JAVA_OPTS -Xms64M -Xmx512M -XX:MaxMetaspaceSize=128M -XX:+UseStringDeduplication -XX:+UseG1GC

RUN mkdir /opt/app
COPY src/target/rekomind-resource-ws.jar /opt/app

RUN mkdir -p /etc/rekomind/rekomind-resource-ws/
COPY config/docker/log4j2.xml /etc/rekomind/rekomind-resource-ws/log4j2.xml

EXPOSE 8082/tcp

CMD ["sh", "-c", "java $JAVA_OPTS -jar /opt/app/rekomind-resource-ws.jar"]
