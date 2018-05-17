#!/usr/bin/env bash

printf "Sourcing project config...\n"

source scripts/project_config.sh

printf "Tearing down project: ${PROJECT}\nDeleting stack: ${STACK}...\n"

aws cloudformation delete-stack --stack-name $STACK