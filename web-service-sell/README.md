# Web Service Sell

## Description

## REST API Documentation

### **GET** `/api/v2/sell/getArticleList`

**Request**
```ts
{
    token: string;
    currency: string;
}
```

**Response**
```ts
{
    token: string;
    currency: string;
}
```

### **POST** `/api/v2/sell/getArticle`

**Request**
```ts
{
    token: string;
    uuid: string;
    currency: string;
}
```

**Response**
```ts
{
    token: string;
    uuid: string;
    currency: string;
}
```

### **POST** `/api/v2/sell/postArticle`

**Request**
```ts
{
    token: string;
    uuid: string;
    price: number;
    currency: string;
}
```

**Response**
```ts
{
    token: string;
    uuid: string;
    price: number;
    currency: string;
}
```

### **GET** `/api/v2/sell/getBasket`

**Request**
```ts
{
    token: string;
    currency: string;
}

```

**Response**
```ts
{
    token: string;
    currency: string;
}
```

### **POST** `/api/v2/sell/postBasket`

**Request**
```ts
{
    token: string;
    bikeUuid: string;
    currency: string;
}
```

**Response**
```ts
{
    token: string;
    bikeUuid: string;
    currency: string;
}
```