package com.wujie.learning.config;

// TODO 需要使用mq则打开注释
//@Configuration
public class TestRabbitMqConfig {

//    // ${env}用于区分环境，dev为mq-dev-，qa为mq-qa-，prod为mq-prod-
//    // TODO 接入apollo后，env参数在qa和prd环境都已按默认指配好，如需修改请联系管理员进行覆盖
//    @Value("${env}")
//    private String env;
//
//    /**
//     * 交换机名称
//     */
//    public static String TEST_EXCHANGE = "test";
//
//    /**
//     * 绑定key名称
//     */
//    public static String BIND_TEST = "test";
//
//
//    /**
//     * 队列名称
//     */
//    public static String QUEUE_TEST = "test";
//
//
//    /**
//     * 构建DirectExchange交换机
//     */
//    @Bean
//    public DirectExchange testExchange() {
//        // 支持持久化，长期不用删除
//        return new DirectExchange(TEST_EXCHANGE, true, false);
//    }
//
//    /**
//     * 创建队列
//     */
//    @Bean
//    public Queue createTestQueue() {
//        return new Queue(env + QUEUE_TEST);
//    }
//
//    /**
//     * 绑定交换机和队列
//     */
//    @Bean
//    public Binding testBinding() {
//        return BindingBuilder.bind(createTestQueue()).to(testExchange()).with(env + BIND_TEST);
//    }
}
