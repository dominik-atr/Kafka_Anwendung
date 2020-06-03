import org.apache.kafka.clients.consumer.*;
import java.util.*;

public class TerminalConsumer {

    private final static String BROKER = "localhost:9092";
    private final static Collection<String> TOPICS = Collections.singletonList("test-topic");

    public static void main(String[] args){

        KafkaConsumer<String, String> cons = new KafkaConsumer<>(setProps());
        cons.subscribe(TOPICS);

        while(true){
            ConsumerRecords<String, String> recs = cons.poll(100);

            for(ConsumerRecord<String, String> rec : recs){
                System.out.println(rec.key() + ": " + rec.value());
            }
        }
    }


    private static Properties setProps() {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER);
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}
