# Bingo

A **Kotlin Multiplatform** project demonstrating API integration using **Ktor Client**
## Features

- **Cross-Platform**: Built with Kotlin Multiplatform (KMP) for Android and iOS
- **Networking**: Uses Ktor Client for HTTP requests with detailed logging

## API Used

The project integrates with a REST API endpoint:
**Base URL**: `https://composekmp.free.beeceptor.com`  
**Endpoint**: `POST /addaddress`

### Request Model Contract
```json
{
  "data": {
    "type": "home",
    "street": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001",
    "country": "USA"
  }
}
```

### Response Model
```json
{
  "status": "Address added"
}
```

## Project Structure Details

### Network Layer
- `HttpClientFactory`: Configures Ktor client with content negotiation and logging
- `AddressApiService`: Handles API requests with comprehensive logging
- `ApiConfig`: Centralized API configuration

### Data Layer
- `AddressModels`: Data classes for API requests and responses
- `AddressRepository`: Repository pattern implementation
- `ApiResult`: Sealed class for handling API response states

### Domain Layer
- `AddressManager`: Business logic layer with simplified API interface

### Examples
- `SimpleMain`: Entry point demonstrating API usage
- `AddressApiExample`: Comprehensive example of address API operations

## Application Output

```
 Running Address API Example...

 Testing Home Address API...
Request: {type=home, street=123 Main Street, city=New York, state=NY, zipCode=10001, country=USA}
2025-08-05 21:06:23 INFO HttpClient - REQUEST: https://composekmp.free.beeceptor.com/addaddress
METHOD: HttpMethod(value=POST)
2025-08-05 21:06:24 INFO HttpClient - RESPONSE: 200 OK
METHOD: HttpMethod(value=POST)
FROM: https://composekmp.free.beeceptor.com/addaddress
Response: Address added

 All API tests completed successfully!
 Resources cleaned up
```