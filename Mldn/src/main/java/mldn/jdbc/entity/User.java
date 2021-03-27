package mldn.jdbc.entity;

import mldn.jdbc.annotation.Column;
import mldn.jdbc.annotation.Primary;
import mldn.jdbc.annotation.Table;

import java.lang.reflect.Type;
import java.sql.Types;

/**
 * @Auther lovely
 * @Create 2020-03-30 21:12
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
@Table("user")
public class User {
    @Primary
    @Column(name = "uid", type = Types.VARCHAR, length = 50)
    private String udi;
    @Column(name="name",type=Types.VARCHAR,length = 30)
    private String name;
    @Column(name = "age",type=Types.INTEGER,length = -1)
    private Integer age;
    @Column(name = "salary",type = Types.FLOAT)
    private Double salary;


    public String getUdi() {
        return udi;
    }

    public void setUdi(String udi) {
        this.udi = udi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
