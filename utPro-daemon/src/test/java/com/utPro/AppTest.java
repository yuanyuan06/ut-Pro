package com.utPro;

import org.junit.Test;

import java.util.ResourceBundle;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void testee(){
        ResourceBundle test = ResourceBundle.getBundle("test");
        System.out.println(test.getString("test"));
    }
}
