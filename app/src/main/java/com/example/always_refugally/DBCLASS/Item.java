package com.example.always_refugally.DBCLASS;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Item implements Serializable {
    public Item(String p, int v) {
        this.volume = v;
        this.pid = p;
    }

    private int volume;
    private String pid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The volume
     */
    public int getVolume() {
        return volume;
    }

    /**
     *
     * @param volume
     * The volume
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     *
     * @return
     * The pid
     */
    public String getPid() {
        return pid;
    }

    /**
     *
     * @param pid
     * The pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

