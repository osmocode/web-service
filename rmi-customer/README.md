# RMI Bike Services

## Description
This service based on the middleware Java RMI allows the management of users of services dependent on it.

All accessible methods are defined in the Documentation API section.

## API Documentation

All services is binded on port `1099`.

## `/CustomerListService`

- Retrieve the customer with `uuid`
```java
    getCustomerByUUID(String uuid)
```
