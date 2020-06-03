# Kafka_Anwendung

- TerminalProducer:
Sendet, über das Terminal eingegebene, Nachrichten an ein Kafka Cluster, 
unter dem Topicname "test-topic". Der Key dieser Nachrichten ist der 
aktuelle Zeistempel in der Form "yyyy/MM/dd.HH:mm:ss".
Verbindet sich über localhost:9092 mit dem Cluster.
Bei Eingabe "shutdown" wird der TerminalProducer beendet.

- TerminalConsumer:
Gibt Nachrichten, unter dem topic "test-topic", eines Kafka Clusters
auf dem Terminal aus.
Verbindet sich über localhost:9092 mit dem Cluster.
Stoppt nicht von alleine.

- MQTTConnector:
Leitet alle Nachrichten eines MQTT Brokers an ein Kafka Cluster weiter.
Verbindet sich über localhost:9092 mit Kafka, über localhost:1883 mit
MQTT.
Topics der Form mqtt.<name> werden zu kafka.<name> abgeändert.
In Kafka bekommen die Nachrichten als Key den aktuellen Zeistempel
in der Form "yyyy/MM/dd.HH:mm:ss".