package com.example.always_refugally.DBCLASS;

import com.example.always_refugally.DBCLASS.Product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store implements Serializable{

    private String name;
    private int total;
    private double lat;
    private double lon;
    private double dis;
    private List<Product> product = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The name
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The total
     */
    public int getTotal() {
        return total;
    }

    /**
     *
     * @param total
     * The total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     *
     * @return
     * The lat
     */
    public double getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public double getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(double lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The product
     */
    public List<Product> getProduct() {
        return product;
    }

    /**
     *
     * @param product
     * The product
     */

    public double getdis()
    {
        return dis;
    }

    public void setdis(double input)
    {
        dis = input;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}