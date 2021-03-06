package basic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by bin.liang on 2016/12/23.
 */
public class BasicProducer {
//    private final Producer<String, String> producer;
//    public final static String TOPIC = "test";

    private void send(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "54.70.215.146:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for(int i = 0; i < 100; i++) {
            System.out.println(i);
            producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)));
        }

        producer.close();

    }



    public static void main( String[] args )
    {
        new BasicProducer().send();
    }
}
