package com.utComm;

import com.alibaba.dubbo.common.json.JSON;
import com.utComm.ut.entity.SkuWhResult;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public class TestExcelPoi {

    private String filePath;

    @Test
    public void  readExcel() throws IOException {
        filePath = System.getProperty("user.dir")+"\\file\\tmall.xls";

        System.out.println(filePath);
        POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(filePath));
        Workbook wb = WorkbookFactory.create(fs);
        Sheet sheet = wb.getSheetAt(0);
        Row rows = sheet.getRow(0);
        int listRowNum = rows.getLastCellNum();
        short lastCellNum = (short) sheet.getLastRowNum();
        for(int i = 1; i <= lastCellNum; i++){
            Row row = sheet.getRow(i);
            Cell colTitle = row.getCell(0);
            for(int j=1; j < listRowNum; j++){
                Cell rowTitle = sheet.getRow(0).getCell(j);
                Cell cell = row.getCell(j);
                int cellValue = cell == null ? 0: (int) cell.getNumericCellValue();
                String [] strArr = new String[]{rowTitle.getStringCellValue(), colTitle.getStringCellValue(), String.valueOf(cellValue)};
                System.out.print(JSON.json(strArr));
            }
            System.out.print("\n");
        }
    }

//    @Test
    public void exE() throws IOException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        SkuWhResult rs = new SkuWhResult();
        PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(rs.getClass());

        List<String> propertyList = new ArrayList<String>();
        for(PropertyDescriptor p: propertyDescriptors) {
            if(p.getWriteMethod() != null){


                propertyList.add(p.getName());

                Method writeMethod = p.getWriteMethod();
                if(p.getPropertyType() == String.class){
                    writeMethod.invoke(rs, "fdf");
                }
            }
        }
//        System.out.println(JSON.json(rs));
        String[] titles =  propertyList.toArray(new String[propertyList.size()]);
//        System.out.println(JSON.json(arrStr));

        for(int i = 0; i < titles.length; i++){
            Field field = rs.getClass().getDeclaredField(titles[i]);
//            System.out.println(field.getType());
//            System.out.println(field.getName());
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(rs.getClass(),field.getName());
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if(field.getType() == Long.class){
                writeMethod.invoke(rs, 4L);
            }
        }


    }


    public void exportExcel(String path,String fileName) throws IOException {
        POIFSFileSystem fs=new POIFSFileSystem(new FileInputStream(path+"\\"+fileName));
        Workbook wb = WorkbookFactory.create(fs);
        wb.createSheet("sheet1");
    }
//   @Test
    public void getPath(){
//      String path = getClass().getClassLoader().getResource("/").getPath();
//      String path = getClass().getClassLoader().getResource("/").getFile().toString();
//      String path0 =  Thread.currentThread().getContextClassLoader().getResource("/").getPath();
       String relativelyPath=System.getProperty("user.dir");
       System.out.println(relativelyPath);
//       System.out.println(path0);

   }
}
