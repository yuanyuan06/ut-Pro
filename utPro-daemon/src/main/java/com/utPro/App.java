package com.utPro;

import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        ResourceBundle test = ResourceBundle.getBundle("test");
        System.out.println(test.getString("test"));
    }
}
