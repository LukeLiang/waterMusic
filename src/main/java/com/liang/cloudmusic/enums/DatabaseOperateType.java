package com.liang.cloudmusic.enums;

/**
 * 云数据库的操作类型(增删改查)
 */
public enum DatabaseOperateType {

    DATABASE_ADD(1, "databaseadd"),
    DATABASE_DELETE(2, "databasedelete"),
    DATABASE_UPDATE(3, "databaseupdate"),
    DATABASE_QUERY(4, "databasequery");


    private int type;
    private String value;

    DatabaseOperateType(int type, String value){
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
