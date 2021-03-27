package mldn.cascade.entity;

/**
 * @Auther lovely
 * @Create 2020-03-22 10:36
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class Dept {
    private Double deptNo;
    private String deptName;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Double getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Double deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", company=" + company +
                '}';
    }
}
