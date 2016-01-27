package com.utComm.ut.eum;

/**
 * Created by Administrator on 2016/1/6.
 */
public enum EumComm {
    value0(0),
    vlaue1(1),
    value2(2);

    private int value;

    private EumComm(int value){
        value = value;
    }
    public int getValue(){
        return value;
    }

    public static EumComm valueOf(int value) {
        switch (value) {
            case 0:
                return value0;
            case 1:
                return vlaue1;
            case 2:
                return value2;
            default:
                throw new IllegalArgumentException();
        }
    }
}
