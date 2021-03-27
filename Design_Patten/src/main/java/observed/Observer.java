package observed;

/**
 * 相关联的观察者。他有一个方法能是subject附属于一个观察者。另外的方法能接受subject的变化通知
 */
public interface Observer {
    void update();

    void setSubject(Subject sub);
}
