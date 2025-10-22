# projeto-sofware-ativo

Para rodar o mysql

```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=stocks -e MYSQL_USER=mysql -e MYSQL_PASSWORD=password --network=rede -p 3306:3306 -d mysql:8.0
```

Para rodar o mongo


```
docker run -p 27017:27017 -d --name=mongo mongo
```

Para rodar o redis


```
docker run -p 6379:6379 -d --name=redis redis
```