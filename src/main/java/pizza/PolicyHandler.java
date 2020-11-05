package pizza;

import pizza.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    OrderRepository OrderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDelivered_UpdateStatus(@Payload Delivered delivered){

        if(delivered.isMe()){

            if("Delivered".equals(delivered.getDeliveryStatus())){
                Optional<Order> orderOptional = OrderRepository.findById(delivered.getOrderId());
                Order order = orderOptional.get();
                order.setOrderStatus(delivered.getDeliveryStatus());

                OrderRepository.save(order);
            }
            System.out.println("##### listener UpdateStatus : " + delivered.toJson());
        }
    }

    //@StreamListener(KafkaProcessor.INPUT)
    //public void wheneverPaid_UpdateStatus(@Payload Paid paid){

    //    if(paid.isMe()){

    //        if("Paid".equals(paid.getPaymentStatus())){
    //            Optional<Order> orderOptional = OrderRepository.findById(paid.getOrderId());
    //            Order order = orderOptional.get();
    //            order.setOrderStatus(paid.getPaymentStatus());

    //            OrderRepository.save(order);
    //        }
    //        System.out.println("##### listener UpdateStatus : " + paid.toJson());
    //    }
    //}

}
