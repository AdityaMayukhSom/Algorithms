import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.stream.IntStream;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;

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
                System.out.println("Trouble reading from the file: " + ioe.getMessage());
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

    public static boolean canPartition(int[] arr, int n)
    {
        int sum = IntStream.of(arr).sum();

        if ((sum & 1) == 1)
        {
            return false;
        }

        int t = sum / 2;

        int[] prev = new int[t + 1];
        int[] curr = new int[t + 1];
        int[] temp;

        for (int i = 0; i <= t; ++i)
        {
            prev[i] = ((i < n) && (arr[i] == i)) ? 1 : 0;
        }

        prev[0] = 1;
        curr[0] = 1;

        for (int i = 1; i < n; ++i)
        {
            for (int j = 1; j <= t; ++j)
            {
                int tk = 0;

                if (j >= arr[i])
                {
                    tk = prev[j - arr[i]];
                }

                int nt = prev[j];
                curr[j] = (tk | nt);
            }

            temp = prev;
            prev = curr;
            curr = temp;
        }

        return (prev[t] == 1);
    }

    public static void main(String[] args)
    {
        try
        {
            fr = new FastReader("./in.txt");
            pw = new PrintWriter("./op.txt");

            int t = fr.nextInt();

            while (t-- > 0)
            {
                int n = fr.nextInt();
                int[] arr = new int[n];

                for (int i = 0; i < n; ++i)
                {
                    arr[i] = fr.nextInt();
                }

                boolean ans = canPartition(arr, n);
                pw.println(ans);
            }
        }
        catch (Exception e)
        {
            System.err.println("Some Error Occured: " + e.getMessage());
        }
        finally
        {
            try
            {
                pw.flush();
                pw.close();
            }
            catch (Exception e)
            {
                System.err.println("Some Error Occured: " + e.getMessage());
            }
        }
    }
}
