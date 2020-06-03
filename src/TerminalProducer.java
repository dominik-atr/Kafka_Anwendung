import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class TerminalProducer {

    private final static String BROKER = "localhost:9092", TOPIC = "test-topic";

    public static void main(String[] args){

        Producer<String, String> prod = new KafkaProducer<>(setProps());

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();

            while (!input.equals("shutdown")){

                prod.send(new ProducerRecord<>(TOPIC,
                        new SimpleDateFormat("yyyy/MM/dd.HH:mm:ss").format(new java.util.Date()), input));

                input = br.readLine();
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        prod.close();
    }


    private static Properties setProps() {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER);
        props.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        return props;
    }
}
