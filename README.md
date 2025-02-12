# Spring Boot Backend

## Project Overview
This project is a Spring Boot application that provides a backend system for managing users, patients, and heart rate data. It includes RESTful API endpoints for user registration, login, and patient management, as well as heart rate data recording.

## Technologies Used
- Spring Boot
- Spring Data JPA
- H2 Database (or PostgreSQL/MySQL)
- Lombok
- Swagger (Springdoc OpenAPI)

## Project Setup

### Prerequisites
- Java 11 or higher
- Maven or Gradle
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation
1. Clone the repository:
   ```
   git clone <repository-url>
   cd spring-boot-backend
   ```

2. If using Maven, run:
   ```
   mvn clean install
   ```

   If using Gradle, run:
   ```
   ./gradlew build
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

   or if using Gradle:
   ```
   ./gradlew bootRun
   ```

## Setup Instructions

1. Clone the repository.
2. Navigate to the project directory.
3. Run `./mvnw clean install` to build the project.
4. Run `./mvnw spring-boot:run` to start the application.

### Database Configuration
- The application uses H2 Database by default. For PostgreSQL or MySQL, update the `application.properties` file with the appropriate database connection settings.

## API Endpoints

### User Management
- **Register User**
  - `POST /users/register`
  - Request Body: `{ "name": "John Doe", "email": "john@example.com", "password": "password123" }`
  
- **Login User**
  - `POST /users/login`
  - Request Body: `{ "email": "john@example.com", "password": "password123" }`

### Patient Management
- **Add Patient**
  - `POST /patients`
  - Request Body: `{ "name": "Jane Doe", "age": 30, "gender": "Female", "userId": 1 }`
  
- **Get Patient Details**
  - `GET /patients/{id}`

### Heart Rate Management
- **Record Heart Rate**
  - `POST /heartrate`
  - Request Body: `{ "patientId": 1, "timestamp": "2023-01-01T10:00:00", "heartRateValue": 75 }`
  
- **Get Heart Rate Data**
  - `GET /heartrate/{patientId}`

- `POST /users/register`: Register a user.
- `POST /users/login`: Login a user.
- `POST /patients`: Add a patient.
- `GET /patients/{id}`: Retrieve a patient’s details.
- `POST /heartrate`: Record heart rate data for a patient.
- `GET /heartrate/{patientId}`: Retrieve heart rate data for a specific patient.

## Error Handling
The application includes global exception handling using `@ControllerAdvice` to manage errors and provide meaningful responses.

## API Documentation
API documentation is available via Swagger. Access it at:
```
http://localhost:8080/swagger-ui.html
```

- Swagger UI is available at `/swagger-ui.html`.

## Assumptions
- User passwords are stored in plain text for simplicity. In a production environment, implement proper password hashing and security measures.
- The application is designed for educational purposes and may require additional features for production use.

- The project uses H2 in-memory database for simplicity.
- Basic email-password validation is implemented without authentication protocols.

## Testing
Basic unit tests are included for controllers, services, and repositories using JUnit and Mockito.

## License
This project is licensed under the MIT License.