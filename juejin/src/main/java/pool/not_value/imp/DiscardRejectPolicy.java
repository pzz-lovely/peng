package pool.not_value.imp;

import pool.not_value.RejectPolicy;

/**
 * ¶ªÆú²ßÂÔ½Ó¿Ú
 */
public class DiscardRejectPolicy implements RejectPolicy {
    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        //do nothing
        System.out.println("discard one task");
    }
}
