# Стадия сборки
FROM maven:3.8.4-openjdk-17 AS builder

WORKDIR /app

# Копируем файл pom.xml
COPY pom.xml .

# Копируем все остальные файлы проекта
COPY src ./src

# Собираем проект
RUN mvn clean package

# Стадия выполнения
FROM openjdk:17-jdk-slim
WORKDIR /app

# Копируем скомпилированный JAR файл из стадии сборки
COPY --from=builder /app/target/task_management_system-0.0.1-SNAPSHOT.jar ./task_management_system-0.0.1-SNAPSHOT.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "task_management_system-0.0.1-SNAPSHOT.jar"]
