#Offices micro-service

##Preconditions

You need to have docker installed and running to execute the following

```
docker run --name offices-postgres -p 5432:5432 -e POSTGRES_PASSWORD=offices -e POSTGRES_USER=offices -d postgres
```

##How to use

Import collection from postman directory

##How to build

```
docker build .
```