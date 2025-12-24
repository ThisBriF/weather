#!/bin/bash

# 1. Generate the OpenAPI JSON using the Maven Profile
echo "🔨 Step 1: Generating OpenAPI spec from Spring..."
mvn verify -Psync-bruno

# 2. Safety Check: Ensure the file was actually created
if [ ! -f "target/openapi.json" ]; then
    echo "❌ Error: target/openapi.json not found. Check Maven logs."
    exit 1
fi

# 3. Clean Slate: Remove the old collection
# We delete it so renamed or deleted endpoints don't leave "ghost" files
if [ -d "./bruno-collection" ]; then
    echo "🧹 Step 2: Clearing old Bruno collection..."
    rm -rf ./bruno-collection
fi

# 4. Import Fresh
echo "🚀 Step 3: Importing into Bruno..."
bru import openapi --source target/openapi.json --output ./bruno-collection

echo "✅ Success! Refresh Bruno Desktop to see your updated API."