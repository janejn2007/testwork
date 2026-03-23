# TestWork

Фреймворк для интеграционного тестирования веб-приложения с использованием Selenium WebDriver, REST Assured и MockWebServer.

## Технологии

- Java 17
- JUnit 5
- REST Assured
- Selenium WebDriver
- MockWebServer (OkHttp)
- WebDriverManager
- Maven

### Запуск тестов

# Сборка проекта
mvn clean compile

# Запуск всех тестов
mvn test

# Запуск конкретного теста
mvn -Dtest=UserLifecycleTest test

Для проверки работоспособности  сделаны дополнительно  API тесты в отдельном  классе

# Параметризация
Добавлена параметризация. Реализовано в отдельной ветке parametrs.
Источником данных для  теста служит AddUserEnum.
