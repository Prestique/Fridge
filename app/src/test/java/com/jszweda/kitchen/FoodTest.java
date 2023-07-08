package com.jszweda.kitchen;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class FoodTest {

    @Test
    public void getDaysTest3(){
        Food food = new Food("kasza", LocalDate.now().plusDays(3), 5, 6);
        long diffDays = food.getDaysLeft();
        Assert.assertEquals(3, diffDays);
    }
    @Test
    public void getDaysTest180(){
        Food food = new Food("kasza", LocalDate.now().plusDays(180), 5, 6);
        long diffDays = food.getDaysLeft();
        Assert.assertEquals(180, diffDays);
    }
    @Test
    public void getMinusDaysTest99(){
        Food food = new Food("kasza", LocalDate.now().minusDays(99), 5, 6);
        long diffDays = food.getDaysLeft();
        Assert.assertEquals(-99, diffDays);
    }



}