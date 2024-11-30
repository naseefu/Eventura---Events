
# **Eventura - RBAC Event Management Website**

**Eventura** is a role-based access control (RBAC) event management system where users can book events, view event details, and access tickets via their profile. Event hosts can create and manage events, view bookings for their events, and interact with attendees.

## **Features**

### **General Features**
- User registration and login with role-based functionality (User and Host).
- Responsive web interface built with React.
- Secure backend with Spring Boot and MySQL.

### **User Features**
- Browse and search for events.
- Book tickets for events.
- View event details and tickets in the user profile.

### **Host Features**
- Host new events.
- View and manage all hosted events.
- Access a list of users who booked tickets for a specific event.

### **Additional Features**
- Payment integration for ticket bookings.
- OTP-based email verification for enhanced security.
- Contact form for user queries.

---

## **Tech Stack**

### **Frontend**
- **React.js**
  - Component-based architecture.

### **Backend**
- **Spring Boot**
  - REST APIs for user authentication, event management, and booking.
  - Service-oriented architecture with proper separation of concerns.
  - Configured SecurityFilterChain to manage role-based access.
  - Used JWT to secure API endpoints.
  - Service classes encapsulated business logic for event and booking management.
  - JPA Repositories for efficient database operations.

### **Database**
- **MySQL**
  - Relational database to store user, event, and booking data.

---

## **Getting Started**

### **Prerequisites**
- Java (JDK 17 or higher)
- Node.js (v16 or higher)
- MySQL Server
- Maven (for building the backend)
- NPM or Yarn (for managing frontend dependencies)

### **Setup Instructions**

#### **Backend Setup**
1. Clone the repository.
   ```bash
   git clone  https://github.com/naseefu/Eventura---Events.git
   cd backend
   ```
2. Configure the MySQL database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/eventura
   spring.datasource.username=<your-db-username>
   spring.datasource.password=<your-db-password>
   ```
3. Build and run the backend:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

#### **Frontend Setup**
1. Navigate to the `frontend` folder:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm start
   ```
4. Access the application at `http://localhost:3000`.

---

## **API Endpoints**

### **Authentication**
| Method | Endpoint                 | Description                      |
|--------|--------------------------|----------------------------------|
| POST   | `/auth/register`         | Register a new user/host.        |
| POST   | `/auth/login`            | Authenticate user/host.          |
| GET    | `/auth/get-user/{userId}`| Retrieve user details by ID.     |

### **Events**
| Method | Endpoint                         | Description                      |
|--------|----------------------------------|----------------------------------|
| GET    | `/event/getall-events/{page}/{size}` | Retrieve paginated events.   |
| POST   | `/event/host-event/{userId}`     | Host a new event.               |
| GET    | `/event/getHostedEvents/{userId}`| Get events hosted by a user.    |

### **Bookings**
| Method | Endpoint                                  | Description                      |
|--------|-------------------------------------------|----------------------------------|
| GET    | `/booking/user-bookings/{userId}`         | Get bookings for a specific user.|
| GET    | `/booking/event-ticket/{userId}/{bookId}` | Retrieve ticket details.         |
| GET    | `/booking/booked-members/{userId}/{eventId}` | Get all bookings for an event.|

### **Payments**
| Method | Endpoint                                    | Description                      |
|--------|---------------------------------------------|----------------------------------|
| POST   | `/payment/create-order/{userId}/{eventId}/{seatCount}` | Create a new payment order. |
| POST   | `/payment/finish-order/{userId}/{eventId}/{orderId}/{paymentId}` | Complete a payment order. |
| POST   | `/payment/cancel-order/{userId}/{eventId}/{orderId}` | Cancel a payment order. |

---

## **Project Highlights**
- Implements **RBAC** for different user functionalities.
- Integrates payment flow with seat management.
- Offers a clear API structure for seamless frontend-backend interaction.

---

## **Future Enhancements**
- Add a review system for events.
- Enhance search functionality with advanced filters.
- Implement real-time notifications for event updates.

---

## **Contributing**
Contributions are welcome! Feel free to fork the project and submit pull requests.

---
