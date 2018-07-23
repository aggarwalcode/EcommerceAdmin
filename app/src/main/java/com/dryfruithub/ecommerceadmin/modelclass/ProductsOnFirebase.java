package com.dryfruithub.ecommerceadmin.modelclass;

public class ProductsOnFirebase {

    String p_short_name;    String p_full_name;
    String p_description;   int p_qty_per_unit;
    String p_unit;          String p_category;
    String p_size;          String p_img_url1;
    String p_img_url2;      String p_img_url3;
    String p_img_url4;      int p_id;
    String p_last_click;    String date_published;
    Boolean p_isavailable;  Boolean p_isPublished;

    int	p_price;     int p_discount;
    int p_unit_left; int p_visit_count;
    int p_total_click;int p_ranking;

    public ProductsOnFirebase(){}

    public ProductsOnFirebase(String p_short_name, String p_full_name, String p_description,
                              int p_qty_per_unit, String p_unit, String p_category, String p_size,
                              String p_img_url1, String p_img_url2, String p_img_url3,
                              String p_img_url4, int p_id, String p_last_click,
                              String date_published, Boolean p_isavailable,Boolean p_isPublished, int p_price,
                              int p_discount, int p_unit_left, int p_visit_count,
                              int p_total_click, int p_ranking) {
        this.p_short_name = p_short_name;
        this.p_full_name = p_full_name;
        this.p_description = p_description;
        this.p_qty_per_unit = p_qty_per_unit;
        this.p_unit = p_unit;
        this.p_category = p_category;
        this.p_size = p_size;
        this.p_img_url1 = p_img_url1;
        this.p_img_url2 = p_img_url2;
        this.p_img_url3 = p_img_url3;
        this.p_img_url4 = p_img_url4;
        this.p_id = p_id;
        this.p_last_click = p_last_click;
        this.date_published = date_published;
        this.p_isavailable = p_isavailable;
        this.p_price = p_price;
        this.p_discount = p_discount;
        this.p_unit_left = p_unit_left;
        this.p_visit_count = p_visit_count;
        this.p_total_click = p_total_click;
        this.p_ranking = p_ranking;
    }

    public String getP_short_name() {
        return p_short_name;
    }

    public void setP_short_name(String p_short_name) {
        this.p_short_name = p_short_name;
    }

    public String getP_full_name() {
        return p_full_name;
    }

    public void setP_full_name(String p_full_name) {
        this.p_full_name = p_full_name;
    }

    public String getP_description() {
        return p_description;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public int getP_qty_per_unit() {
        return p_qty_per_unit;
    }

    public void setP_qty_per_unit(int p_qty_per_unit) {
        this.p_qty_per_unit = p_qty_per_unit;
    }

    public String getP_unit() {
        return p_unit;
    }

    public void setP_unit(String p_unit) {
        this.p_unit = p_unit;
    }

    public String getP_category() {
        return p_category;
    }

    public void setP_category(String p_category) {
        this.p_category = p_category;
    }

    public String getP_size() {
        return p_size;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public String getP_img_url1() {
        return p_img_url1;
    }

    public void setP_img_url1(String p_img_url1) {
        this.p_img_url1 = p_img_url1;
    }

    public String getP_img_url2() {
        return p_img_url2;
    }

    public void setP_img_url2(String p_img_url2) {
        this.p_img_url2 = p_img_url2;
    }

    public String getP_img_url3() {
        return p_img_url3;
    }

    public void setP_img_url3(String p_img_url3) {
        this.p_img_url3 = p_img_url3;
    }

    public String getP_img_url4() {
        return p_img_url4;
    }

    public void setP_img_url4(String p_img_url4) {
        this.p_img_url4 = p_img_url4;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_last_click() {
        return p_last_click;
    }

    public void setP_last_click(String p_last_click) {
        this.p_last_click = p_last_click;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public Boolean getP_isavailable() {
        return p_isavailable;
    }

    public void setP_isavailable(Boolean p_isavailable) {
        this.p_isavailable = p_isavailable;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public int getP_discount() {
        return p_discount;
    }

    public void setP_discount(int p_discount) {
        this.p_discount = p_discount;
    }

    public int getP_unit_left() {
        return p_unit_left;
    }

    public void setP_unit_left(int p_unit_left) {
        this.p_unit_left = p_unit_left;
    }

    public int getP_visit_count() {
        return p_visit_count;
    }

    public void setP_visit_count(int p_visit_count) {
        this.p_visit_count = p_visit_count;
    }

    public int getP_total_click() {
        return p_total_click;
    }

    public void setP_total_click(int p_total_click) {
        this.p_total_click = p_total_click;
    }

    public int getP_ranking() {
        return p_ranking;
    }

    public void setP_ranking(int p_ranking) {
        this.p_ranking = p_ranking;
    }


    public Boolean getP_isPublished() {
        return p_isPublished;
    }

    public void setP_isPublished(Boolean p_isPublished) {
        this.p_isPublished = p_isPublished;
    }
}
