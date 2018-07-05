FROM registry.cn-hangzhou.aliyuncs.com/tools/jdk:1.8
MAINTAINER qinzhongjian<https://github.com/dqqzj>
VOLUME /tmp
COPY ./target/config-0.0.1-SNAPSHOT.jar /config.jar
ENTRYPOINT [ "java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/config.jar" ]
EXPOSE 8766

