# Clinic ERP

A production-ready, web-based Clinic Management ERP system built with Java Spring Boot and React.

🔗 **Live Demo:** [https://clinic-erp-three.vercel.app/](https://clinic-erp-three.vercel.app/)
📖 **API Docs:** [clinic-erp-backend.onrender.com/swagger-ui/index.html](https://clinic-erp-backend.onrender.com/swagger-ui/index.html)

---

## Features

- 🔐 JWT authentication with role-based access control (Admin, Doctor, Receptionist)
- 👥 Patient management — register, search, paginate, update, delete
- 🩺 Doctor management — profiles linked to user accounts
- 📅 Appointment scheduling — status tracking (Scheduled, Completed, Cancelled)
- 🧾 Billing & invoicing — mark paid/unpaid, revenue summary
- 📖 Auto-generated Swagger/OpenAPI documentation
- ✅ 31 unit tests with Mockito
- 🚀 CI/CD with GitHub Actions
- 🐳 Docker-ready for local development

---

## Tech Stack

### Backend
- Java 17 + Spring Boot 4.x
- Spring Security + JWT (jjwt)
- Spring Data JPA + Flyway migrations
- PostgreSQL
- Swagger/OpenAPI (springdoc)
- JUnit 5 + Mockito
- Docker + Docker Compose
- Maven

### Frontend
- React 19 (Vite)
- Tailwind CSS
- React Router
- Axios
- Lucide React icons

---

## Architecture
```
clinic-erp/
├── backend/ # Spring Boot REST API
│ ├── src/main/java/com/clinicerp/backend/
│ │ ├── config/ # Security, OpenAPI config
│ │ ├── controller/ # REST controllers
│ │ ├── dto/ # Request/Response DTOs
│ │ ├── entity/ # JPA entities
│ │ ├── exception/ # Global exception handler
│ │ ├── mapper/ # Entity <-> DTO mappers
│ │ ├── repository/ # Spring Data repositories
│ │ ├── security/ # JWT filter, UserDetailsService
│ │ └── service/ # Business logic
│ └── src/main/resources/
│ └── db/migration/ # Flyway SQL migrations
└── frontend/ # React SPA
└── src/
├── api/ # Axios instance + interceptors
├── components/ # Layout (Sidebar, Topbar)
├── context/ # Auth context + provider
├── pages/ # Login, Dashboard, Patients, Doctors, Appointments, Billing
└── routes/ # Protected route wrapper
```
---

## Local Development

### Prerequisites
- Java 17
- Node.js 20+
- Docker Desktop

### Backend

```bash
cd backend
docker-compose up -d        # start Postgres
./mvnw spring-boot:run      # start Spring Boot on port 8080
```

### Frontend

```bash
cd frontend
npm install
npm run dev                 # start React on port 5173
```

### API Documentation

Once the backend is running, visit:

http://localhost:8080/swagger-ui/index.html

---

## Environment Variables

### Backend (Render)
| Variable | Description |
|---|---|
| `SPRING_PROFILES_ACTIVE` | `prod` |
| `DATABASE_URL` | PostgreSQL JDBC URL |
| `DATABASE_USERNAME` | DB username |
| `DATABASE_PASSWORD` | DB password |
| `JWT_SECRET` | Secret key for JWT signing |

### Frontend (Vercel)
| Variable | Description |
|---|---|
| `VITE_API_URL` | Backend API base URL (e.g. `https://your-app.onrender.com/api`) |

---

## API Endpoints

| Method | Endpoint | Access |
|---|---|---|
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public |
| GET/POST | `/api/patients` | Admin, Receptionist |
| GET/PUT/DELETE | `/api/patients/{id}` | Admin, Receptionist |
| GET/POST | `/api/doctors` | Admin |
| GET/PUT/DELETE | `/api/doctors/{id}` | Admin |
| GET/POST | `/api/appointments` | Admin, Receptionist, Doctor |
| GET/PUT/DELETE | `/api/appointments/{id}` | Admin, Receptionist |
| GET/POST | `/api/invoices` | Admin, Receptionist |
| GET/PUT/DELETE | `/api/invoices/{id}` | Admin, Receptionist |

---

## Testing

```bash
cd backend
./mvnw test
```

31 unit tests covering all service layers (Patient, Doctor, Appointment, Invoice, InvoiceItem, Auth).

---

## CI/CD

GitHub Actions runs on every push to `main`:
- **Backend CI** — runs all 31 tests against a real Postgres container
- **Frontend CI** — lint check + production build

---

## Deployment

- **Backend:** Render (Docker web service)
- **Frontend:** Vercel
- **Database:** Neon (serverless Postgres)

---

## Developer

Built by **AbySynk - Naol Kedir** ([github.com/naol-onGit](https://github.com/naol-onGit)) as a portfolio project demonstrating intensive use of free tier AI's like Claude and ChatGPT for production-ready full-stack development with Java Spring Boot and React.