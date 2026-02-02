FROM gradle:8-jdk21 AS build
WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src ./src

RUN gradle bootJar --no-daemon

FROM amazoncorretto:17.0.17-alpine3.22
WORKDIR /app

ENV TZ=Asia/Seoul
RUN apt-get update \
    && apt-get install -y tzdata \
    && ln -sf /usr/share/zoneinfo/$TZ /etc/localtime \
    && echo $TZ > /etc/timezone \
    && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/build/libs/image-0.0.1-SNAPSHOT.jar .

EXPOSE 8082 9090

ENTRYPOINT ["java", "-jar", "user-0.0.1-SNAPSHOT.jar"]
