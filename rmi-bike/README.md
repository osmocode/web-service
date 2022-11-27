# RMI Bike Services

## Description
This service based on the middleware Java RMI allows users to provide or reserve bicycles.  
This service depends on another service which is [RMI Customers](https://github.com/osmocode/web-service/tree/main/rmi-customer).  

All accessible methods are defined in the Documentation API section.

## API Documentation

All services is binded on port `1099`.

## `/BikeListService`

### BikeList
- Get all bikes with their uuid. The return value will be a `HashMap<UUID, BikeService>`.
```java
  getAll()
```

- Get the customer with `uuid`. The return value will be a `Optional<BikeService>`.
```java
  getBikeByUUID(String uuid)
```

- Adds a bike to the list with his `firstName`, `lastName`, `bikeState`. The return value will be a `void`.
```java
    add(Image image, UUID ownerUUID, BikeState bikeState)
```

### Bike

- Get bike `image`. The return value will be a `Image`.
```java
  getImage()
```

- Get bike `ownerUUID`. The return value will be a `UUID`.
```java
  getOwnerUUID()
```

- Get bike `bikeState`. The return value will be a `BikeState`.
```java
  getBikeState()
```

- Get bike `rentHistory`. The return value will be a `List<FeedbackService>`.
```java
  getRentHistory()
```

- Get bike `rentQueue`. The return value will be a `List<RentService>`.
```java
  getRentQueue()
```

- Get bike averageNote. The return value will be a `float`.
```java
  getAverageNote()
```

## `/RentListService`

### RentList

- Get all rents with their uuid. The return value will be a `HashMap<UUID, RentService>`.
```java
  getAll()
```

- Get the rent with `uuid`. The return value will be a `Optional<RentService>`.
```java
  getRentByUUID(String uuid)
```

- Adds a rent to the list with his `start`, `end`, `customerClientUUID`. The return value will be a `void`.
```java
    add(Date start, Date end, UUID customerClientUUID)
```

### Rent

- Get rent `start`. The return value will be a `Date`.
```java
  getStart()
```

- Get rent `end`. The return value will be a `Date`.
```java
  getEnd()
```

- Get rent `customerClientUUID`. The return value will be a `UUID`.
```java
  getCustomerClientUUID()
```

## `/FeedbackListService`

### FeedbackList

- Get all feedbacks with their uuid. The return value will be a `HashMap<UUID, FeedbackService>`.
```java
  getAll()
```

- Get the feedback with `uuid`. The return value will be a `Optional<FeedbackService>`.
```java
  getFeedbackByUUID(String uuid)
```

- Adds a rent to the list with his `start`, `end`, `customerClientUUID`. The return value will be a `void`.
```java
    add(Date date, int note, String comment, BikeState bikeState, String rentUUID)
```

### Feedback

- Get feedback `date`. The return value will be a `Date`.
```java
  getDate()
```

- Get feedback `note`. The return value will be a `int`.
```java
  getNote()
```

- Get feedback `comment`. The return value will be a `String`.
```java
  getComment()
```

- Get feedback `bikeState`. The return value will be a `BikeState`.
```java
  getBikeState()
```

- Get feedback `rentUUID`. The return value will be a `UUID`.
```java
  getRentUUID()
```
