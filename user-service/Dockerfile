FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/user-service-1.0.0.jar app.jar
RUN useradd --system --uid 10001 appuser
USER 10001
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
