
# 🏋️‍♂️ Fitness Microservices App

A complete **Fitness Management Application** built using **Java**, **Spring Boot**, **Spring Cloud**, **MySQL**, **MongoDB**, **RabbitMQ**, and **React.js**. This project follows a **microservices architecture** to ensure scalability, maintainability, and modular development.

---

## 🚀 Tech Stack

### Backend (Java Microservices)
- **Spring Boot**
- **Spring Cloud Eureka (Service Registry)**
- **Spring Cloud API Gateway**
- **Spring Data JPA & Hibernate**
- **Spring WebClient**
- **Spring Security (JWT Based Auth)**
- **RabbitMQ (Message Broker)**
- **MySQL (Relational DB)**
- **MongoDB (NoSQL DB)**

### Frontend
- **React.js**
- **Axios**
- **Bootstrap / Tailwind CSS**

---

## 📁 Project Structure

```
fitness-microservices-app/
│
├── activity-service/              # Tracks fitness activities (MongoDB)
├── api-gateway/                   # Centralized API Gateway
├── authentication-service/       # JWT-based user auth and login
├── config-server/                # Spring Cloud Config Server
├── discovery-server/             # Eureka Service Discovery
├── notification-service/         # Handles email/notification via RabbitMQ
├── user-service/                 # Manages user data (MySQL)
├── frontend/                     # React-based user interface
└── README.md                     # Project documentation
```

---

## 🧩 Microservices Overview

| Service                  | Description                                                 | Database |
|--------------------------|-------------------------------------------------------------|----------|
| **User Service**         | Handles user registration, profile management              | MySQL    |
| **Authentication Service** | JWT-based authentication and role-based access           | MySQL    |
| **Activity Service**     | Logs and tracks user fitness activities                    | MongoDB  |
| **Notification Service** | Listens to RabbitMQ and sends email/SMS notifications      | —        |
| **API Gateway**          | Routes requests to respective microservices                | —        |
| **Discovery Server**     | Eureka registry for service discovery                      | —        |
| **Config Server**        | Centralized configuration management                       | —        |

---

## 🔄 Communication Flow

- Services communicate via **REST APIs** and **RabbitMQ** for asynchronous messaging.
- Centralized routing and load balancing through **API Gateway**.
- All services registered to **Eureka Discovery Server**.

---

## 🔐 Authentication

- JWT-based authentication with secure token validation.
- Role-based access control.
- Secure communication between services.

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

### 2. Clone the Repository

```bash
git clone https://github.com/pramodavhad/fitness-microservices-app.git
cd fitness-microservices-app
```

### 3. Run Services

#### Start Infrastructure

- Start MySQL, MongoDB, and RabbitMQ locally.
- Update DB credentials in each service's `application.yml` or `bootstrap.yml`.

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

> Make sure all ports and URLs (RabbitMQ, Eureka) are correctly configured.

---

### 4. Run Frontend

```bash
cd frontend
npm install
npm start
```

---

## 📸 Screenshots

<!-- Add screenshots of the frontend UI here (optional) -->
<!-- ![Home Page](screenshots/home.png) -->

---

## 📬 Features

- ✅ User Registration & Login
- ✅ Activity Tracking
- ✅ Email/SMS Notifications via RabbitMQ
- ✅ Secure REST APIs with JWT
- ✅ Microservice Discovery (Eureka)
- ✅ API Gateway Routing
- ✅ React-based Responsive UI

---

## 📌 To-Do

- [ ] Dockerize all services
- [ ] Add Swagger API Documentation
- [ ] Implement Unit & Integration Tests
- [ ] Add OTP/Email Verification

---

## 🤝 Contributing

Contributions are welcome!  
Feel free to fork the repo, create a branch, and open a pull request.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 🙋‍♂️ Author

**Pramod Avhad**

- GitHub: [@pramodavhad](https://github.com/pramodavhad)
- LinkedIn: [Pramod Avhad](https://linkedin.com/in/pramodavhad)

---

## ⭐️ Support

If you find this project helpful, please consider giving it a ⭐ on GitHub!
