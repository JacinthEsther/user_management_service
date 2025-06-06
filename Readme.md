# User Management Service

The **User Management Service** is a Spring Boot-based application designed to handle user authentication, authorization, and management. It provides functionalities for user registration, login, and account management using **JWT-based authentication**.

---

## Features

- **User Registration**: Allows users to create an account.
- **User Login**: Allows users to log in and receive a JWT token for secure authentication.
- **User Authorization**: Assigns roles and controls access to resources.
- **Secure Password Handling**: Ensures passwords are securely stored.
- **JWT Token Generation**: Provides a secure mechanism to authenticate and manage sessions.

---

## Technologies Used

- **Spring Boot**: For building the RESTful service.
- **Spring Security**: For managing authentication and authorization.
- **JWT (JSON Web Token)**: For securing endpoints and managing user sessions.
- **Lombok**: For reducing boilerplate code (getters, setters, constructors).
- **JUnit**: For unit testing.
- **H2 Database**: In-memory database for development (can be replaced with a production database like MySQL or PostgreSQL).

---

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17+**: The application requires at least Java 17.
- **Maven or Gradle**: For dependency management and running the application.
- **IDE**: e.g., IntelliJ IDEA, Eclipse, or text editor like VS Code.
- **Postman or similar tool**: For testing the API endpoints (optional but recommended).

---

## Application Configuration

The application is configured using `application.properties`. Here are the key settings for JWT:

```properties
# JWT configuration
jwt.secret=mysecretkey
jwt.expiration=86400000  # Token expiration time in milliseconds (1 day)

Ensure that these values are set before running the application.

Running the Application
1. Clone the Repository
   First, clone the repository to your local machine:
   git clone https://github.com/JacinthEsther/user_management_service.git
2. Install Dependencies
      Navigate to the project directory and install the required dependencies:
      cd user-management-service
      mvn install
3. Run the Application
      Once the dependencies are installed, you can run the application using the following command:
      mvn spring-boot:run
   Once the application starts, you should see output indicating that the service is running on http://localhost:8080.
4. Testing the Application
      You can now test the application by sending requests to the following endpoints:

POST /login: To log in and receive a JWT token (send username and password in the request body)

POST /register: To register a new user (send username, password, and email in the request body)

GET /user: To fetch the user details (requires JWT token in the Authorization header)

Example login request:

POST /login
{
"username": "testuser",
"password": "password123"
}

5. Run Unit Tests
   To run the tests for the application, use the following Maven command:

API Endpoints
/login (POST)
Description: Logs in the user and generates a JWT token.

Request Body:
{
"username": "user1",
"password": "password"
}

Response: JWT token

/register (POST)
Description: Registers a new user.

Request Body:
{
"username": "newuser",
"password": "newpassword123",
"email": "newuser@example.com"
}

Response: Success message

/user (GET)
Description: Fetches user details (requires authentication).

Headers: Authorization: Bearer <jwt_token>

Response:
{
"username": "user1",
"email": "user1@example.com",
"role": "USER"
}

Troubleshooting
JWT Errors: If you encounter any issues related to JWT token validation, ensure that your token is being passed correctly in the Authorization header as Bearer <token>.

Database Configuration: Ensure that your database is properly configured if you're using a persistent database rather than the default in-memory H2 database.

Directory Structure
Here’s the directory structure for the project:

src/
│
├── main/
│   ├── java/
│   │   ├── com/
│   │   │   └── usermanagementservice/
│   │   │       ├── controllers/           # Controllers for user authentication and management
│   │   │       ├── dtos/                  # DTOs (Data Transfer Objects)
│   │   │       ├── config/                # Configuration classes like JwtUtil
│   │   │       └── entities/              # Entities and domain models like User
│   │   │       └── repositories/          # Repository interfaces and implementations
│   │   │       └── services/              # Service interfaces and implementations
│   └── resources/
│       └── application.properties         # Configuration for the application (e.g., JWT settings)
└── test/                                  # Unit tests for the application
