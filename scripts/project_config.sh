#!/usr/bin/env bash

# General
export PROJECT="activity-service"
export PACKAGE="ActivityService-1.0.jar"
export STAGE="local"

# APIGW
export API_NAME=$PROJECT

# Cloudformation
export STACK="${PROJECT}-${STAGE}"

# Lambda
export HANDLER_PATH="com.gutiere.activityservice.lambda.ActivityServiceHandler::handleRequest"