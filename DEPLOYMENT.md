## Despliegue de la aplicación

1. Compilar la aplicación `$ ./mvnw package`
2. Heroku login
3. heroku create
4. heroku container:login
5. heroku container:push web -a account-ms-g71
6. heroku container:release web