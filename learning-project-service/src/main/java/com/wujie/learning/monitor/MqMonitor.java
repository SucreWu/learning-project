package com.wujie.learning.monitor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * mq消费示例代码, 需要新建consumer时请参考示例代码修改,
 */
@Component
@Slf4j
public class MqMonitor {

    @RabbitListener(queues = "${env}test")
    @RabbitHandler
    public void test(String map){

        log.info("test接收到的消息是 {}", map);
        try {
            /** 业务逻辑处理
             * ...
             */
        } catch (Exception e) {
            log.error("test消息推送异常, 异常信息", e);
        }
    }

}
