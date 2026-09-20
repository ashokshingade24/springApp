FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/order-service-1.0.0.jar app.jar
RUN useradd --system --uid 10001 appuser
USER 10001
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]
