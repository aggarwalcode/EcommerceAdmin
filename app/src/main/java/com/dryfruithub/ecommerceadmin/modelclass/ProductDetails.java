package com.dryfruithub.ecommerceadmin.modelclass;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductDetails implements Serializable {
    private String p_id;
    private String p_name;
    private String p_description;
    private String p_size;
    private boolean p_isavailable;
    private int p_qty_per_unit;
    private int p_ranking;
    private int p_price;
    private int p_discount;
    private int p_visit_count;
    private ArrayList<String> p_images;
    String p_imageStr;

    public ProductDetails(String p_id, String p_name, int p_price, int p_discount,
                       String p_description, boolean p_isavailable, int p_qty_per_unit,
                       int p_ranking, String p_size, int p_visit_count, ArrayList<String> p_images) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_discount = p_discount;
        this.p_description = p_description;
        this.p_isavailable = p_isavailable;
        this.p_qty_per_unit = p_qty_per_unit;
        this.p_ranking = p_ranking;
        this.p_size = p_size;
        this.p_visit_count = p_visit_count;
        this.p_images = p_images;
    }

    public ProductDetails(){}

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public void setP_discount(int p_discount) {
        this.p_discount = p_discount;
    }

    public void setP_description(String p_description) {
        this.p_description = p_description;
    }

    public void setP_isavailable(boolean p_isavailable) {
        this.p_isavailable = p_isavailable;
    }

    public void setP_qty_per_unit(int p_qty_per_unit) {
        this.p_qty_per_unit = p_qty_per_unit;
    }

    public void setP_ranking(int p_ranking) {
        this.p_ranking = p_ranking;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public void setP_visit_count(int p_visit_count) {
        this.p_visit_count = p_visit_count;
    }

    public void setP_images( int pos,String p_imageStr) {
        if (pos<p_images.size()){
        this.p_images.set(pos,p_imageStr);
        }else this.p_images.add(pos,p_imageStr);
    }

    public void setP_images(ArrayList<String> p_images){
        this.p_images=p_images;
    }

    public String getP_id() {
        return p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public int getP_price() {
        return p_price;
    }

    public int getP_discount() {
        return p_discount;
    }

    public String getP_description() {
        return p_description;
    }

    public boolean isP_isavailable() {
        return p_isavailable;
    }

    public int getP_qty_per_unit() {
        return p_qty_per_unit;
    }

    public int getP_ranking() {
        return p_ranking;
    }

    public String getP_size() {
        return p_size;
    }

    public int getP_visit_count() {
        return p_visit_count;
    }

    public  ArrayList<String> getP_images() {
        return p_images;
    }
}
