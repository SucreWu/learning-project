package com.wujie.learning.proxy;

import com.wujie.learning.service.Employee;

/**
 * @author wujie
 * @Description
 * @date 2023/2/6 16:40
 */
public class Developer implements Employee {

    @Override
    public void work() {
        System.out.println("日常开发");
    }
}
