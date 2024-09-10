# Backend Liv2Train

This project is a Spring Boot application for managing educational centers. It provides APIs for creating, retrieving, and searching for centers based on various criteria.

## Table of Contents

1. [Setup](#setup)
2. [Database Seeding](#database-seeding)
3. [API Documentation](#api-documentation)
   - [Create a Center](#create-a-center)
   - [Search Centers](#search-centers)

## Setup

1. Clone the repository:
   ```
   git clone https://github.com/Rishabh-Kumar01/Backend_Liv2Train_Rishabh_Kumar.git
   ```

2. Navigate to the project directory:
   ```
   cd Backend_Liv2Train_Rishabh_Kumar
   ```

3. Configure the `application.properties` file in `src/main/resources`:
   ```properties
   spring.application.name=Backend_Liv2Train_Rishabh_Kumar

    # Database Configuration
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/database_name
    spring.datasource.username=mysql_username
    spring.datasource.password=mysql_password
    
    # Hibernate properties
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
    spring.jpa.hibernate.ddl-auto=update
    
    # Show SQL queries in console (optional, for debugging)
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    
    # Swagger Configuration
    springdoc.api-docs.path=/api-docs
    springdoc.swagger-ui.path=/swagger-ui.html

   ```
   
   Replace `database_name`,  `mysql_username` and `mysql_password` with your actual database credentials.

4. Build the project:
   ```
   ./mvnw clean install
   ```

5. Run the application:
   ```
   ./mvnw spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

## Database Seeding

The application includes a seeder to populate the database with initial data. The seeder is implemented in `CenterAddressSeeder.java` and uses data from `src/main/resources/data/centers.json`.

To modify the initial data:

1. Edit the `centers.json` file.
2. Rebuild and restart the application.

The seeder will automatically run on application startup if the database is empty.

## API Documentation

### Swagger UI

The API documentation is available via Swagger UI. After starting the application, you can access the Swagger UI at:

```
http://localhost:8080/swagger-ui.html
```

This interface provides a comprehensive view of all available endpoints, allowing you to test them directly from the browser.

### Using Postman

[Postman](https://www.postman.com/) is a popular API client that makes it easy to create, share, test and document APIs. Here's how to use Postman with our endpoints:

1. Download and install Postman from [https://www.postman.com/downloads/](https://www.postman.com/downloads/)
2. Open Postman and create a new request for each of the following endpoints.

### Test Endpoint

### Create a Center

**Endpoint:** `POST /api/centers`

**Postman Setup:**
1. Create a new POST request
2. Enter the URL: `http://localhost:8080/api/centers`
3. Go to the "Headers" tab and add:
   - Key: `Content-Type`
   - Value: `application/json`
4. Go to the "Body" tab:
   - Select "raw"
   - Select "JSON" from the dropdown
   - Enter the following JSON:

```json
{
  "centerName": "Tech Innovators Hub",
  "centerCode": "TIH123456789",
  "address": {
    "detailedAddress": "123 Innovation Avenue, Tech Park",
    "city": "Bangalore",
    "state": "Karnataka",
    "pincode": "560001"
  },
  "studentCapacity": 500,
  "coursesOffered": [
    "Data Science",
    "Artificial Intelligence",
    "Cloud Computing"
  ],
  "contactEmail": "info@techinnovatorshub.com",
  "contactPhone": "9876543210"
}
```

5. Click "Send"

**Expected Response:**
```json
{
  "id": 1,
  "centerName": "Tech Innovators Hub",
  "centerCode": "TIH123456789",
  "address": {
    "id": 1,
    "detailedAddress": "123 Innovation Avenue, Tech Park",
    "city": "Bangalore",
    "state": "Karnataka",
    "pincode": "560001",
    "createdOn": 1631234567
  },
  "studentCapacity": 500,
  "coursesOffered": [
    "Data Science",
    "Artificial Intelligence",
    "Cloud Computing"
  ],
  "contactEmail": "info@techinnovatorshub.com",
  "contactPhone": "+919876543210",
  "createdOn": 1631234567
}
```

### Search Centers

**Endpoint:** `GET /api/centers/search`

**Postman Setup:**
1. Create a new GET request
2. Enter the base URL: `http://localhost:8080/api/centers/search`
3. Go to the "Params" tab and add your search parameters. For example:
   - Key: `city`, Value: `Bangalore`
   - Key: `coursesOffered`, Value: `Data`
4. The full URL will look like: `http://localhost:8080/api/centers/search?city=Bangalore&coursesOffered=Data`
5. Click "Send"

**Available Query Parameters:**
- `detailedAddress` (string, optional): Partial match for address
- `city` (string, optional): Exact match for city
- `state` (string, optional): Exact match for state
- `pincode` (string, optional): Exact match for pincode
- `coursesOffered` (string, optional): Partial match for any course offered
- `centerName` (string, optional): Partial match for center name
- `contactEmail` (string, optional): Exact match for contact email
- `contactPhone` (string, optional): Exact match for contact phone

**Expected Response:**
```json
[
  {
    "id": 1,
    "centerName": "Tech Innovators Hub",
    "centerCode": "TIH123456789",
    "address": {
      "id": 1,
      "detailedAddress": "123 Innovation Avenue, Tech Park",
      "city": "Bangalore",
      "state": "Karnataka",
      "pincode": "560001",
      "createdOn": 1631234567
    },
    "studentCapacity": 500,
    "coursesOffered": [
      "Data Science",
      "Artificial Intelligence",
      "Cloud Computing"
    ],
    "contactEmail": "info@techinnovatorshub.com",
    "contactPhone": "+919876543210",
    "createdOn": 1631234567
  },
  // ... other matching centers
]
```

## Notes

- The `contactPhone` is automatically formatted to include the "+91" prefix if not provided.
- The `contactEmail` is stored in lowercase in the database for consistency.
- The `createdOn` field is automatically set to the current timestamp when a center is created.
- All search operations are case-insensitive.

For any issues or feature requests, please open an issue in the GitHub repository.
