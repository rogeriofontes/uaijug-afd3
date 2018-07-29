# uaijug-afd3


Para Consultar os dados do Cliente (Metodo GET):

```
 curl -H "Accept: application/json" -H "Content-type: application/json" -X GET http://localhost:8080/clientes
```

Para inserir os dados do Cliente (Metodo POST):

```
 curl -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"nome": "Rogerio Fontes 1", "email": "root@localhost", "cpf": "323333333333"}' http://localhost:8080/clientes
```

Para Alterar os dados do Cliente (Metodo PUT):

```
 curl -H "Accept: application/json" -H "Content-type: application/json" -X PUT -d '{"nome": "Rogerio Fontes 2", "email": "root@localhost", "cpf": "323333333333"}' http://localhost:8080/clientes/0
```

Para Excluir os dados do Cliente (Metodo PUT):

```
 curl -H "Accept: application/json" -H "Content-type: application/json" -X DELETE http://localhost:8080/clientes/0
```