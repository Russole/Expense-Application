# Expense-Application

## How To Run

### FrontEnd

```
cd Frontend
npm run dev
```

### BackEnd

```
cd Backend／restapi
```

### Docker File

```
docker network create my-network
docker build -t russole/expense-app .
docker pull russole/expense-app
docker run -d --network my-network --name my-mysql -e MYSQL_ROOT_PASSWORD=850905 -e MYSQL_DATABASE=expensetransactions -p 3306:3306 mysql:8.0
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dockerLocal russole/expense-app
docker run -p 8080:8080 --network my-network -e SPRING_PROFILES_ACTIVE=dockerEC2 russole/expense-app:1.2.0
docker run --rm -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dockerEC2 --name Expense-App -m 8g --memory-swap -1 --cpus=4 russole/expense-app:1.1.3
docker run --rm -p 8080:8080 --network my-network -e SPRING_PROFILES_ACTIVE=dockerEC2 --name Expense-App russole/expense-app:1.2.0
docker run -d --network my-network --name redis-local -p 6379:6379 redis:7
docker logs -f Expense-App
```

### Redis

```
docker exec -it redis-local redis-cli
```
