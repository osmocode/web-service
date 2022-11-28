# RMI Bike Services

## Description
This service based on the middleware Java RMI allows the management of users of services dependent on it.

All accessible methods are defined in the Documentation API section.

## API Documentation

All services is binded on port `1099`.

## `/CustomerListService`

### CustomerList

- Get all customers with their uuid. The return value will be a `HashMap<UUID, CustomerService>`.
```java
    getAll()
```

- Get the customer with `uuid`. The return value will be a `CustomerService`.
```java
    getCustomerByUUID(String uuid)
```

- Adds a customer to the list with his `firstName`, `lastName`, `customerType` `password`. The return value will be a `CustomerService`.
```java
    add(String firstName, String lastName, CustomerType customerType, String password)
```

### Customer

- Get customer `firstName`. The return value will be a `String`.
```java
    getFirstName()
```

- Get customer `lastName`. The return value will be a `String`.
```java
    getLastName()
```

- Get customer `customerType`. The return value will be a `String`.
```java
    getCustomerType()
```

- Get customer `password`. The return value will be a `String`.
```java
    getPassword()
```

- Get customer `bikes`. The return value will be a `List<UUID>`.
```java
    getBikes()
```

- Get customer `actualBikeRent`. The return value will be a `UUID`.
```java
    getActualBikeRent()
```

- SET customer `actualBikeRent`. The return value will be a `void`.
```java
    setActualBikeRent()
```

- Informs if the customer can rent. The return value will be a `boolean`.
```java
    canRent()
```

- Informs if the customer can propose a bike. The return value will be a `boolean`.
```java
    canProposeBike()
```
