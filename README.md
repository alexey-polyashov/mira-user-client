# mira-user-client
Микросервис, реализующий функии gateway сервиса
 - авторизация
 - запрос списка пользователей

Сервис работает на порту 8081, адрес сервиса после запуска приложения - http://localhost:8081/mira-users-gateway/

API сервиса доступно для просмотра через swagger - http://localhost:8081/mira-users-gateway/swagger-ui.html

Есть опубликованный API - https://mira-gateway.herokuapp.com/swagger-ui.html


# Инструкция для запуска

## 1 Вариант - запуск как отдельного приложения

1. Склонировать репозиторий к себе на компьютер
2. Открыть проект в среде разработки
3. Указать имя профиля для спринга через переменную среды: spring.profiles.active=dev
4. Запустить приложение - http://localhost:8081/mira-users-gateway/swagger-ui.html

## 2 Вариант - запуск в docker контейнере

1. Склонировать репозиторий к себе на компьютер
2. Открыть проект в среде разработки
3. Указать имя профиля для спринга через переменную среды: spring.profiles.active=dev
4. В среде разработки выполнить команду install в maven, для получения jar файла в папке target: ./mvnw clean package -DskipTests
5. На компьютере должен быть установлен Docker (ссылка для установки - https://www.docker.com/get-started/) 
6. В терминале выполнить команду: docker build -t mira-gateway
7. Далее склонировать [этот](https://github.com/alexey-polyashov/mira-users-server) репозиторий и выполнить инструкции по запуску в docker контейнере
8. Выполнить инструкцию из второго репозитория - https://github.com/alexey-polyashov/mira-users-server

