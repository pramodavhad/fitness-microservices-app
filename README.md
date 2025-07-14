
# 🏋️‍♂️ Fitness Microservices App

A **Fitness Management Application** built using **Java**, **Spring Boot**, **Spring Cloud**, **MySQL**, **MongoDB**, **RabbitMQ**, **Keycloak**, and **React.js**. This project follows a **microservices architecture** to ensure scalability, maintainability, and modular development.

---

## 🚀 Tech Stack

### Backend (Java Microservices)
- **Spring Boot**
- **Spring Cloud Eureka (Service Registry)**
- **Spring Cloud API Gateway (with Keycloak Auth)**
- **Spring Data JPA & Hibernate**
- **Spring WebClient**
- **RabbitMQ (Message Broker)**
- **MySQL (Relational DB)**
- **MongoDB (NoSQL DB)**

### Frontend
- **React.js**
- **Axios**
- **Material UI**

---

## 📁 Project Structure

```
fitness-microservices-app/
│
├── activity-service/              # Tracks fitness activities (MongoDB)
├── ai-service/                    # Provides AI-generated feedback on activities
├── api-gateway/                   # Centralized API Gateway (Keycloak Auth)
├── config-server/                # Spring Cloud Config Server
├── discovery-server/             # Eureka Service Discovery
├── user-service/                 # Manages user data (MySQL)
├── frontend/                     # React-based user interface
└── README.md                     # Project documentation
```

---

## 🧩 Microservices Overview

| Service            | Description                                                    | Database |
|--------------------|----------------------------------------------------------------|----------|
| **User Service**   | Handles user registration, profile management                  | MySQL    |
| **Activity Service** | Logs and tracks user fitness activities                      | MongoDB  |
| **AI Service**     | Generates intelligent feedback based on registered activities  | —        |
| **API Gateway**    | Routes requests and integrates with Keycloak for authentication | —        |
| **Discovery Server** | Eureka registry for service discovery                         | —        |
| **Config Server**  | Centralized configuration management                           | —        |

---

## 🔄 Communication Flow

- Services communicate via **REST APIs** and **RabbitMQ**.
- Centralized routing and authentication via **API Gateway + Keycloak**.
- All services registered to **Eureka Discovery Server**.

---

## 🔐 Authentication

- Uses **Keycloak** for centralized authentication and role-based access control.
- API Gateway handles Keycloak token validation and secure routing.

---

## 🛠️ Getting Started

### 1. Prerequisites

Ensure the following tools are installed:

- Java 17+
- Maven
- Node.js and npm
- MySQL
- MongoDB
- RabbitMQ
- Keycloak

### 2. Clone the Repository

```bash
git clone https://github.com/pramodavhad/fitness-microservices-app.git
cd fitness-microservices-app
```

### 3. Run Services

#### Start Infrastructure

- Start MySQL, MongoDB, RabbitMQ, and Keycloak locally.
- Update DB credentials and Keycloak config in `application.yml` or `bootstrap.yml`.

#### Start Discovery Server

```bash
cd discovery-server
mvn spring-boot:run
```

#### Start Config Server

```bash
cd config-server
mvn spring-boot:run
```

#### Start Other Microservices

Use the following command in each service folder:

```bash
mvn spring-boot:run
```

> Make sure all ports and configurations are correctly set.

---

### 4. Run Frontend

```bash
cd frontend
npm install
npm start
```

---

## 📬 Features

- ✅ User Registration & Login via Keycloak
- ✅ Activity Tracking
- ✅ AI Feedback Suggestions
- ✅ Secure REST APIs with OAuth2 (Keycloak)
- ✅ Microservice Discovery (Eureka)
- ✅ API Gateway Routing
- ✅ React-based Responsive UI

---

## 📌 To-Do

- [ ] Dockerize all services
- [ ] Add Swagger API Documentation
- [ ] Implement Unit & Integration Tests
- [ ] Add Email/OTP Verification

---

## 🤝 Contributing

Contributions are welcome!  
Feel free to fork the repo, create a branch, and open a pull request.

---

## 📄 License

This is a personal practice project created for learning purposes.  
Feel free to explore and learn from the code.  
No official license is associated with this repository.

---

## 🙋‍♂️ Author

**Pramod Avhad**

- GitHub: [@pramodavhad](https://github.com/pramodavhad)
- LinkedIn: [Pramod Avhad](https://linkedin.com/in/pramod-avhad-bab8b6291)

---

## ⭐️ Support

If you find this project helpful, please consider giving it a ⭐ on GitHub!
