package com.utPro;

import com.utPro.exception.CustomException;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    @Test
    public void fdfd(){
        throw new CustomException(2, new Object[]{"异常"});
    }
}
