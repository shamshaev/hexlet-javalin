FROM eclipse-temurin:8-jdk-jammy

WORKDIR /app

COPY . /app

RUN gradle installDist

CMD ./build/install/HexletJavalin/bin/HexletJavalin