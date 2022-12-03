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

- Get the customer with `uuid`. The return value will be a `BikeService`.
```java
  getBikeByUUID(String uuid)
```

- Adds a bike to the list with his `label`, `description, `ownerUUID`, `bikeState`. The return value will be a `Map<UUID, BikeService>`.
```java
    add(String label, String description, UUID ownerUUID, BikeState bikeState)
```

### Bike
- Get bike `label`. The return value will be a `String`.
```java
  getLabel()
```

- Get bike `description`. The return value will be a `String`.
```java
  getDescription()
```

- Get bike `image`. The return value will be a `Image`.
```java
  getImage()
```

- Set bike `image`. The return value will be a `void`.
```java
  setImage()
```

- Get bike `ownerUUID`. The return value will be a `UUID`.
```java
  getOwnerUUID()
```

- Get bike `bikeState`. The return value will be a `BikeState`.
```java
  getBikeState()
```

- Get bike `feedbackHistory`. The return value will be a `List<FeedbackService>`.
```java
  getFeedbackHistory()
```

- Get bike `rentQueue`. The return value will be a `List<RentService>`.
```java
  getRentQueue()
```

- Get bike averageNote. The return value will be a `float`.
```java
  getAverageNote()
```

- Informs if the bike can be rent. The return value will be a `boolean`.
```java
    canBeRent()
```

- Informs if the bike can be sale. The return value will be a `boolean`.
```java
    canBeSale()
```

## `/RentListService`

### RentList

- Get all rents with their uuid. The return value will be a `HashMap<UUID, RentService>`.
```java
  getAll()
```

- Get the rent with `uuid`. The return value will be a `RentService`.
```java
  getRentByUUID(String uuid)
```

- Get the customer rent. The return value will be a `Map<UUID, RentService>`.
```java
  getRentByCustomerUUID(String customerUUID)
```

- Adds a rent to the list with his `start`, `end`, `customerClientUUID`, `bikeUUID`. The return value will be a `Map<UUID, RentService>`.
```java
    add(Date start, Date end, UUID customerClientUUID, UUID bikeUUID)
```

- Retrieves the rentals of a customer who has no feedback. The return value will be a `Map<UUID, RentService>`.
```java
    getRentsWithNoFeedbackByCustomer(String uuid)
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

- Get rent `bikeUUID`. The return value will be a `UUID`.
```java
  getBikeUUID()
```

## `/FeedbackListService`

### FeedbackList

- Get all feedbacks with their uuid. The return value will be a `HashMap<UUID, FeedbackService>`.
```java
  getAll()
```

- Get the feedback with `uuid`. The return value will be a `FeedbackService`.
```java
  getFeedbackByUUID(String uuid)
```

- Adds a feedback to the list with  `date`, `note`, `comment`, `bikeState`, `rentUUID`. The return value will be a `FeedbackService`.
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

- Get feedback `rentUUID`. The return value will be a `RentService`.
```java
  getRentUUID()
```
