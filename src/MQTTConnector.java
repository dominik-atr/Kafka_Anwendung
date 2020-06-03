import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.paho.client.mqttv3.*;
import java.text.SimpleDateFormat;
import java.util.Properties;


/**
 * alle MQTT Nachrichten werden an Kafka geleitet, wenn topic mit "mqtt" anfängt wird das Präfix durch
 * "kafka" ersetzt, sonst bleibt das topic gleich
 */

public class MQTTConnector {


    private final static String MQTT_SERVER_URL = "tcp://localhost:1883",
            MQTT_CLIENT_ID = "KafkaConnector",
            KAFKA_BROKER = "localhost:9092";


    public static void main(String[] args) throws MqttException {

        IMqttClient client = new MqttClient(MQTT_SERVER_URL, MQTT_CLIENT_ID);
        client.connect(setOpts());

        client.subscribe("#", setListenerAction());
    }



    private static IMqttMessageListener setListenerAction(){

        Producer<String, String> prod = new KafkaProducer<>(setProps());

        return (topic, msg) -> {

            String newTopic = topic;

            if(topic.startsWith("mqtt")){
                newTopic = topic.replaceFirst("mqtt","kafka");
            }

            String date = new SimpleDateFormat("yyyy/MM/dd.HH:mm:ss").format(new java.util.Date());
            prod.send(new ProducerRecord<String, String>(newTopic, date, msg.toString()));

        };
    }


    private static MqttConnectOptions setOpts(){
        MqttConnectOptions opts = new MqttConnectOptions();
        opts.setAutomaticReconnect(true);
        opts.setCleanSession(true);
        opts.setConnectionTimeout(10);
        return opts;
    }



    private static Properties setProps() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER);
        props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }
}
