package com.cydeo.day5;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;//looks like assertion class
import static org.hamcrest.Matchers.*;//

public class HamcrestMatchersIntro {

    @DisplayName("Assertion with numbers")
    @Test
    public void simpleTest1(){

        //actual 5+5
        assertThat(5+5, is(10));//assertThat(5+5,equalTo(10));  same as
        assertThat(5+5,equalTo(10));
        //matchers has 2 overloaded version
        //first that accept actual value
        //second that accept another matchers
        //below examples is method is accepting another matchers equal to make it readable
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));//you can say not 9
        assertThat(5+5,is(not(9)));//you can say is(not(9)
        assertThat(5+5,is(not(equalTo(9))));//you can say is(not(equalTo(9)))  all of SAME
//www.hamcrest.org  all methods
        //number comparison  methods:
        //greaterThan()
        //greaterThanOrEqualTo()
        //lessThan()
        //lessThanOrEqualTo()
        assertThat(5+5,is(greaterThan(9)));//number comparison
        assertThat(6*6 , lessThan(39));//correct
    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "B22 is learning Hamcrest";

        //checking for euqality is same as numbers
        assertThat(text,is("B22 is learning Hamcrest"));//not compile error same as STRING
        assertThat(text,equalTo("B22 is learning Hamcrest"));//SAME BEFORE
        assertThat(text,is(equalTo("B22 is learning Hamcrest")));//SAME BEFORE

        //check if this text starts with B22
        assertThat(text,startsWith("B22"));
        //now do it in case insensitive manner
        assertThat(text,startsWithIgnoringCase("b22"));//Lower case ignore it
        //endswith
        assertThat(text,endsWith("rest"));

        //check if text contains String learning
        assertThat(text,containsString("learning"));//it is string thats why containsString
        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEARNING"));
//----------------------------------------------------------
        String str ="  ";//which has space inside

        //check if above str is blank
        assertThat(str,blankString());
        //check if trimmed str is empty string
        assertThat(str.trim(),emptyString());//this string is empty
    }

    @DisplayName("Hamcrest for Collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        assertThat(listOfNumbers,hasSize(10));//10 items are there   true

        //check if this list hasItem 77
        assertThat(listOfNumbers,hasItem(77));//has 77  true

        //check if this list hasItems 77,54,23
        assertThat(listOfNumbers,hasItems(77,54,23)); //77,54,23  true

        //check if all numbers greater than 0
        assertThat(listOfNumbers,everyItem(greaterThan(0)));//everyItem method: all of greater than 0

    }


}
