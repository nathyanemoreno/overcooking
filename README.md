# Overcooking API

Welcome to the Overcooking API! This Spring Boot API provides endpoints to manage recipes for the Overcooking app.

To run the Overcooking API locally, follow these steps:

1. **Build the project:**
    ```bash
    ./mvnw clean install
    ```

2. **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

The API will be accessible at `http://localhost:8080`.

## Usage

To use the Overcooking API, you can use tools like `curl` or Postman to send HTTP requests to the endpoints.

### Example

To get all recipes, send a GET request to:

```http
GET http://localhost:8080
