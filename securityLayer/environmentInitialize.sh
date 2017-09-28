#!/bin/bash

#  JDBC Properties 
export JDBC_URL="jdbc:mysql://nonproddb.cynprxzxw8wr.us-west-2.rds.amazonaws.com:3306/revaturev2_s3?autoReconnect=true&zeroDateTimeBehavior=convertToNull"
export JDBC_USERNAME="rev_devind"
export JDBC_PASSWORD="revdevindia"

#  URL Properties
export BASE_DOWNLOAD_URL="http://localhost:8080/core/resources/download/"

#  Security
export JWT_SIGNING_KEY="@v8nt^uqNBa3Xqoq2bn4J^n747Y@VEiODsId^@SNA&H96hZ0o6"
export JWT_MINUTES_TO_LIVE="20160"
