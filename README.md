# Deploy

## heroku

- build application : `mvn clean install`
- deploy : `heroku deploy:jar target/routine-0.0.1-SNAPSHOT.jar --app api-routine`
- check log : `heroku logs --tail`