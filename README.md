# Clinic ERP: Full-Stack Clinic Management System

A production-ready, shippable, and portfolio-grade **Small Clinic Management Enterprise Resource Planning (ERP)** system built with Java Spring Boot and React. This application demonstrates modern enterprise architecture patterns, stateless JWT authentication, role-based access control (RBAC), and robust automated testing pipelines, all running on a fully containerized architecture.

---

## 🚀 Key Features

- **Role-Based Access Control (RBAC):** Distinct dashboards and access scopes for **Admins**, **Doctors**, and **Receptionists** using Spring Security and JWT.
- **Patient Management:** Full CRUD capabilities for tracking patient demographics, blood groups, and medical histories.
- **Appointment Scheduling:** Advanced scheduling mechanics with automated conflict detection to prevent double-booking.
- **Automated Invoicing & Billing:** Dynamic invoice generation directly from completed appointments, tracking itemized costs and payment statuses (`PAID`/`UNPAID`).
- **Interactive API Documentation:** Real-time interactive REST API exploration via integrated Swagger UI (`springdoc-openapi`).
- **Containerized Dev Parity:** Multi-stage Docker configurations allowing the entire multi-service stack to boot with a single command (`docker-compose up`).

---

## 🛠️ Tech Stack & Architecture

### Backend
* **Core Framework:** Java 17 + Spring Boot 3
* **Persistence & Data:** Spring Data JPA + Hibernate + PostgreSQL
* **Database Migrations:** Flyway (Versioned SQL migrations)
* **Security & Auth:** Spring Security + Stateless JWT (`jjwt`)
* **API Documentation:** `springdoc-openapi` (Swagger UI)
* **Testing Stack:** JUnit 5 + Mockito + Testcontainers (PostgreSQL)

### Frontend
* **Core Framework:** React 18 (Vite fast dev loop)
* **Styling & UI:** Tailwind CSS
* **Routing & State:** React Router DOM + React Context API
* **API Client:** Axios (configured with automated JWT interceptors)
* **Testing Stack:** Vitest + React Testing Library

### Infrastructure & DevOps
* **Containerization:** Docker + Docker Compose
* **CI/CD Pipelines:** GitHub Actions (Independent frontend & backend workflows)
* **Hosting Platforms:** Render (Backend Docker Web Service), Vercel/Netlify (Frontend Static), Neon/Supabase (Serverless Postgres)[cite: 1]

---

## 📂 Repository Structure

The project utilizes a **monorepo** layout to manage both the decoupled backend API and the single-page frontend application efficiently[cite: 1]:

```text
clinic-erp/
├── .github/workflows/       # Automated CI/CD pipelines
│   ├── backend-ci.yml       # Maven test, build, and checkstyle verification
│   └── frontend-ci.yml      # Vitest execution and production compilation
├── backend/                 # Spring Boot application
│   ├── src/main/java/       # Domain modules (Controller -> Service -> Repo -> Entity)
│   ├── src/main/resources/  # Application configurations & Flyway migrations
│   ├── src/test/            # Unit, Mockito, and Testcontainers integration tests
│   └── Dockerfile           # Multi-stage JRE runtime container image build
├── frontend/                # React application
│   ├── src/                 # Component, hooks, pages, contexts, and API wrappers
│   ├── Dockerfile           # Production-ready Nginx container image build
│   ├── package.json         # Front-end dependencies and test scripts
│   └── vite.config.js       # Vite bundler configuration
├── docs/                    # Technical architecture design and ER diagrams
├── docker-compose.yml       # Full stack local orchestration configuration
└── README.md                # Project documentation
```

## 🗄️ Database Schema & Domain Model

The underlying domain model is normalized and built to scale. The core entities comprise:

* **User**: Manages authorization credentials (`username`, `passwordHash`) and maps them to granular system roles (`ADMIN`, `DOCTOR`, `RECEPTIONIST`).

* **Doctor**: Extension of the user profile, tracking core specializations and scheduling availability.

* **Patient**: Complete medical-demographic registry tracking names, DOB, blood types, and contact vectors.

* **Appointment**: Connects doctors and patients at specific timestamps while maintaining workflow state transitions (`SCHEDULED`, `COMPLETED`, `CANCELLED`).

* **Invoice & InvoiceItem**: Captures financial ledger data tied to appointments with tracking for payment collection metrics.

* **Entity Relationship Diagram**: The database design diagram can be inspected visually under `docs/er-diagram.png`.

## ⚙️ Local Development Setup

### Prerequisites
Ensure the following tools are installed locally:

* Java 17 JDK or higher
* Node.js & npm
* Docker Desktop

### Option A: Complete Containerized Boot (Recommended)
Spin up the database, backend REST API, and frontend client instantly using the orchestrated Compose architecture:

```
# Clone the repository
git clone [https://github.com/naol-onGit/clinic-erp.git](https://github.com/naol-onGit/clinic-erp.git)
cd clinic-erp

# Run the entire ecosystem
docker-compose up --build
```
* **Frontend UI**: `http://localhost:5173`
* **Backend API Gateway**: `http://localhost:8080`
* **Swagger Documentation**: `http://localhost:8080/swagger-ui.html`

### Option B: Step-by-Step Manual Boot
**1. Database Setup**
Launch a local PostgreSQL database or create a serverless cloud tier using Neon. Update `backend/src/main/resources/application-dev.yml` with your connection credentials.

**2. Start the Spring Boot Backend**
```
cd backend
# Build and run the project using the Maven Wrapper
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
**3. Start the React Frontend**
```
cd frontend
# Install client dependencies
npm install
# Start Vite development server
npm run dev
```

## 🧪 Testing Matrix
The implementation is verified across layers to ensure stability and reliable contract-driven communication:

### Backend Testing Suite
* **Service Layer**: Isolated business logic validation (e.g., appointment overlap algorithms, automated billing arithmetic) verified using JUnit 5 and Mockito.

* **Integration Layer**: Controller endpoints and JPA query performance tested under real-world runtime environments using Testcontainers spawning transient PostgreSQL Docker containers.

```
cd backend
./mvnw test
```
### Frontend Testing Suite
Form validation mechanics, responsive navigation flows, and components tested via Vitest and React Testing Library.

```
cd frontend
npm run test
```

## 🛡️ Architecture & Security Disclaimer
This application is designed specifically as an architectural learning model and portfolio showcase piece demonstrating corporate ERP software design principles.

* **HIPAA Compliance**: This software is NOT compliant with the Health Insurance Portability and Accountability Act (HIPAA) or similar international frameworks governing personal health information (PHI).

* **Data Privacy**: Do not enter real patient details, authentic medical records, or genuine personal identifiable information (PII) into any live or local instance of this platform. All database seeding and interactions must use mock data.

## 📄 License
Distributed under the MIT License. See `LICENSE` for more information.
