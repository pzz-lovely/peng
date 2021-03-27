package com.peng.strategy.pool.future;

import com.peng.strategy.pool.Warehouse;
import com.peng.strategy.pool.listener.ItemsListener;
import com.peng.strategy.pool.work.Work;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author lovely
 * @Create 2020-11-18 16:04
 * @Description todo
 */
@Slf4j
public class RunnableFuture<T> implements Runnable {
    protected ArrayList<ItemsListener> listeners = new ArrayList<>();

    protected ResultFuture.ResultStatus resultStatus;

    protected Work<T> work;

    protected volatile FutureStatus status = FutureStatus.START;

    protected volatile Object result;

    public RunnableFuture(Work<T> work) {
        this.work = work;
    }

    @Override
    public void run() {
        T t = null;
        while (!status.equals(FutureStatus.CANCEL) && !Warehouse.isEmpty()) {
            try {
                t = work.processingItems();
                status = FutureStatus.RESULT;
                if (status.equals(FutureStatus.RESULT) && !listeners.isEmpty()) {
                    runListener();
                }
            } catch (Exception e) {
                status = FutureStatus.EXCEPTION;
                this.result = e;
                throw new RuntimeException(e);
            }
        }
        finish(t);
    }

    protected  void finish(Object r){
        status = FutureStatus.CANCEL;
        if (resultStatus.decrement()) {
            LockSupport.unpark(resultStatus.thread);
        }
        result =  r;
    }


    void addThread(ResultFuture.ResultStatus status) {
        resultStatus = status;
        log.info("resultStatus:{}",this.resultStatus);
    }

    public void runListener() {
        for (int i = 0; i < listeners.size(); i++) {
            listeners.get(i).processListener(this);
        }
    }



    static enum FutureStatus{
        START(1), RESULT(2),EXCEPTION(3),CANCEL(4);
        private int code ;

        FutureStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }


}