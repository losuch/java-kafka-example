# java-kafka-example

## Run kafka in local environment in kraft mode

generate a Kafka UUID

`kafka-storage random-uuid`

This returns a UUID, for example 76BLQI7sT_ql1mBfKsOk9Q

`kafka-storage format -t <uuid> -c /usr/local/etc/kafka/kraft server.properties`

This will format the directory that is in the log.dirs in the config/kraft/server.properties file

start Kafka:

`kafka-server-start /usr/local/etc/kafka/kraft/server.properties`
