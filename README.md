# ktor-mysql-di

A very basic setup to expand upon that serves up data from a MySQL server

Useful links:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor WAR Documentation](https://ktor.io/docs/server-war.html)

## Features

Koin DI, MySQL, Swagger, Tomcat-Ready

## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                  |
|-------------------------------|----------------------------------------------|
| `./gradlew test`              | Run the tests                                |
| `./gradlew build`             | Build everything                             |
| `./gradlew war`               | Build a WAR file for deployment              |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8085
```

