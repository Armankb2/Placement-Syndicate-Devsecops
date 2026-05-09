#!/bin/bash
# Professional Rollback Strategy Script

echo "⚠️  Initiating Rollback Procedure..."

# --- Configuration ---
PREVIOUS_TAG=$1

if [ -z "$PREVIOUS_TAG" ]; then
    echo "❌ Error: Please provide the previous image tag to rollback to."
    echo "Usage: ./rollback.sh <tag>"
    exit 1
fi

echo "🔄 Rolling back all services to version: $PREVIOUS_TAG"

# Update environment variable for Docker Compose
export APP_VERSION=$PREVIOUS_TAG

# Re-deploy using the specific version
docker-compose down
docker-compose up -d

echo "✅ Rollback complete. Services are running on version $PREVIOUS_TAG."
echo "🔍 Please run smoke tests to verify stability: ./scripts/smoke-test.sh"
