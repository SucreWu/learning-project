package com.wujie.learning.service.impl;

import com.wujie.learning.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableAsync
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async("threadPool")
    public void asyncTask() {
        // 定时任务业务逻辑
    }
}
