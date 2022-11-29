# REST API Service

## Description

## API Documentation

### **GET** `/bike` ✅

**Field**

**Response**
```json
{
  
}
```

### **PUT** `/bike` ✅

**Fields**
* `image` Optional
* `ownerId`

**Response**
```json
{
  "uuid": "...",
  "image": "...",
  "owner": "...",
  "feedback": [],
  "location": []
}
```

### **GET** `/bike/<uuid:string>` 

**Fields**

**Response**
```json
{
  "uuid": "...",
  "image": "...",
  "owner": "...",
  "feedback": [],
  "location": []
}
```

### **UPDATE** `/bike/<uuid:string>`

**Fields**
```json
{
  "image": "..."
}
```

**Response**
```json
{
  "uuid": "...",
  "image": "...",
  "owner": "...",
  "feedback": [],
  "location": []
}
```

### **DELETE** `/bike/<uuiD:string>`

**Fields**

**Response**
```json
{
  "uuid": "...",
  "image": "...",
  "owner": "...",
  "feedback": [],
  "location": []
}
```

### **GET** `/feedback`

**Fields**

**Response**
```json
{

}
```

### **PUT** `/feedback`

**Fields**
* `date`
* `note`
* `comment`
* `bikeState`
* `location`

**Response**
```json
{
  "date": "...",
  "note": "...",
  "comment": "...",
  "bikeState": "...",
  "location": "..."
}
```

### **GET** `/feedback/<uuid:string>`

**Fields**

**Response**
```json
{
  "date": "...",
  "note": "...",
  "comment": "...",
  "bikeState": "...",
  "location": "..."
}
```

### **UPDATE** `/feedback/<uuid:string>`

**Fields**
* `date`
* `note`
* `comment`
* `bikeState`

**Response**
```json
{
  "date": "...",
  "note": "...",
  "comment": "...",
  "bikeState": "...",
  "location": "..."
}
```

### **DELETE** `/feedback/<uuid:string>`

**Fields**

**Response**
```json
{
  "date": "...",
  "note": "...",
  "comment": "...",
  "bikeState": "...",
  "location": "..."
}
```

### **GET** `/location`

**Fields**

**Response**
```json
{
  
}
```

### **PUT** `/location`

**Fields**
* `start:Date` 
* `end:Date`
* `customerClient`

**Response**
```json
{
  "start": "...",
  "end": "...",
  "customerClient": "..."
}
```

### **GET** `/location/<uuid:string>`

**Fields**

**Response**
```json
{
  "start": "...",
  "end": "...",
  "customerClient": "..."
}
```


### **UPDATE** `/location/<uuid:string>`

**Fields**
* `end:Date`

**Response**

```json
{
  "start": "...",
  "end": "...",
  "customerClient": "..."
}
```

### **DELETE** `/location/<uuid:string>`

**Fields**

**Response**
```json
{
  "start": "...",
  "end": "...",
  "customerClient": "..."
}
```

### **GET** `/customer`

**Fields**

**Response**
```json
{
  
}
```

### **PUT** `/customer`

**Fields**
* `firstName:string`
* `lastName:string`
* `customerType`

**Response**
```json
{
  "uuid": "...",
  "firstName": "...",
  "lastName": "...",
  "customerType": "..."
}
```

### **GET** `/customer/<uuid:string>`

**Fields**

**Response**
```json
{
  "uuid": "...",
  "firstName": "...",
  "lastName": "...",
  "customerType": "..."
}
```

### **UPDATE** `/customer/<uuid:string>`

**Fields**
* `firstName:string`
* `lastName:string`
* `customerType`

**Response**
```json
{
  "uuid": "...",
  "firstName": "...",
  "lastName": "...",
  "customerType": "..."
}
```

### **DELETE** `/customer/<uuid:string>`

**Fields**

**Response**
```json
{
  "uuid": "...",
  "firstName": "...",
  "lastName": "...",
  "customerType": "..."
}
```