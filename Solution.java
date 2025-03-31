import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UncheckedIOException;

record Pair(int first, int second)
{
}

record Trio(int first, int second, int third)
{
}

final class FastReader
{
    private StringTokenizer s;
    private final BufferedReader b;

    public FastReader()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(System.in, "UTF-8");
            this.b = new BufferedReader(isr);

        }
        catch (IOException ioe)
        {
            throw new UncheckedIOException(ioe);
        }
    }

    public FastReader(final String filename)
    {
        try
        {
            FileReader fr = new FileReader(filename);
            this.b = new BufferedReader(fr);
        }
        catch (IOException ioe)
        {
            throw new UncheckedIOException(ioe);
        }
    }

    private String next()
    {
        while (s == null || !s.hasMoreElements())
        {
            try
            {
                s = new StringTokenizer(b.readLine());
            }
            catch (IOException ioe)
            {
                System.out.println(
                    "Trouble reading from the file: " + ioe.getMessage()
                );
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
            }
            else
            {
                str = b.readLine();
            }
        }
        catch (IOException ioe)
        {
            System.out.println("Trouble reading from file: " + ioe.getMessage());
        }

        return str;
    }
}

public class Solution
{
    private static FastReader fr;
    private static PrintWriter pw;

    public static int findWays(final int num[], final int tar) 
    {
        return 0;
    }

    public static void main(String[] args) throws Exception
    {
        fr = new FastReader("./in.txt");
        pw = new PrintWriter("./op.txt");

        try
        {
            // int t = fr.nextInt();

            // while (t-- > 0)
            // {
            int n = fr.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; ++i)
            {
                arr[i] = fr.nextInt();
            }

            // whatever change you're doing in function 
            // calling do that here
            // int ans = minSubsetSumDifference(arr, n);

            // pw.println(ans);
            // }
        }
        catch (Exception e)
        {
            System.err.println("Some Error Occured: " + e.getMessage());
        }
        finally
        {
            pw.flush();
            pw.close();
        }
    }
}
