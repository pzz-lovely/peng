package mldn.cascade.entity;

/**
 * @Auther lovely
 * @Create 2020-03-22 10:41
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Company {
    private Integer cno;
    private String cname;


    @Override
    public String toString() {
        return "Company{" +
                "cno=" + cno +
                ", cname='" + cname + '\'' +
                '}';
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
