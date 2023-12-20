FROM amazoncorretto:8u372

COPY /target/ /target/

EXPOSE 8080

ENTRYPOINT [ "java","-jar", "/target/registration-system-v1.jar" ]
