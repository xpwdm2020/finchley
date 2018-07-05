FROM registry.cn-hangzhou.aliyuncs.com/tools/jdk:1.8
MAINTAINER qinzhongjian<https://github.com/dqqzj>
VOLUME /tmp
COPY ./target/zipkin.jar /zipkin.jar
ENTRYPOINT [ "java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/zipkin.jar" ]
EXPOSE 9411