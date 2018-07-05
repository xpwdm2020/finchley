FROM registry.cn-hangzhou.aliyuncs.com/tools/jdk:1.8
MAINTAINER qinzhongjian<https://github.com/dqqzj>
VOLUME /tmp
COPY ./target/gateway.jar /gateway.jar
ENTRYPOINT [ "java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/gateway.jar" ]
EXPOSE 9411