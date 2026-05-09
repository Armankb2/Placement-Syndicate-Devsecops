#!/bin/bash
# Local Deployment Script for Placement Syndicate

set -e

echo "🚢 Starting Deployment..."

# 1. Pull latest code (if in git)
# git pull origin main

# 2. Build and start services
docker-compose up -d --build

# 3. Wait for services to be ready
echo "Waiting for services to initialize..."
sleep 20

# 4. Run Smoke Tests
./scripts/smoke-test.sh

echo "🚀 Deployment Complete & Verified!"
