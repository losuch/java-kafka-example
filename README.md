# java-kafka-example

This workspace contains multiple projects demonstrating various aspects of software development, primarily focusing on Java and Apache Kafka. Each project is self-contained and serves a specific purpose.

### Project Structure

The project structure is as follows:

- `src/`: Contains the source code files.
- `config/`: Contains the Kafka configuration files.
- `README.md`: This file, providing an overview of the project and instructions for running Kafka.

Feel free to explore the project and make any necessary modifications.

### Projects

1. ConsumerDemoCooperative: This project is a simple demonstration of a Kafka Consumer using the cooperative rebalancing strategy. It is written in Java and uses the Apache Kafka library.

2. ProducerDemo: This project demonstrates the basic usage of a Kafka Producer. It shows how to send messages to a Kafka topic.

3. StreamsDemo: This project is an introduction to Kafka Streams. It demonstrates how to perform simple transformations on data in Kafka topics.

4. ConnectDemo: This project shows how to use Kafka Connect to import/export data from/to Kafka.

### Setup

Each project has its own setup instructions, which can be found in the project's README file. In general, you will need to have Apache Kafka installed and running on your machine.

### Running the Projects

Each project can be run independently. Check the specific README file of the project for instructions on how to run it.

This workspace contains the `java-kafka-example` project, which demonstrates how to work with Kafka in a local environment using kraft mode.

### Kafka Setup

To run Kafka in local environment in kraft mode, follow these steps:

1. Generate a Kafka UUID by running the following command:

   ```
   kafka-storage random-uuid
   ```

   This will return a UUID, for example `76BLQI7sT_ql1mBfKsOk9Q`.

2. Format the directory specified in the `log.dirs` property in the `config/kraft/server.properties` file using the generated UUID. Run the following command:

   ```
   kafka-storage format -t <uuid> -c /usr/local/etc/kafka/kraft/server.properties
   ```

3. Start Kafka by running the following command:
   ```
   kafka-server-start /usr/local/etc/kafka/kraft/server.properties
   ```

### Contributing

Contributions are welcome. Please open an issue to discuss your idea before making a large change.

### License

This workspace is open-source software licensed under the MIT license. Please see the LICENSE file for details.
