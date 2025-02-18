FROM openjdk:22-jdk
ADD target/ecommerce.jar ecommerce.jar
ENTRYPOINT ["java","-jar","ecommerce.jar"]