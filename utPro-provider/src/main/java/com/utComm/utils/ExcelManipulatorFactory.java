package com.utComm.utils;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by Administrator on 2016/2/3.
 */
public class ExcelManipulatorFactory {

    public static final String RULE_FILE = "excelcontent-definition-rule.xml";

    public void setConfig(String... configurations){
        for(String config: configurations){
            Digester digester = DigesterLoader.createDigester(
                    new InputSource(Thread.currentThread().getContextClassLoader()
                            .getResourceAsStream(RULE_FILE)));
            digester.setValidating(false);
            try {
                List<ExcelSheet> list =
                        (List<ExcelSheet>)digester.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream(config));
                for(ExcelSheet es: list)
                    sheetDefinitions.put(es.getName(), es);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Read excel config failed.");
            } catch (SAXException e) {
                e.printStackTrace();
                throw new RuntimeException("Read excel config failed.");
            }
        }
    }
}
