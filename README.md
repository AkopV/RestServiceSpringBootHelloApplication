# RestServiceSpringBootHelloApplication
[![Build Status](https://travis-ci.org/AkopV/RestServiceSpringBootHelloApplication.svg?branch=master)](https://travis-ci.org/AkopV/RestServiceSpringBootHelloApplication)

Есть таблица contacts в SQL базе. В ней миллионы строк.
Поле
Тип
id
64 bit integer
name
varchar


Написать REST сервис hello с ресурсом
/hello/contacts?nameFilter=val


Запрос к contacts должен возвращать контакты из таблицы БД contacts. Параметр nameFilter обязателен. В него передаётся регулярное выражение. В возвращаемых данных не должно быть записей, в которых contacts.name совпадает с регулярным выражением.


Массив контактов возвращается в json формате


contacts: [ Contact, ... ]


Contact
{
	“id”: integer,
 	“name”: string
}


Пример запросов
/hello/contacts?nameFilter=^A.*$ - возвращает контакты, которые НЕ начинаются с A
/hello/contacts?nameFilter=^.*[aei].*$ - возвращает контакты, которые НЕ содержат букв a, e, i


Замечания и пожелания к реализации
Фильтр обязательно применять в java коде, не использовать возможности SQL.
В реализации обязательно учитывать огромное предполагаемое количество контактов и то, что легко написать фильтр, который возвращает их все.
Учитывать, что сервис должен быть готов одновременно обрабатывать множество запросов.
SQL БД можно использовать любую, предпочтительно PostgreSQL.
Сервис хотелось бы увидеть на Spring Boot, но не обязательно.
Коды ошибок HTTP использовать типичные для REST сервисов.
Сборка maven
Наличие тестов
К результату приложить объяснение, как развернуть БД, запустить приложение. Было бы здорово, если бы окружение поднималось с помощью Vagrant, виртуализация VirtualBox.


Код решения должен быть опубликован на GitHub’е без упоминания названия компании. В README должен присутствовать статус сборки от Travis CI (https://docs.travis-ci.com/user/status-images/) или Circle CI (https://circleci.com/docs/status-badges) в виде бейджика, по клику на который происходит переход на страницу сборки.

Для запуска приложения необходимо:

1) H2.database

2) Java 8

3) Maven

4) Spring Boot

5) Tomcat 8

Алгоритм:

1) Создаем логику проекта.

2) Подключаем базу данных. В настоящем проекте использовалось H2.database

3) Пишем контроллер, который будет непосредственно обрабатывать запросы от клиента 

4) Создаем фильтр с помощью регулярного выражения, для выборки данных из базы данных.

5) Выводим данные в JSON формате клиенту.

6) Тестируем приложение для проверки фильтра данных.

Выполнение.
В начале работы программа проверяет БД на количество записей. Если БД окажется пуста, тогда будут автоматически сгенерированы 1 миллион случайных контактов.

По умолчанию программа использует порт 8080. Если данный порт у вас занят, то необходимо ввести любой свободный порт в ресурсах программы в файлу application.properties изменить параметр:  server.port: 8090.

Адрес приложения: http://localhost:8080/hello/contacts Принимает обязательный параметр nameFilter, который содержит регулярное выражение, по которому необходимо отсортировать контакты.

Ответ вернется в формате json в виде:
{
  "contacts":[id, name];
}
