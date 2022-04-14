# mira-user-client
Микросервис, реализующий функии gateway сервиса
 - авторизация
 - запрос списка пользователей

Сервис работает на порту 8081, адрес сервиса после запуска приложения - http://localhost:8081/mira-users-gateway/

API сервиса доступно для просмотра через swagger - http://localhost:8081/mira-users-gateway/swagger-ui.html


# Как запустить

1. Склонировать репозиторий к себе на компьютер
2. В среде разработки выполнить команду install в maven, для получения jar файла в папке target
3. На компьютере должен быть установлен Docker (ссылка для установки - https://www.docker.com/get-started/) 
4. В терминале выполнить команду: docker build -t mira-gateway
5. Далее склонировать [этот](https://github.com/alexey-polyashov/mira-users-server) репозиторий и выполнить инструкции по запуску
