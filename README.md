# 🎬 BookMySeat - Movie Ticket Booking Platform

BookMySeat is a comprehensive, full-stack movie ticket booking application. It allows users to browse movies, find theaters in their city, view available shows, select seats via an interactive visual seat map, and manage their bookings. It also includes an Admin dashboard for managing the entire catalog of cities, theaters, movies, screens, shows, and seats.

### 🎥 Application Demo

[![BookMySeat Demo](https://bookmyseat-backend-zz2o.onrender.com)
> 👆 Click the image to watch the full demo

### ✨ Features

### 👤 User Features

* **Authentication:** User registration and login functionality.
* **Browse & Search:** View all "Now Showing" movies, filter by genre or language, and search by title.
* **City Selection:** Filter theaters and shows based on the selected city.
* **Seat Map Validation:** Interactive visual seat map displaying Available, Selected, and Booked seats.
* **Tiered Seating:** Support for Regular, Premium, and VIP seat types.
* **Booking Management:** View booking history and cancel active bookings directly from the user dashboard.

### 🛡️ Admin Features

* **City Management:** Add and manage operational cities.
* **Movie Management:** Add movies with details like genre, language, duration, release date, and poster URLs.
* **Theater & Screen Management:** Register new theaters and assign multiple screens with custom seating capacities.
* **Seat Configuration:** Bulk-add seats to screens defining specific rows, columns, and seat tiers (VIP/Premium/Regular).
* **Show Scheduling:** Map movies to specific screens, dates, and times, alongside ticket pricing.

## 🛠️ Tech Stack

**Backend:**

* Java 21
* Spring Boot 3.x / 4.x
* Spring Data JPA (Hibernate)
* Spring WebMVC (RESTful APIs)
* MySQL (Database)
* Lombok (Boilerplate reduction)
* Maven (Build tool)

**Frontend:**

* HTML5 / CSS3 (Custom responsive styling)
* Vanilla JavaScript (Fetch API for REST integration)
* Served directly from Spring Boot `src/main/resources/static`

## 🚀 Getting Started

### Prerequisites

* [Java 21](https://jdk.java.net/21/) or higher installed.
* [Maven](https://maven.apache.org/) installed (or use the provided `mvnw` wrapper).
* [MySQL](https://www.mysql.com/) server running locally.

### Local Setup Instructions

1. **Clone the Repository**
```bash
git clone https://github.com/Dikshantk29/bookmyseat-backend.git
cd bookmyseat

```


2. **Database Configuration**
* Open your MySQL terminal or GUI (like MySQL Workbench) and create a database:
```sql
CREATE DATABASE bookmyseat;

```


* Navigate to `src/main/resources/` in the project.
* Rename `application-example.properties` to `application.properties`.
* Update the database credentials inside `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookmyseat?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_mysql_password

```




3. **Build and Run the Application**
Run the following Maven command to start the Spring Boot server:
```bash
./mvnw spring-boot:run

```


*Note: If using Windows, use `mvnw.cmd spring-boot:run*`
4. **Access the Application**
Once the application is running, open your web browser and navigate to:
* **Frontend UI:** `http://localhost:8080/`
* **Admin Dashboard:** `http://localhost:8080/pages/admin.html`


*(Note: Hibernate `ddl-auto=update` will automatically create the required tables in your MySQL database on the first run).*

## 🔌 API Endpoints Overview

The backend exposes RESTful APIs grouped under `/api/*`:

* **Users:** `/api/users/register`, `/api/users/login`, `/api/users/{id}`
* **Cities:** `/api/cities`
* **Movies:** `/api/movies`, `/api/movies/search`, `/api/movies/genre/{genre}`
* **Theaters:** `/api/theaters`, `/api/theaters/city/{cityId}`
* **Screens:** `/api/screens`, `/api/screens/theater/{theaterId}`
* **Seats:** `/api/seats`, `/api/seats/screen/{screenId}`
* **Shows:** `/api/shows`, `/api/shows/movie/{movieId}`, `/api/shows/screen/{screenId}`
* **Bookings:** `/api/bookings`, `/api/bookings/user/{userId}`, `/api/bookings/show/{showId}/available-seats`

## 📂 Project Structure Highlights

```text
bookmyseat/
├── src/main/java/com/dikshant/bookmyseat/
│   ├── config/          # CORS configurations
│   ├── Controller/      # REST API Controllers 
│   ├── dto/             # Data Transfer Objects for API requests
│   ├── entity/          # JPA Entities (Database Tables)
│   ├── enums/           # Enums (SeatType, BookingStatus)
│   ├── exception/       # Global Exception Handling
│   ├── repository/      # Spring Data JPA Repositories
│   └── service/         # Business Logic Layer
└── src/main/resources/
    ├── application.properties   # App configurations
    └── static/                  # Frontend UI Assets
        ├── css/                 # Stylesheets
        ├── js/                  # API communication and UI utilities
        ├── pages/               # HTML Views (Login, Admin, Bookings)
        └── index.html           # Main Landing Page

```

## ⚠️ Important Notes

* **Security:** Currently, the application uses basic logic for login/registration. Spring Security is added to the `pom.xml` but full JWT/Session configurations would be the next step for production readiness.
* **Admin Access:** The Admin UI is openly accessible at `/pages/admin.html` for demonstration and data population purposes. In a real-world scenario, this route should be protected by Role-Based Access Control (RBAC).
