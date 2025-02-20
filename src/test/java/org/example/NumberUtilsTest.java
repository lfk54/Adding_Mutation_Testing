package org.example;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {
    /**
     *
     * Step 1: understand the requirement, input type and output type
     *        Requirement: Add two list of integer, index by index, and returns another list
     *
     * Step 2 (raw):  Perform partition and boundary analysis on input and output
     *        Each input: left | right
     *        Combination of input:
     *        Output:
     *  Step 3: Derive potential test cases
     *
     */

    @Test
    public void testOneNullInput(){
        //test case for one null input
        //should return null
        List<Integer> left = null;
        List<Integer> right = new ArrayList<>(List.of());
        assertNull(NumberUtils.add(left, right));
    }
    @Test
    public void testTwoNullInputs(){
        //test case for two null inputs
        //should return null
        List<Integer> left = null;
        List<Integer> right = null;
        assertNull(NumberUtils.add(left, right));
    }

    @Test
    public void testBothListsEmpty(){
        //test case for two empty inputs
        //should return empty list
        List<Integer> left = new ArrayList<>(List.of());
        List<Integer> right = new ArrayList<>(List.of());
        List<Integer> expectedOutput = new ArrayList<>(List.of());
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testOneListEmpty(){
        //test case for one empty input, one input with length >=1
        //should return the non-empty input
        List<Integer> left = new ArrayList<>(List.of());
        List<Integer> right = new ArrayList<>(Arrays.asList(3, 4));
        List<Integer> expectedOutput = new ArrayList<>(Arrays.asList(3, 4));
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testBothListsValidDoubleDigits(){
        //test case for if both lists have >1 integers and no invalid elements
        //should return a list with the sum of the input lists
        List<Integer> left = new ArrayList<>(List.of(2, 5));
        List<Integer> right = new ArrayList<>(List.of(1, 0));
        List<Integer> expectedOutput = new ArrayList<>(List.of(3, 5));
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testBothListsValidSingleDigits(){
        //test case for if both lists have 1 integer and no invalid elements
        //should return a list with the sum of the input lists
        List<Integer> left = new ArrayList<>(List.of(2));
        List<Integer> right = new ArrayList<>(List.of(1));
        List<Integer> expectedOutput = new ArrayList<>(List.of(3));
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testBothListsValidMixedDigits(){
        //test case for if one list has 2 elements and the other has 1, with no invalid elements
        //should return a list with the sum of the input lists
        List<Integer> left = new ArrayList<>(List.of(2, 5));
        List<Integer> right = new ArrayList<>(List.of(5));
        List<Integer> expectedOutput = new ArrayList<>(List.of(3, 0));
        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testOneListInvalid(){
        //test case for if one list has an invalid element
        //should throw IllegalArgumentException
        List<Integer> left = Arrays.asList(2, -1);
        List<Integer> right = new ArrayList<>(List.of(5));
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(left, right);
        });
    }

    @Test
    public void testBothListsInvalid(){
        //test case for if both lists have an invalid element
        //should throw IllegalArgumentException
        List<Integer> left = Arrays.asList(2, -1);
        List<Integer> right = new ArrayList<>(List.of(-5));
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(left, right);
        });
    }

    @Test
    public void testNegativeSumThrowsException() {
        //test case to make sure IllegalArgumentException is thrown for invalid digits
        List<Integer> left = new ArrayList<>(List.of(-1, 2));
        List<Integer> right = new ArrayList<>(List.of(5));

        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(left, right);
        });
    }

    @Test
    public void testCarryOverMultipleDigits() {
        //test case for carry over to make sure digits are tracked correctly
        List<Integer> left = new ArrayList<>(List.of(9, 9));
        List<Integer> right = new ArrayList<>(List.of(1));
        List<Integer> expectedOutput = new ArrayList<>(List.of(1, 0, 0));

        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testRightListLongerThanLeft() {
        //test case for making sure leading zeros are handled correctly when one list is longer than the other
        List<Integer> left = new ArrayList<>(List.of(2));
        List<Integer> right = new ArrayList<>(List.of(9, 8));
        List<Integer> expectedOutput = new ArrayList<>(List.of(1, 0, 0));

        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testLeftListLongerThanRight() {
        //test case for making sure leading zeros are handled correctly when one list is longer than the other
        List<Integer> left = new ArrayList<>(List.of(9, 9));
        List<Integer> right = new ArrayList<>(List.of(1));
        List<Integer> expectedOutput = new ArrayList<>(List.of(1, 0, 0));

        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

    @Test
    public void testRemoveLeadingZeroesEdgeCase() {
        //test case to make sure significant leading 0s (the number 0) are correctly handled
        List<Integer> left = new ArrayList<>(List.of(0));
        List<Integer> right = new ArrayList<>(List.of(0));
        List<Integer> expectedOutput = new ArrayList<>(List.of(0));

        assertEquals(expectedOutput, NumberUtils.add(left, right));
    }

}