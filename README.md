# 🛡️ Placement Syndicate: DevSecOps Edition

[![DevSecOps Pipeline](https://github.com/Armankb2/Placement-Syndicate-Devsecops/actions/workflows/devsecops-pipeline.yml/badge.svg)](https://github.com/Armankb2/Placement-Syndicate-Devsecops/actions)
[![Sonar Quality Gate](https://img.shields.io/badge/SonarQube-Quality%20Gate-brightgreen)](http://localhost:9000)

**Placement Syndicate** is a production-grade, secure, and automated microservices platform built with a "Security-First" approach. This project demonstrates a complete DevSecOps lifecycle implementation.

---

## 🚀 DevSecOps Architecture
Our CI/CD pipeline consists of **15 Sequential Stages**:
1.  **Source Checkout:** Fetching latest code.
2.  **Linting:** ESLint for frontend code quality.
3.  **SAST:** SonarQube analysis for deep code inspection.
4.  **Unit Testing:** Individual component verification.
5.  **Integration Testing:** Cross-service communication testing.
6.  **Code Coverage:** JaCoCo reports for test sufficiency.
7.  **Security FS Scan:** Trivy scan of the source code.
8.  **Dependency Check:** OWASP scan for vulnerable libraries.
9.  **Docker Build:** Optimized multi-stage container creation.
10. **Container Image Scan:** Trivy scan of final Docker images.
11. **Artifact Push:** Pushing secure images to Registry.
12. **Staging Deploy:** Deployment to a pre-production environment.
13. **Smoke Testing:** Health check and basic functionality verification.
14. **Production Deploy:** Rolling out to end-users.
15. **Monitoring:** Prometheus & Grafana for real-time observability.

---

## 🛠️ Technology Stack
*   **Backend:** Java 21, Spring Boot, Microservices.
*   **Frontend:** React.js, Vanilla CSS.
*   **DevOps:** GitHub Actions, Docker, Docker Compose.
*   **Security:** SonarQube, Trivy, OWASP Dependency Check.
*   **Monitoring:** Prometheus, Grafana, Actuator.

---

## ⚙️ Local Setup Instructions

### 1. Prerequisites
*   Docker & Docker Compose
*   Java 21 (Temurin)
*   Node.js 20+

### 2. Start the Infrastructure
```bash
# Start Databases, SonarQube, and Monitoring
docker-compose up -d mysql mongodb keycloak sonarqube prometheus grafana
```

### 3. Run Security Scans Locally
```bash
# Install Trivy then run:
trivy fs .
```

### 4. Run the Full Pipeline (Simulation)
```bash
# Execute local test suite
mvn clean verify
```

---

## 📊 Monitoring Dashboards
*   **SonarQube:** `http://localhost:9000`
*   **Prometheus:** `http://localhost:9091`
*   **Grafana:** `http://localhost:3001` (User: admin, Pass: admin)
*   **Eureka Discovery:** `http://localhost:8100`

---

## 🛡️ Security Implementation
*   **Static Analysis:** SonarQube quality gates.
*   **Vulnerability Scanning:** Trivy scans fail the build on 'CRITICAL' severity.
*   **Dependency Management:** OWASP checks run on every build.
*   **Runtime Security:** Containers run as non-root users.

---

## 🔄 Rollback Strategy
If a deployment fails, run the provided rollback script:
```bash
chmod +x scripts/rollback.sh
./scripts/rollback.sh <previous_tag>
```

---

## 📂 Project Structure
```text
.
├── .github/workflows/   # CI/CD Pipeline Definitions
├── docs/                # Reports, PPT Content, Viva Prep
├── scripts/             # Smoke tests, Rollback, Deploy scripts
├── security/            # Security scan configurations
├── monitoring/          # Prometheus/Grafana configs
├── user-service/        # Spring Boot Service
├── frontend/            # React Application
└── docker-compose.yml   # Local Orchestration
```

---

## 👨‍🏫 Viva & Submission
All documentation required for college submission is located in the `docs/` folder:
*   [Project Report](docs/REPORT.md)
*   [Presentation Content](docs/PPT_CONTENT.md)
*   [Viva Q&A Prep](docs/VIVA_PREP.md)
