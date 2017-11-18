package com.example.liu.androidtest;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    //checks if the time calculation is valid
    @Test
    public void timeCalculation() throws Exception{
        Screen2 sc = new Screen2();
        assertEquals("00:00", sc.hourCounter("E", -1.0) );

    }

    //tries to organize a list of talks at morning
    @Test
    public void organizationOkMorning() throws Exception{
        Screen2 sc = new Screen2();
        ArrayList<Talk> a = new ArrayList<>();
        assertEquals("", sc.organizer(a, "M"));
    }

    //tries to organize a list of talks at evening
    @Test
    public void organizationOkEvening() throws Exception{
        Screen2 sc = new Screen2();
        ArrayList<Talk> a = new ArrayList<>();
        assertEquals("13:00  Evento de Networking\n", sc.organizer(a, "E"));
    }

    //checks if the method is reading a valid file
    public void file_isCorrect() throws Exception {
        Screen2  sc = new Screen2();
        assertEquals(18, sc.fileReader().size());
    }

}