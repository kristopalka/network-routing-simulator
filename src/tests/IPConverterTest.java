package tests;

import org.junit.jupiter.api.Test;
import tools.IPConverter;

import static org.junit.jupiter.api.Assertions.*;

class IPConverterTest
{

    @Test
    void strToNum()
    {
        assertEquals(3232235521L, IPConverter.strToNum("192.168.0.1"));
        assertEquals(4294967295L, IPConverter.strToNum("255.255.255.255"));
        assertEquals(204241668L, IPConverter.strToNum("12.44.123.4"));
        assertEquals(0L, IPConverter.strToNum("0.0.0.0"));
    }

    @Test
    void numToStr()
    {
        assertEquals("192.168.0.1", IPConverter.numToStr(3232235521L));
        assertEquals("255.255.255.255", IPConverter.numToStr(4294967295L));
        assertEquals("12.44.123.4", IPConverter.numToStr(204241668L));
        assertEquals("0.0.0.0", IPConverter.numToStr(0L));
    }

    @Test
    void getMask()
    {
        assertEquals(4294967040L, IPConverter.getMask(24));
        assertEquals(4294967295L, IPConverter.getMask(32));
        assertEquals(4294959104L, IPConverter.getMask(19));
        assertEquals(0L, IPConverter.getMask(0));
    }

    @Test
    void getMaskToStr()
    {
        assertEquals("255.255.255.0", IPConverter.getMaskToStr(24));
        assertEquals("255.255.255.255", IPConverter.getMaskToStr(32));
        assertEquals("255.255.224.0", IPConverter.getMaskToStr(19));
        assertEquals("0.0.0.0", IPConverter.getMaskToStr(0));
    }
}