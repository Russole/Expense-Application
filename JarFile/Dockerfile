# 使用你需要的 JDK（這裡是 Oracle JDK 24）
FROM container-registry.oracle.com/java/openjdk:24

# 建立資料夾
WORKDIR /app

# 複製整個 jars 資料夾到容器中
COPY restapi-cfd4ee0-c5Xlarge.jar /app/restapi-cfd4ee0-c5Xlarge

# 設定容器啟動時執行的 JAR
ENTRYPOINT ["java", "-jar", "/app/restapi-cfd4ee0-c5Xlarge"]
