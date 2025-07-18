package springcloud.consumer;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MessageConsumer {
    @Bean
    public Consumer<String> kafka(){
        return message -> {
            System.out.println("接受消息:" + message);
        };
    }
}
