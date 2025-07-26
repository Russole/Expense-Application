# 使用你需要的 JDK（這裡是 Oracle JDK 24）
FROM container-registry.oracle.com/java/openjdk:24

# 建立資料夾
WORKDIR /app

# 複製整個 jars 資料夾到容器中
COPY restapi-0.0.1-SNAPSHOT.jar /app/restapi-0.0.1-SNAPSHOT.jar

# 設定容器啟動時執行的 JAR
ENTRYPOINT ["java", "-jar", "/app/restapi-0.0.1-SNAPSHOT.jar"]
