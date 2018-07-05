FROM registry.cn-hangzhou.aliyuncs.com/tools/jdk:1.8
MAINTAINER qinzhongjian<https://github.com/dqqzj>
VOLUME /tmp
COPY ./target/registry-0.0.1-SNAPSHOT.jar /registry.jar
ENTRYPOINT [ "java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/registry.jar" ]
EXPOSE 8763 9274

