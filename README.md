# Deploy

## heroku

- build application : `mvn clean install`
- deploy : `heroku deploy:jar target/routine-0.0.1-SNAPSHOT.jar --app api-routine`
- check log : `heroku logs --tail`

## DB

    spring.datasource.url=jdbc:mysql://db4free.net:3306/routineapi
    spring.datasource.username=routineapi
    spring.datasource.password=routineapi