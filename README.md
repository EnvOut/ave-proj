### Swagger documentation
    http://localhost:8080/swagger-ui.html#/vehicle-controller/

### How to run
    ./gradlew run

### Requarements
* JDK 13

## Examples
#### Search
POST: http://localhost:8080/api/vehicles/search
```json
{
  "firstPoint":{
    "longitude": 1,
    "latitude": 1
  },
  "secondPoint":{
    "longitude": 2,
    "latitude": 2
  }
}
```

#### Patch
PATCH: http://localhost:8080/api/vehicles/362c21b3-49d4-477b-a066-ce0dafbb5ca4
```
{
  "location": {
    "longitude": 1.13,
    "latitude": 1.2
  }
}
```

#### Get all
GET: http://localhost:8080/api/vehicles

#### Create vehicle
POST: http://localhost:8080/api/vehicles/
```
{
  "id": "362c21b3-49d4-477b-a066-ce0dafbb5ca4",
  "name": "some",
  "location": {
    "longitude": 1.1,
    "latitude": 1.2
  }
}
```