package com.wujie.learning.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient("learning-project/test")
public interface ArchetypeFeign {

    @GetMapping
    void test();
}
