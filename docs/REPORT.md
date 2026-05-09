# 📄 Project Report: Placement Syndicate DevSecOps

**Project Name:** Placement Syndicate  
**Domain:** DevSecOps / Cloud Engineering  
**Tech Stack:** Java Spring Boot, React, Docker, GitHub Actions, SonarQube, Prometheus, Grafana.

---

## 1. Introduction to CI/CD & DevSecOps
Continuous Integration (CI) and Continuous Deployment (CD) are modern software development practices that enable teams to deliver code changes more frequently and reliably. DevSecOps extends this by integrating security as a core component throughout the software development lifecycle, rather than an afterthought.

In this project, we have transformed the "Placement Syndicate" platform into a production-ready system by implementing a 15-stage DevSecOps pipeline.

## 2. Selected Tools and Their Roles
| Tool | Category | Role |
| :--- | :--- | :--- |
| **GitHub Actions** | CI/CD | Orchestrates the entire pipeline from code push to deployment. |
| **SonarQube** | SAST | Analyzes source code for bugs, vulnerabilities, and code smells. |
| **Trivy** | Security Scan | Scans the file system and Docker images for CVEs. |
| **OWASP Dep-Check**| SCA | Checks for vulnerable third-party libraries in Maven/NPM. |
| **Docker** | Containerization | Packages services into portable containers. |
| **Prometheus** | Monitoring | Collects and stores metrics from microservices. |
| **Grafana** | Visualization | Displays application health and metrics in dashboards. |

## 3. Architecture of the CI/CD Pipeline
The pipeline is triggered automatically on every push to the `main` branch.
1.  **Phase 1 (Code Quality):** Linting and SonarQube analysis.
2.  **Phase 2 (Testing):** Unit and Integration tests with Code Coverage.
3.  **Phase 3 (Security):** SCA and Vulnerability scans (Trivy).
4.  **Phase 4 (Artifact):** Docker build and Image scanning.
5.  **Phase 5 (Deployment):** Staging deployment and Smoke testing.
6.  **Phase 6 (Production):** Production deployment and Monitoring.

## 4. Installation & Configuration Steps
### Prerequisites:
*   Docker & Docker Compose installed.
*   Java 21 & Node.js installed.
*   GitHub Account with Secrets configured.

### Local Setup:
```bash
# 1. Clone the repository
git clone <repo-url>
cd Placement-Syndicate-Devsecops

# 2. Start Infrastructure (SonarQube, MySQL, etc.)
docker-compose up -d

# 3. Run Security Scan locally
trivy fs .
```

## 5. Implementation Details
*   **Dockerization:** Used multi-stage builds to reduce image size by ~60%.
*   **Quality Gates:** Configured SonarQube to fail the build if "New Bugs > 0" or "Security Rating < A".
*   **Monitoring:** Integrated Spring Boot Actuator with Prometheus to track JVM metrics.

## 6. Screenshots Placeholder
> [!IMPORTANT]
> Capture and insert the following screenshots here:
> 1. **GitHub Actions Workflow:** Successful 15-stage run. [Screenshot_1]
> 2. **SonarQube Dashboard:** Project quality report. [Screenshot_2]
> 3. **Trivy Scan Results:** Table showing vulnerabilities found. [Screenshot_3]
> 4. **Grafana Dashboard:** Visualized metrics. [Screenshot_4]
> 5. **Application UI:** Running frontend. [Screenshot_5]

## 7. Challenges Faced and Solutions
*   **Challenge:** Dependency conflicts in microservices.
*   **Solution:** Standardized versions in the parent `pom.xml`.
*   **Challenge:** Pipeline slowness.
*   **Solution:** Implemented Docker layer caching and parallel jobs.

## 8. Use Case Demonstration
To demonstrate the pipeline:
1.  Introduce a "Bug" (e.g., hardcoded password).
2.  Push code to GitHub.
3.  Observe SonarQube/Trivy failing the build.
4.  Fix the bug and push again to see a successful deployment.

## 9. Conclusion
The implementation of DevSecOps in the Placement Syndicate project ensures that security is not a bottleneck but a built-in feature. This project provides a robust template for any production-grade microservices deployment.
