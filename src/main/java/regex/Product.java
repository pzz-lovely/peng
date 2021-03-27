package regex;

public class Product {
    private int product_id;
    private String product_name;
    private double price;
    private int pnum;
    private String imgurl;
    private String description;
    private int brand_id;
    private int category_id;

    public Product(int product_id, String product_name, double price, int pnum, String imgurl, String description, int brand_id, int category_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.price = price;
        this.pnum = pnum;
        this.imgurl = imgurl;
        this.description = description;
        this.brand_id = brand_id;
        this.category_id = category_id;
    }

    public Product() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
