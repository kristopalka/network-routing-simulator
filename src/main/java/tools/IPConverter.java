package tools;

import java.security.InvalidParameterException;
import java.util.regex.Pattern;

public abstract class IPConverter
{
    public static long strToNum(String ip) throws InvalidParameterException
    {
        String[] numbers = ip.split(Pattern.quote("."));

        if(numbers.length != 4) { throw new InvalidParameterException(); }

        long a = Long.parseLong(numbers[0]);
        long b = Long.parseLong(numbers[1]);
        long c = Long.parseLong(numbers[2]);
        long d = Long.parseLong(numbers[3]);

        if(a<0 || a>255 || b<0 || b>255 || c<0 || c>255 || d<0 || d>255) { throw new InvalidParameterException(); }

        long res = (a<<24) + (b<<16) + (c<<8) + d;

        return res;
    }

    public static String numToStr(long ip) throws InvalidParameterException
    {
        if(ip > 4294967295L || ip < 0)  { throw new InvalidParameterException(); }

        long a = (ip &4278190080L)>>24;
        long b = (ip &16711680)>>16;
        long c = (ip &65280)>>8;
        long d = (ip &255);

        String res = a + "." + b + "." + c + "." + d;
        return res;
    }

    public static long getMask(int length)
    {
        long mask = 0L;

        for(int i=31; i>=32-length ; i--)
        {
            //System.out.println(Long.toBinaryString(mask));
            mask = mask|(1L<<i);
        }
        return mask;
    }


    public static String getMaskToStr(int length)
    {
        return numToStr(getMask(length));
    }
}
