package observed;

/**
 * ������Ĺ۲��ߡ�����һ����������subject������һ���۲��ߡ�����ķ����ܽ���subject�ı仯֪ͨ
 */
public interface Observer {
    void update();

    void setSubject(Subject sub);
}
