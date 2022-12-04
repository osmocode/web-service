# Web Service Bank

## Description

## REST API Documentation

### **GET** `/api/v2/bank/getWalletList`

**Request**
```ts
{
    uuid: string;
}
```

**Response**
```ts
{
    uuid: string;
}
```

### **POST** `/api/v2/bank/getBalance`

**Request**
```ts
{
    uuid: string;
    currency: string;
}
```

**Response**

```ts
{
    uuid: string;
    currency: string;
}
```

### **POST** `/api/v2/bank/addBalance`

**Request**
```ts
{
    uuid: string;
    currency: string;
    value: number;
}
```

**Response**

```ts
{
    uuid: string;
    currency: string;
    value: number;
}
```

### **POST** `/api/v2/bank/removeBalance`

**Request**
```ts
    uuid: string;
    currency: string;
    value: number;
```

**Response**
```ts
{
    uuid: string;
    currency: string;
    value: number;
}
```
