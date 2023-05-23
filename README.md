# hello-kafka

Kafka code examples

## Setup

1. Install Kafka from [here](https://kafka.apache.org/downloads)
1. Start ZooKeeper
    ```
    bin/zookeeper-server-start.sh config/zookeeper.properties
    ```
1. Start Kafka broker on another terminal
    ```
    bin/kafka-server-start.sh config/server.properties
    ```
## Regular Kafka example

1. Run `HelloKafkaApp`
1. App will produce a message and then consume that same event and print on console like below
    ```
    helloEvent = HelloEvent(name=2023-05-23T11:36:15.470)
    ```

## Stream example

1. Listen and consume messages from `output-topic`
    ```
    bin/kafka-console-consumer.sh --topic output-topic --from-beginning --bootstrap-server localhost:9092
    ```
1. Run `HelloStreamApp`
1. Produce messages to `input-topic`
    ```
    bin/kafka-console-producer.sh --topic input-topic --bootstrap-server localhost:9092
    >aaa
    >bbb
    ```
1.  You should see the capitalized messages in the output-topic terminal
    ```
    bin/kafka-console-consumer.sh --topic output-topic --from-beginning --bootstrap-server localhost:9092
    AAA
    BBB
    ```
