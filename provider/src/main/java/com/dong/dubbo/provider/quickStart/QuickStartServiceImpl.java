package com.dong.dubbo.provider.quickStart;

import com.alibaba.dubbo.config.annotation.Service;
import com.dong.dubbo.ServiceAPI;
import org.springframework.stereotype.Component;

/**
 * @ClassName QuickStartServiceImpl
 * @Description 实现类
 * @Author LXD
 * @Date 2018/12/24 15:05
 **/
@Service(interfaceClass = ServiceAPI.class)
@Component
public class QuickStartServiceImpl implements ServiceAPI {
    @Override
    public String sendMessage(String msg) {
        return "quickStart-provider=" + msg;
    }
}
