#### Description ####
* This is a toy project requested by a technical services firm to demonstrate my minimum competency with developing a Java based RESTful microservices application.
* I chose the "easy button" approach using Spring Boot/Web/Data that uses JPA/Hibernate under the covers with H2 as a database just to demonstrate the proof of concept.
* Additionally, modern tools such as Lombok, gradle were used to reduce overall verbosity (aka _"cognitive load"_) and speed development.
#### Features ####
* Implementation of REST endpoints for JSON -> JPA Entities and vice-versa.  
|Operation | URI                 | Description
|----------|---------------------|----------------------------
|     POST | /order              | Create an new order with content body.
|     GET  | /order/{orderId}    | Fetch a specific order.
|     GET  | /{customerId}/order | Fetch order summar(y/ies) for a specific customer.  Obviously, the more you post for a specific customer, the longer the array becomes.
* Every organization/client has their own standards and practices, so these endpoints aren't necessarily optimal.
Input data format:
```json
{
  "customerId": "string",
  "items": [
    {
      "productId": "string",
      "quantity": 0
    }
  ]
}
```
#### Testing ####
[Integration]
* Build and run: ./gradlew
* You can use Postman (a Chrome app) or curl to integration test
* If you care to use curl, copy/paste below for bash or git-bash.exe in a terminal window
```sh
curl -X POST \
  http://localhost:4242/order \
  -H 'content-type: application/json' \
  -d '{
        "customerId": "12345",
        "items": [
          {
            "productId": "8ed0e6f7",
            "quantity": 1
          },
          {
            "productId": "c0258525",
            "quantity": 3
          },
          {
            "productId": "0a207870",
            "quantity": 2
          }
        ]
      }
```
* You should get back a 201 HTTP status and an order summary in the content body that looks like:
```json
{
    "orderId": 1,
    "customerId": "12345",
    "total": 155.35,
    "orderItems": [
        {
            "itemId": 1,
            "productId": "8ed0e6f7",
            "quantity": 1,
            "unitPrice": 124.95
        },
        {
            "itemId": 2,
            "productId": "c0258525",
            "quantity": 3,
            "unitPrice": 3.5
        },
        {
            "itemId": 3,
            "productId": "0a207870",
            "quantity": 2,
            "unitPrice": 9.95
        }
    ]
}
```
[Unit]
* no time for this without being paid...this uses an external API which requires service mocking and repository tests
