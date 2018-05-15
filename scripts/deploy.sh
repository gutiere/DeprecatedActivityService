#!/usr/bin/env bash

printf "Sourcing project config...\n"

source scripts/project_config.sh

# Message Font
CYAN="\e[36m"
BOLD=$"\e[1m"
DEFAULT="\e[39m"

# General
REGION=$(aws configure get default.region)

# S3
BUCKET="${PROJECT}-${REGION}"
HANDLER_KEY="${PROJECT}/target/${PACKAGE}-$(date +%s)"
HANDLER_BODY="target/$PACKAGE"

# TODO find a solution that doesn't require creating a new package key name every build...
if aws s3api head-bucket --bucket $BUCKET 2>/dev/null; then
    printf "\n${BOLD}${CYAN}Clearing resource bucket ...${DEFAULT}\n"
    aws s3 rm s3://$BUCKET --recursive
else
    printf $"\n$BOLD$CYANCreating S3 bucket: $BUCKET ...$DEFAULT\n"
    aws s3api create-bucket --bucket $BUCKET \
    --region $REGION \
    --create-bucket-configuration LocationConstraint=$REGION
fi

mvn install

printf "\n${BOLD}${CYAN}Pushing target to bucket ...${DEFAULT}\n"
aws s3api put-object \
--bucket $BUCKET \
--key $HANDLER_KEY \
--body $HANDLER_BODY

sam package \
--template-file ./configuration/cloudformation/template.yml \
--s3-bucket $BUCKET

sam deploy \
--template-file ./configuration/cloudformation/template.yml \
--stack-name $STACK \
--parameter-overrides \
    CodeKey=$HANDLER_KEY \
    CodeBucket=$BUCKET \
    HandlerPath=$HANDLER_PATH \
--capabilities CAPABILITY_IAM