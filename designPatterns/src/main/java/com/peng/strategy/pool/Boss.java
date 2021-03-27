package com.peng.strategy.pool;

import com.peng.strategy.pool.context.Context;
import com.peng.strategy.pool.entity.Item;
import com.peng.strategy.pool.future.Future;
import com.peng.strategy.pool.future.ResultFuture;
import com.peng.strategy.pool.future.StaffFuture;
import com.peng.strategy.pool.handler.ElectricalHandler;
import com.peng.strategy.pool.handler.FoodHandler;
import com.peng.strategy.pool.handler.MakeupHandler;
import com.peng.strategy.pool.work.Staff;
import com.peng.strategy.pool.wrapper.ItemWrapper;
import com.peng.strategy.pool.wrapper.ResourceWrapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author lovely
 * @Create 2020-11-19 8:53
 * @Description todo
 */
@Slf4j
public class Boss {
    private volatile  Context context;

    private MyExecutorService myExecutorService;
    private ResultFuture resultFuture;

    private List<Staff> staffs = new ArrayList<>();

    private final ItemWrapper itemWrapper;

    private volatile Warehouse warehouse;


    private int threadCount = 0;


    public Boss() {
        itemWrapper = new ItemWrapper();
        myExecutorService = new MyExecutorService(10, new ArrayBlockingQueue<>(10), 1000, TimeUnit.SECONDS, new ThreadFactory() {
            private int index;

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "ExecutorService" + (++index));
            }
        });

    }

    public synchronized void init(){
        context = new Context(this);
        resultFuture = new ResultFuture();

        warehouse = new Warehouse(this);
        log.info("添加Handler");
        context.addHandler(new ElectricalHandler(context));
        context.addHandler(new FoodHandler(context));
        context.addHandler(new MakeupHandler(context));


        Random random;
        ResourceWrapper[] resource;
        random = new Random();
        Item[] items = new Item[Warehouse.SIZE];
        resource = context.getResources();
        int length = resource.length;
        // 初始化库存
        log.info("初始化库存");
        for (int i = 0; i < Warehouse.SIZE; i++) {
            String s = resource[random.nextInt(length)].getResource();
            items[i] = new Item(s + ":" + i, false, s);
        }
        Warehouse.addItems(items);
        log.info("全部物品为：{}", Arrays.toString(items));

        myExecutorService.setResultFuture(resultFuture);


    }

    public <T>  Boss addStaff(final Staff<T> staff) {
        staffs.add(staff);
        return this;
    }

    public <T> Future<List<T>> future(){
        return resultFuture;
    }

    public synchronized void start() {
        itemWrapper.setAllTag(false);
        itemWrapper.initItemWrapper(Warehouse.subList());
        context.getTrigger().setThreadCount(staffs.size());
        for (int i = 0; i < staffs.size(); i++) {
            StaffFuture future = myExecutorService.submit(staffs.get(i));
            log.info("拿到的future{}", future);
        }
    }


    public int getThreadCount() {
        return threadCount;
    }


    public void stop() {
        log.info("items:{}",Warehouse.getItems());
        resultFuture.cancel();
    }



    public Context getContext() {
        return context;
    }


    public MyExecutorService getMyExecutorService() {
        return myExecutorService;
    }

    public void setMyExecutorService(MyExecutorService myExecutorService) {
        this.myExecutorService = myExecutorService;
    }


    public ItemWrapper getItemWrapper() {
        return itemWrapper;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}