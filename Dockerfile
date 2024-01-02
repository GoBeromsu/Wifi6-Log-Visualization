FROM openjdk:17

# 필요한 패키지 설치
RUN microdnf install findutils

WORKDIR /app

# 프로젝트 파일 복사
COPY . /app

# Gradle을 사용하여 빌드
RUN ./gradlew clean build -x test

ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "build/libs/wifi6-0.0.1-SNAPSHOT.jar"]
