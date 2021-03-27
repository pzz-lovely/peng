package com.peng.thecallback.async_callback;

/**
 * @Auther lovely
 * @Create 2020-07-04 12:30
 * @Description todo
 */
public interface OrderResult {
    /**
     * 订购货物的状态
     * @param state
     * @return
     */
    public String getOrderResult(String state);
}
