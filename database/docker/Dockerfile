FROM mysql:8.0.17

LABEL maintainer="Mariusz Marciniuk"

ENV MYSQL_USER root
ENV MYSQL_ROOT_PASSWORD secredPassword

COPY ./initializationDataBase.MySql/document-archiver-db-schema-MySQL.sql /docker-entrypoint-initdb.d/

EXPOSE 3337:3306