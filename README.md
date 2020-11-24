# EriksCodingChallenge


Welcome to the coding challenge 

General information 

Project contains 2 microServices: 

1. Authentication service port 7074

2. Order service port 7075

You must run both 

------------------------------------------

How to start 

1. Send get to http://localhost:7074/authentication/hello to check that server is running 

Response should be status 200 and welcome to port 7074

2. Send get to http://localhost:7074/authentication/initDb response back with details of all users pick one and use it to login

This was created specific for you so you will not have to add users alone, you can if you want there is an endpoint http://localhost:7074/authentication/signup
String , String ,String ,String :: userEmail,password,firstName,lastName (JsonFormat)


3. send Post  http://localhost:7074/authentication/login  response with a token keep this token you will need it for the other service 


-------------------------------------------------

4 run order server send get http://localhost:7074/orders/hello (try both with and without the JWT ) Response should be status 200 and welcome to port 7075

5. use crud functionality based on the below endpoints 


		*POST http://localhost:7074/orders/add-new-order 
		** @RequestBody String orderJson {"orderId":"","totalPrice":00.00,"status":"","date":"somedateStringFormat"}
		*DELETE http://localhost:7074/orders/delete/{orderId}
		*GET http://localhost:7074/orders/get-order/{orderId}
		*GET http://localhost:7074/orders/get-all-order
		*POST http://localhost:7074/orders/update	
		** @RequestBody String orderJson {"orderId":"","totalPrice":00.00,"status":"","date":"somedateStringFormat"}

----------------------------------------------------



The application is using h2 embedded DB , if you don't have it install please do 
I have granted access to the DB 
Username = sa 
No password 

Both services use this db 

----------------------------------------------------


Object Lists 

Authentication service 


1. UserCredentialsDto (pojo)
2. UserCredentials (Entity)
3. UserCredentialsRepository (DAO ) 
4. loginController 
5. JWT Authentication classes including filter , configuration ,cors handling , utils 
6. Modelmapper 


Mojo--->modelmapper.map----> entity ----->repo.save 



order Server 


1.OrderDto(pojo)
2.Order(entity)
3.orderRepository (Jpa repo ) (Dao)
4.JWT Authentication classes including filter , configuration ,cors handling , utils 
5.orderController



<b> Extra security layer added by creating my own convertors AES algorithm</b>


For examples you can see the added screenshoots from postman




------------------------------------------

Unit testing 


Unit testing was only added to the authentication service, 

Mockito was used to test the controller layer 

And unit for unit testing of the supporting methods. 









 


 