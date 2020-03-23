# 基于哪个镜像
FROM java:8

# 将本地文件夹挂载到当前容器
VOLUME /tmp

# 拷贝文件到容器，也可以直接写成ADD erp-backend.jar /app.jar
ADD erp-backend.jar app.jar
RUN bash -c 'touch /app.jar'

# 开放8080端口
EXPOSE 8080

# 配置容器启动后执行的命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
