FROM adoptopenjdk:11.0.8_10-jre-hotspot-bionic

ENV JAVA_OPTS -Xms64M -Xmx512M -XX:MaxMetaspaceSize=128M -XX:+UseStringDeduplication -XX:+UseG1GC

RUN mkdir /opt/app
COPY src/target/rekomind-api.jar /opt/app

RUN mkdir -p /etc/rekomind/rekomind-api/
COPY config/docker/log4j2.xml /etc/rekomind/rekomind-api/log4j2.xml

EXPOSE 8080/tcp

CMD ["sh", "-c", "java $JAVA_OPTS -jar /opt/app/rekomind-api.jar"]
