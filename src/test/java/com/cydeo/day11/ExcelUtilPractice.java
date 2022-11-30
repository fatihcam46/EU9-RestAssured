package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.*;

import java.util.*;

public class ExcelUtilPractice {



    @Test
    public void test1(){

        //How to use excelUtil file ?
        //it accepts two arguments
        //Argument 1: location of the file(path)
        //Argument 2: sheet that we want to open               from resources  Vytracktestdata.xlsx"
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-short");//
                                                                        //worksheet name is QA3-short
        //method for returning list of map
        List<Map<String, String>> dataList = vytrackFile.getDataList();//getDataList();  we can save whole table
        for (Map<String, String> rowmap : dataList) {
            System.out.println(rowmap);
            /*
            {password=UserUser123, firstname=John, username=user1, lastname=Doe}
            {password=UserUser123, firstname=Kyleigh, username=user4, lastname=Reichert} ......
             */
        }

    }

}
