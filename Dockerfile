FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY src/ ./src/

RUN find src -name "*.java" -print0 | xargs -0 javac -encoding UTF-8 -d out

CMD ["java", "-cp", "out", "org.example.Aplicacao"]
