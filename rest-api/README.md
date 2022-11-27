# REST API Service

## Description

## API Documentation

### **GET** `/bike`

**Field**

**Response**
```json
{
  
}
```

### **PUT** `/bike`

**Fields**
* `image` Optional

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

### **GET** `bike/<uuid:string>`

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

### **UPDATE** `bike/<uuid:string>`

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