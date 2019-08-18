# Creating database with docker - building image
##### To build image use:

`docker build -t document-archive-db-mysql:<tag> .`

Example:
* `docker build -t document-archive-db-mysql:latest .`
* `docker build -t document-archive-db-mysql:1.0 .`

##### Alternatively following command can be used:

`mvn clean install -Ddocker.image.pre-integration-test.skip=false`

##### To run builded image use command:

`docker run -t -d -p 3337:3306 --hostname documentArchive --name document-archive-db-mysql document-archive-db-mysql:<tag>` 


# NamingConventions for database
## Naming convention of Test Request Manager database

|Object                 |Notation  |Length|Plural|Prefix|Suffix|Char Mask  |Example                  |
|-----------------------|----------|------|------|------|------|-----------|-------------------------|
|Schema                 |lowercase |30    |No    |No    |No    |[A-z][0-9] |my_schema                |
|Table                  |lowercase |50    |No    |No    |No    |[A-z][0-9] |my_table                 |
|Column                 |PascalCase|100   |No    |No    |No    |[A-z][0-9] |MyColumn                 |
|Constraint Unique Index|PascalCase|100   |No    |IX_   |No    |[A-z][0-9] |IX_MyConstrainUniqueIndex|
|Constraint Unique      |PascalCase|100   |No    |UQ_   |No    |[A-z][0-9] |UQ_MyConstrainUnique     |
|Table Primary Key      |PascalCase|100   |No    |PK_   |No    |[A-z][0-9] |PK_MyTableID             |
|Table Foreign Key      |PascalCase|100   |No    |FK_   |No    |[A-z][0-9] |FK_ColumnIdMyTable       |
