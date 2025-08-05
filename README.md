## Bingo

A **Kotlin Multiplatform** project demonstrating API integration using **Ktor Client**
## Features

- **Cross-Platform**: Built with Kotlin Multiplatform (KMP) for Android and iOS
- **Networking**: Uses Ktor Client for HTTP requests with detailed logging

## Functionalities Exposed

### 1. Helper Function

- **validateAddress(address: Map<String, String>): ValidationResult**:
  Validates an address map and returns a list of if errors exist else blank

### 2. API

The project integrates with a REST API endpoint:
**Base URL**: `https://composekmp.free.beeceptor.com`  
**Endpoint**: `POST /addaddress`

### API Output

```
Testing Home Address API...
Payload: {type=home, street=123 Main Street, city=New York, state=NY, zipCode=10001, country=USA}
HttpClient - REQUEST URL: https://composekmp.free.beeceptor.com/addaddress
METHOD: HttpMethod(value=POST)

Response: 
{ "status": "Address added"}

All API tests completed successfully!
Resources cleaned up
```