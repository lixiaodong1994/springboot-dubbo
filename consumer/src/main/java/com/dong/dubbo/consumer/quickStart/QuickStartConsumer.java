package com.dong.dubbo.consumer.quickStart;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dong.dubbo.ServiceAPI;
import org.springframework.stereotype.Component;

/**
 * @ClassName QuickStartServiceImpl
 * @Description
 * @Author admin
 * @Date 2018/12/24 15:08
 **/
@Component
public class QuickStartConsumer {
    @Reference(interfaceClass = ServiceAPI.class)
    ServiceAPI serviceAPI;

    public void sendMsg(String message) {
        System.out.println(serviceAPI.sendMessage(message));
    }

}
