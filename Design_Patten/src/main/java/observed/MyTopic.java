package observed;

import java.util.ArrayList;
import java.util.List;

public class MyTopic implements Subject {
    private List<Observer> observers;
    private String message;
    private boolean changed;
    private final Object MUTEX = new Object();

    public MyTopic(){
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer obj) {
        if(obj == null) throw new NullPointerException("obj is null");
        //如果不存就添加
        if(!observers.contains(obj)) observers.add(obj);
    }

    @Override
    public void unregister(Observer obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        List<Observer> observersLocal = null;
        //synchronized is used to make sure any observer registered after message is received is not notified
        synchronized (MUTEX) {
            if(!changed)
                return;
            observersLocal = new ArrayList<>(this.observers);
            this.changed = false;
        }
        for (Observer obj : observersLocal) {
            obj.update();
        }
    }

    @Override
    public Object getUpdate(Observer object) {
        return this.message;
    }

    public void postMessage(String msg) {
        System.out.println("message posted to topic : " + msg);
        this.message = msg;
        this.changed = true;
        notifyObservers();
    }
}
