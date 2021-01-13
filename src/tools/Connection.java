package tools;

public class Connection
{
    public int r1;
    public String s1;
    public int r2;
    public String s2;

    public Connection(int r1, String s1, int r2, String s2)
    {
        this.r1 = r1;
        this.s1 = s1;
        this.r2 = r2;
        this.s2 = s2;
    }

    @Override
    public String toString()
    {
        return r1 + "." + s1 + "-" + r2 + "." + s2;
    }
}
