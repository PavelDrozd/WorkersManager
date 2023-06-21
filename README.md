# Проект: Workers manager

#### Используемые зависимости:
- Spring Boot: 3.1.0
- Spring Framework, MVC, Data JPA
- Gradle: 8.0
- Docker
- Postman
- mysql-connector-java: 8.0.32
- ModelMapper: 3.1.1
- Lombok

#### Инструкция по развёртыванию и запуску приложения:
- Для развёртывания приложения необходимо будет использовать Gradle и Docker
- Используя консоль перейдите в корневую папку приложения WorkersManager
- Прописать в консоли: gradle build
- Подождать до момента пока приложение скомпилируется,т.к. для запуска в Docker конейнер необходим .jar файл проекта для запуска.
- Далее прописать в консоли в корневой папке приложения: docker-compose up
- Подождать покуда создаются контейнеры базы данных и приложения.
- В консоли прописать команду: docker exec -it mysql8 mysql -uroot -proot
- Далее прописать команды для развёртывания схемы: source docker/mysql/schema.sql и для тестовых данных: source docker/mysql/data.sql

Также приложение можно запустить альтернативными способами:
1.
- Docker при запуске базы данных раскрывается на порту 3306 и он должен быть свободен(можно самостоятельно изменить порт в docker-compose.yml в корневой папке приложения и в src/main/resources/application.yml изменить порт к которому будет обращаться приложения)
- Запустить базу данных отдельно в Docker контейнере командой в корневой папке приложения: docker-compose up mysql
- Дождаться развёртывания базы данных
- В консоли прописать команду: docker exec -it mysql8 mysql -uroot -proot
- Далее прописать команды для развёртывания схемы: source docker/mysql/schema.sql и для тестовых данных: source docker/mysql/data.sql
- Изменить поле spring.datasource.url в файле src/main/resources/application.yml на: jdbc:mysql://${MYSQL_HOST:localhost}:3306/workersmanager
- Зайдя в IDE запустить src/main/java/com/example/Application.java
2.
- Самостоятельно развернуть базу данных создав только базу данных с названием usermanager на порту 3306 либо изменив их в docker-compose.yml и application.yml
- Воспользоваться схемой schema.sql (также опционально использовать data.sql) которые находятся в папке docker/mysql приложения
- Изменить поле spring.datasource.url в файле src/main/resources/application.yml на: jdbc:mysql://${MYSQL_HOST:localhost}:3306/workersmanager
- Зайдя в IDE запустить src/main/java/com/example/Application.java

#### Отправка запросов:
- В корневой папке приложения находится папка postman с колекцией запросов 5 команд на каждую из двух сущностей Worker и Company
- GET запросы на получение всех Workers или Companies осуществляется командами: /api/workers /api/companies
- GET запросы на получение одного Worker или Company по ID осуществляется командами: /api/workers/{id} /api/companies/{id} где вместо {id} указать необходимое число
- POST запрос на создание осуществляется командами: /api/workers/create?company={name} /api/companies/create где в теле запроса по сущности Worker передаётся {"firstName","lastName","age","salary","profession"} и в параметр company передаётся название компании данной сущности, Company {"name","enterpriseFund","fieldOfActivity"}
- PUT запрос аналогичен запросу POST только в запрос добавляется id сущности которую хотим изменить: /api/workers/{id}?company={name} /api/companies/{id}
- DELETE запрос осуществляется передачей id пользователя для удаления: /api/workers/{id} /api/companies/{id}