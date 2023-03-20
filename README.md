# Paguelofacil
![](https://assets.paguelofacil.com/images/logos-svg/paguelofacil.svg)

## Resumen
Esta API rest intenta simular la API de la empresa "PAGUELOFACIL". Permite consultar tus transacciones y guardarlas automaticamente en una base de datos, para luego poder ser filtradas y ordenadas en base a los mismos parametros de la api original.
## Variables de entorno
En el archivo application.properties se pueden configurar las variables de entorno y los perfiles de desarrollaor o produccion.
Es necesario crear una cuenta DEMO en la web de PAGUELOFACIL , para obtener el token que nos permite consultar movimientos y realizar pruebas.

```
- #Datasoruce
spring.datasource.url=jdbc:mysql://${localhost:PORT}/${BD_NAME}
spring.datasource.username=${USERNAME}
spring.datasource.password=${PASSWORD}
#Hibernate
spring.jpa.hibernate.ddl-auto=update
#Sql
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
transaction.filter=fieldName::fieldValue|fieldName2::fieldValue2
paguelofacil.token={TOKEN}
paguelofacil.transactions.url=https://sandbox.paguelofacil.com/PFManagementServices/api/v1/MerchantTransactions
logging.level.org.springframework=

```
## Endpoints
```
GET Http://localhost:8080/TransactionsGetAndSave
```
Permite consultar las transacciones originales de la cuenta y guardarlas en nuestra base de datos para poder filtrarlas y ordenarlas.
Se pasa automaticamente como header de "Autorization" el token obtenido previamente (no es necesario incluir la palabra "Bearer")

```
GET Http://localhost:8080/transactions 
```
Muestra las transacciones que se guardaron previamente en la base de datos y se pueden pasar por parametro los filtros y condicionales necesarios.
```
 @RequestParam(value = "filter", required = false) String filter,
      @RequestParam(value = "conditional", required = false) String conditional,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "offset", required = false) Integer offset,
      @RequestParam(value = "sort", required = false) String sort,
      @RequestParam(value = "fields", required = false) String fields
```
A modo de ejemlo s establece el siguiente parametro de busqueda 

 **http://localhost:8080/tansactions?conditional=customFields%24lk%25tramiteId%25%7CcustomFields%24lk%25TID2345%25&limit=1**
 
 _El parametro Filter String	{field}::{value}|{field2}::{value} Conditional	Permite realizar filtros y consultas sobre el servicio. Los Query Param son no casesensitive_
 
 _El parametro Conditional String		{field}{operator}{value}|{field2}{operator $bt}{value}::{value2}_
 
 _El parametro Limit Indica el numero maximo de resultados esperados_
 
 _El parametro Offset Indica desde donde se retornara la consulta, esto es usado para paginacion_
 
 _El parametro Sort {field},{-field2} Permite ordenar la consulta, si quiere hacer un order descendente use - antes del campo_
 
 _El parametro Field {field},{field2::{Operator}} Permite retornar solo los campos indicados, y aplicar operaciones sobre estos_
 
 ## Tecnologias
 Se usó Java con Springboot y para la base de datos sencilla mysql.
 
 ## Contacto
 Luego de la visita de esta pagina se pueden oner en contacto a agustin.kiryk@gmail.com
 
 ## Desarrollado por
 **Agustin Kiryk**

