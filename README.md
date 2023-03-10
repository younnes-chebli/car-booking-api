# Car Booking API

Extension and improvement of our [CLI Car Booking Application](https://github.com/younnes-chebli/cli-car-booking-application) to a REST API that will constitute our backend and expose a series of endpoints.

## Technologies Used
* **Java 17**
* **Maven**
* **Spring Boot 3**
* **Spring Data JPA**
* **PostgreSQL Database** running on
* **Docker**

## Endpoints

| Endpoints                                 | Description                                            |
|-------------------------------------------|--------------------------------------------------------|
| **Customers**                             |                                                        |
| ```GET /api/v1/customers```               | Retrieves **All** the **Customers**                    |
| ```GET /api/v1/customers/{id}```          | Retrieves the **Customer** with the specified **id**   |
| ```POST /api/v1/customers```              | Adds a **New Customer**                                |
| ```PUT /api/v1/customers/{id}```          | **Updates** the Customer with the specified **id**     |
| ```DELETE /api/v1/customers/{id}```       | **Deletes** the Customer with the specified **id**     |
| **Cars**                                  |
| ```GET /api/v1/cars```                    | Retrieves **All** the **Cars**                         |
| ```GET /api/v1/cars/{id}```               | Retrieves the **Car** with the specified **id**        |
| ```GET /api/v1/cars/rn/{regNumber}```     | Retrieves the **Car** with the specified **regNumber** |
| ```GET /api/v1/cars/available```          | Retrieves the **available** cars                       |
| ```GET /api/v1/cars/available-electric``` | Retrieves the **availabe electric** cars               |
| ```POST /api/v1/cars```                   | Adds a **New Car**                                     |
| ```PUT /api/v1/cars/{id}```               | **Updates** the Car with the specified **id**          |
| ```DELETE /api/v1/cars/{id}```            | **Deletes** the Car with the specified **id**          |
| **Bookings**                              |
| ```GET /api/v1/bookings```                | Retrieves **All** the **Bookings**                     |
| ```GET /api/v1/bookings/{id}```           | Retrieves the **Booking** with the specified **id**    |
| ```POST /api /v1/bookings```              | Adds a **New Booking**                                 |

