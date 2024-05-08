FROM maven:3.9.6-eclipse-temurin-22-alpine
WORKDIR /app
COPY . /app
RUN \
    apk upgrade &&\
    apk add inotify-tools &&\
    apk add npm &&\
    apk add git
CMD [ "sh", "-c","\
    git config --global user.name docker &&\
    git config --global user.email docker@mail.com &&\
    npm ci &&\
    source ./.inotify/file-event.sh &\
    mvn clean install &&\
    sh\
"]