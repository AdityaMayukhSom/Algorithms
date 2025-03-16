import java.io.Serializable;

public class Trio implements Serializable 
{
    public int first;
    public int second;
    public int third;

    public Trio(final int first, final int second, final int third) 
    {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public String toString() 
    {
        return String.format(
            "Trio (first: %d, second: %d, third: %d)", 
            this.first, 
            this.second, 
            this.third
        );
    }

    @Override
    public boolean equals(Object ob) 
    {
        if (ob == null) 
        {
            return false;
        }

        if (ob.getClass() != this.getClass()) 
        {
            return false;
        }

        Trio t = (Trio) ob;

        return t.first == this.first 
            && t.second == this.second 
            && t.third == this.third;
    }
}
