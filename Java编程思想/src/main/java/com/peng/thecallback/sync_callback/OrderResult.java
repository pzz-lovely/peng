package com.peng.thecallback.sync_callback;

/**
 * @Auther lovely
 * @Create 2020-07-04 11:02
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
