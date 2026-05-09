#!/bin/bash
# Professional Smoke Test Script for DevSecOps Pipeline

echo "🚀 Starting Smoke Tests..."

# --- Configuration ---
GATEWAY_URL=${1:-"http://localhost:8200"}
USER_SERVICE_URL=${2:-"http://localhost:8081"}
FRONTEND_URL=${3:-"http://localhost:3000"}

# --- Helper Function ---
check_service() {
    local NAME=$1
    local URL=$2
    echo -n "Checking $NAME ($URL)... "
    STATUS=$(curl -s -o /dev/null -w "%{http_code}" $URL)
    if [ "$STATUS" == "200" ]; then
        echo "✅ OK"
    else
        echo "❌ FAILED (Status: $STATUS)"
        exit 1
    fi
}

# --- Execution ---
echo "--------------------------------------"
check_service "Frontend" "$FRONTEND_URL"
check_service "API Gateway (Health)" "$GATEWAY_URL/actuator/health"
check_service "User Service (Health)" "$USER_SERVICE_URL/actuator/health"
echo "--------------------------------------"
echo "🎉 All smoke tests passed successfully!"
exit 0
