package com.example.always_refugally.DBCLASS;

import com.example.always_refugally.DBCLASS.Store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreData {

    private List<Store> store = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The store
     */
    public List<Store> getStore() {
        return store;
    }

    /**
     *
     * @param store
     * The store
     */
    public void setStore(List<Store> store) {
        this.store = store;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}