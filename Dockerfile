FROM maven:3.9.6-eclipse-temurin-22-alpine
WORKDIR /app
COPY . /app
RUN apk upgrade && apk add inotify-tools
CMD [ "sh", "-c","\
  source ./.inotify/file-event.sh &\
  mvn spring-boot:run &&\
  sh\
"]