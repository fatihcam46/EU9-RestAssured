package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.*;

public class MethodSourceParameterizedTest {


    @ParameterizedTest
    @MethodSource("getNames")//public static List<String> getNames(){  add here getNames  inside just write names
    public void testPrintNames(String name){

        System.out.print("name = " + name +", ");
        //name = Parvin, name = Nasim, name = mohamad, name = Nadir, name = Saim, name = Gurhan, name = Murodil,
    }



    public static List<String> getNames(){
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Excel
        //other APIs

        List<String> nameList = Arrays.asList("Parvin","Nasim","mohamad","Nadir","Saim","Gurhan","Murodil");
        return  nameList;
    }

    public static List<Map<String,String>> getExcelData(){
        //get your file object
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-all");
        //return sheet as a a list of map                                           worksheet name:QA3-all
        return vytrackFile.getDataList();

    }

    @ParameterizedTest
   @MethodSource("getExcelData")
    public void excelParamTest(Map<String,String> userInfo){

        System.out.println("FirstName: "+userInfo.get("firstname"));//this information coming from excel
        System.out.println("LastName: "+userInfo.get("lastname"));

    }


}
