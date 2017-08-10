#!/usr/bin/env bash
# This script starts the maven build process

mvn clean spring-boot:run "$@"
