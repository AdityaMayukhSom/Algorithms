import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

public final class FastReader
{
    private StringTokenizer s;
    private final BufferedReader b;

    public FastReader() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
        this.b = new BufferedReader(isr);
    }

    public FastReader(final String filename) throws IOException
    {
        FileReader fr = new FileReader(filename);
        this.b = new BufferedReader(fr);
    }

    private String next()
    {
        while (s == null || !s.hasMoreElements())
        {
            try
            {
                s = new StringTokenizer(b.readLine());
            } catch (IOException ioe)
            {
                System.out.println(
                        "Trouble reading from the file: " + ioe.getMessage());
                // ioe.printStackTrace();
            }
        }

        return s.nextToken();
    }

    public int nextInt()
    {
        return Integer.parseInt(next());
    }

    public long nextLong()
    {
        return Long.parseLong(next());
    }

    public double nextDouble()
    {
        return Double.parseDouble(next());
    }

    public String nextLine()
    {
        String str = "";

        try
        {
            if (s.hasMoreTokens())
            {
                str = s.nextToken("\n");
            } else
            {
                str = b.readLine();
            }
        } catch (IOException ioe)
        {
            System.out.println(
                    "Trouble reading from the file: " + ioe.getMessage());
            // ioe.printStackTrace();
        }

        return str;
    }
}
