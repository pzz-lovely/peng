package observed;

public class ObserverPatternTest {
    public static void main(String[] args) {
        MyTopic topic = new MyTopic();
        Observer obj1 = new MyTopicSubscriber("obj1");
        Observer obj2 = new MyTopicSubscriber("obj1");
        Observer obj3 = new MyTopicSubscriber("obj1");

        topic.register(obj1);
        topic.register(obj1);
        topic.register(obj1);

        obj1.setSubject(topic);
        obj1.setSubject(topic);
        obj1.setSubject(topic);

        obj1.update();

        topic.postMessage("new Message");
    }
}
