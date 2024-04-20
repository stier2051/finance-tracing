FROM amazoncorretto:17-alpine as builder

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract
ENV TZ Asia/Almaty

#msttcorefonts
RUN apk add --no-cache msttcorefonts-installer fontconfig
RUN update-ms-fonts

#docker
FROM amazoncorretto:17-alpine
COPY --from=builder dependencies/ ./
RUN true
COPY --from=builder snapshot-dependencies/ ./
RUN true
COPY --from=builder spring-boot-loader/ ./
RUN true
COPY --from=builder application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]