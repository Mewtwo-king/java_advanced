package com.ljw.lock_qs;

public enum City_Enum {
    one(1,"楚国"),two(2,"韩国"),three(3,"燕国"),four(4,"赵国");
    private String name;
    private Integer index;
    City_Enum(Integer index,String name) {
        this.name = name;
        this.index = index;
    }
    public static City_Enum getInstance(int index){
        City_Enum[] values = City_Enum.values();
        for (City_Enum el: values) {
            if(el.getIndex()==index){
                return el;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
