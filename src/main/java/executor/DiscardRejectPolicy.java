package executor;

public class DiscardRejectPolicy implements RejectPolicy {

    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        System.out.println("discard in task");
    }
}
