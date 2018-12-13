package io.benjyair.rukia;

import com.google.gson.annotations.SerializedName;

public class City {

    /**
     * _id : 1
     * id : 1
     * pid : 0
     * city_code : 101010100
     * city_name : 北京
     */

    @SerializedName("city_code")
    private String code;
    @SerializedName("city_name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
