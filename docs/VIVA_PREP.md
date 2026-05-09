# 🎓 DevSecOps Viva Preparation Guide

This guide contains 25 essential viva questions and answers for the **Placement Syndicate** project.

## 🚀 DevOps & CI/CD
**Q1: What is DevOps and why is it used?**
*   **A:** DevOps is a culture/practice that bridges the gap between Development and Operations. It uses automation to ensure faster, reliable, and frequent software releases.

**Q2: What is the difference between CI and CD?**
*   **A:** CI (Continuous Integration) is the practice of merging code into a shared repository frequently. CD (Continuous Deployment) is the practice of automatically deploying every change that passes the CI pipeline to production.

**Q3: Explain the 15 stages implemented in your pipeline.**
*   **A:** Code Push, Checkout, Linting, SonarQube Scan, Unit Test, Integration Test, Coverage, Trivy FS Scan, OWASP Dependency Check, Docker Build, Trivy Image Scan, Push to GHCR/DockerHub, Staging Deploy, Smoke Test, Production Deploy.

**Q4: What is a Pipeline as Code?**
*   **A:** It is the practice of defining the CI/CD pipeline configuration in a file (like `.github/workflows/devsecops-pipeline.yml`) that lives with the source code.

## 🐳 Docker & Containerization
**Q5: Why did you use Docker for this project?**
*   **A:** Docker ensures "it works on my machine" consistency. It packages the app and dependencies into a container that runs the same in dev, staging, and production.

**Q6: What is a multi-stage Docker build?**
*   **A:** It is a technique to keep Docker images small. We use one stage for building (with all tools) and another for running (only the JAR and JRE).

**Q7: How do you secure a Docker image?**
*   **A:** Use official base images, run as a non-root user, use multi-stage builds, and scan images using tools like Trivy.

**Q8: Difference between an Image and a Container?**
*   **A:** An Image is a read-only template (like a class). A Container is a running instance of that image (like an object).

## 🛡️ Security (DevSecOps)
**Q9: What makes it "DevSecOps" and not just "DevOps"?**
*   **A:** DevSecOps integrates security checks (SAST, SCA, Image Scanning) directly into the CI/CD pipeline from the start, rather than checking at the end.

**Q10: What is SAST and which tool did you use?**
*   **A:** Static Application Security Testing. We used **SonarQube** and **ESLint** to find bugs and security vulnerabilities in the source code without running it.

**Q11: What is SCA and which tool did you use?**
*   **A:** Software Composition Analysis. We used **OWASP Dependency Check** to find vulnerabilities in third-party libraries (Maven/NPM dependencies).

**Q12: What does Trivy do?**
*   **A:** Trivy is a vulnerability scanner. We use it for scanning both the local File System (FS) and the final Docker Image for known CVEs.

## 📊 Monitoring & Rollback
**Q13: Why is monitoring important in DevSecOps?**
*   **A:** It provides visibility into application health and performance in production, allowing us to detect and fix issues before they affect users.

**Q14: Explain Prometheus and Grafana.**
*   **A:** Prometheus is a time-series database that "scrapes" metrics from the app. Grafana is a visualization tool that creates beautiful dashboards from those metrics.

**Q15: What is a Rollback strategy?**
*   **A:** A plan to quickly revert to a previous working version of the application if a new deployment fails or causes bugs.

**Q16: How do you implement a rollback in Docker?**
*   **A:** By re-tagging the previous image as 'latest' or by deploying a specific version tag (e.g., v1.0.1) that was known to be stable.

## 🛠️ Tools & Implementation
**Q17: Why did you choose GitHub Actions?**
*   **A:** It's integrated into GitHub, free for public repos, easy to configure using YAML, and has a massive marketplace of pre-built actions.

**Q18: What are GitHub Secrets?**
*   **A:** Encrypted environment variables (like DOCKER_PASSWORD, SONAR_TOKEN) that are stored securely in GitHub and used in the pipeline without exposing them in code.

**Q19: What is a Smoke Test?**
*   **A:** A quick set of tests run after deployment to ensure the basic functionality (like health endpoints) is working before doing full testing.

**Q20: What is Code Coverage?**
*   **A:** A metric that tells us what percentage of our source code is actually executed during tests. We used **JaCoCo** for this.

**Q21: How do you handle environment variables in Docker?**
*   **A:** Using `.env` files or passing them via the `environment` section in `docker-compose.yml`.

**Q22: What is the API Gateway's role?**
*   **A:** It acts as a single entry point for all frontend requests, routing them to the correct microservice (e.g., user-service).

**Q23: What is Service Discovery (Eureka)?**
*   **A:** It allows microservices to find each other dynamically by name rather than hardcoding IP addresses.

**Q24: What is the "Shift Left" security concept?**
*   **A:** It means moving security testing earlier in the development lifecycle (to the "left" of the timeline).

**Q25: What was the biggest challenge in this project?**
*   **A:** Integrating all tools sequentially and ensuring secrets were handled securely while maintaining high performance on macOS M1/M2.
