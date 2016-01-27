package com.utPro.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/28.
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -8429553052311613701L;

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

}
