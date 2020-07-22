# Kafka_Anwendung

- TerminalProducer:
Sendet Terminaleingaben als Value einer Nachricht an ein Kafka Cluster 
unter dem Topicname "test-topic".
Der Key dieser Nachrichten ist der aktuelle Zeistempel in der Form
"yyyy/MM/dd.HH:mm:ss".
Verbindet sich über localhost:9092 mit dem Cluster.
Bei Eingabe "shutdown" wird der TerminalProducer beendet.

- TerminalConsumer:
Gibt alle Nachrichten, des Topics "test-topic", eines Kafka Clusters
auf dem Terminal aus.
Verbindet sich über localhost:9092 mit dem Cluster.

- MQTTConnector:
Leitet alle Nachrichten eines MQTT Brokers an ein Kafka Cluster weiter.
Verbindet sich über localhost:9092 mit Kafka, über localhost:1883 mit
MQTT.
Topics der Form mqtt.name werden zu kafka.name abgeändert.
In Kafka bekommen die Nachrichten den String "key" als Key.