package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamkafkaConsumer5003 {
    public static void main(String[] args) {
        SpringApplication.run(StreamkafkaConsumer5003.class, args);
    }
}
