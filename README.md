# Car Booking API

Extension and improvement of our [CLI Car Booking Application](https://github.com/younnes-chebli/cli-car-booking-application) to a REST API that will constitute our backend and expose a series of endpoints.

## Technologies Used
* **Java 17**
* **Maven**
* **Spring Boot 3**

## Endpoints

| HTTP                                | Description                                            |
|-------------------------------------|--------------------------------------------------------|
| **Customers**                       |                                                        |
| ```GET /api/v1/customers```         | Retrieves **all** the **Customers**                    |
| ```GET /api/v1/customer/{id}```     | Retrieves the **Customer** with the specified **id**   |
| **Cars**                            |
| ```GET /api/v1/cars```              | Retrieves **all** the **Cars**                         |
| ```GET /api/v1/car/{id}```          | Retrieves the **Car** with the specified **id**        |
| ```GET /api/v1/car/{regNumber}```   | Retrieves the **Car** with the specified **regNumber** |
| **Bookings**                        |
| ```GET /api/v1/bookings```          | Retrieves **all** the **Bookings**                     |
| ```GET /api/v1/booking/{id}```      | Retrieves the **Booking** with the specified **id**    |

