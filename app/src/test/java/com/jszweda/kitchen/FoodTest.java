package com.jszweda.kitchen;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class FoodTest {

    @Test
    public void getDaysTest(){
        Food food = new Food("kasza", LocalDate.now().plusDays(3), 5, 6);
        int diffDays = food.getDaysLeft();
        Assert.assertEquals(3, diffDays);
    }

}