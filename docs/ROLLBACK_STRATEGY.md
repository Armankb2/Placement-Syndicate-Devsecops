# ↩️ Rollback Strategy

In a DevSecOps environment, things can occasionally go wrong after a deployment. This guide explains how to quickly restore the system to a stable state.

## 1. Automated Rollback (GitHub Actions)
If the **Smoke Test** stage in GitHub Actions fails, the pipeline is configured to NOT proceed to production. This is the first line of defense.

## 2. Manual Container Rollback
If a bug is discovered after a successful deployment, you can manually revert to the previous stable Docker image:

```bash
# 1. Identify the previous stable image tag (e.g., v1.0.1)
# 2. Update the docker-compose.yml or your deployment script
# 3. Re-deploy using that specific tag:
docker-compose up -d
```

## 3. Database Rollback
For database-related issues:
- **MySQL**: Restore from the latest volume backup (`/var/lib/mysql`).
- **Migration Rollback**: If using Liquibase/Flyway (future enhancement), run the `rollback` command.

## 4. Recovery Steps
1. **Notify the team**: Inform stakeholders that a rollback is in progress.
2. **Isolate the issue**: Stop the problematic container if it's causing data corruption.
3. **Revert**: Run the rollback command.
4. **Verify**: Run the smoke test script (`./scripts/smoke-test.sh`) to ensure the system is back to normal.
5. **Post-Mortem**: Analyze why the failure occurred and add a test case to prevent it in the future.
