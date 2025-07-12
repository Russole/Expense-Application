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
docker build -t russole/expense-app .
docker pull russole/expense-app
docker run -d --name my-mysql -e MYSQL_ROOT_PASSWORD=850905 -e MYSQL_DATABASE=expensetransactions -p 3306:3306 mysql:8.0
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dockerLocal russole/expense-app
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dockerEC2 russole/expense-app
```
