FROM adoptopenjdk:11.0.8_10-jre-hotspot-bionic

ENV JAVA_OPTS -Xms128M -Xmx512M -XX:MaxMetaspaceSize=256M -XX:+UseStringDeduplication -XX:+UseG1GC

RUN mkdir /opt/app
COPY target/rekomind-api.jar /opt/app

EXPOSE 8080/tcp

CMD ["sh", "-c", "java -jar /opt/app/rekomind-api.jar ${JAVA_OPTS}"]
