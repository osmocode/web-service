# RMI Bike Services

## Description
This service based on the middleware Java RMI allows users to provide or reserve bicycles.  
This service depends on another service which is [RMI Customers](https://github.com/osmocode/web-service/tree/main/rmi-customer).  

All accessible methods are defined in the Documentation API section.

## API Documentation

All services is binded on port `1099`.

## `/BikeListService`

- List of all bikes
```java
  getBikeList()
```

- List of all bikes owned by owner
```java
getBikeList(String owner)
```

- Retrieve the bike with the `uuid`
```java
getBikeByUUID(String uuid)
```

## `/LocationListService`

## `/FeedbackListService`
