package tech.dork.randoms_generator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.dork.randoms_generator.DTOs.WebHookDetails;
import tech.dork.randoms_generator.configs.RabbitMQConfig;

@Service
@Slf4j
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    RabbitMQConfig mqConfig = new RabbitMQConfig();

    public void send(WebHookDetails webHookDetails) {

        String exchange = mqConfig.getExchange();
        String routingKey = mqConfig.getRoutingKey();


        rabbitmqTemplate.convertAndSend(exchange, routingKey, impl(webHookDetails));
        log.info("send message => {}", webHookDetails );
    }

    public String  impl(WebHookDetails webHookDetails) {
        RestTemplate restTemplate = new RestTemplate();
        String response = null;
        try {
            //request url
            String url = "https://hooks.slack.com/services/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";

            //create request
            HttpEntity request = new HttpEntity<>(webHookDetails);

            var result  = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            response =  result.getBody();
        } catch (Exception exception){
            log.info("Error message => {}",exception.getMessage());
        }

    return response;
    }
}
