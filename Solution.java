
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.*;
import java.io.InputStream;
import java.io.PrintWriter;

public class Solution 
{
	private static record Pair<U, V>(U first, V second) {}
	private static record Trio<U, V, W>(U first, V second, W third) {}

	private static final String IN_FILE_PATH = "./in.txt";
	private static final String OP_FILE_PATH = "./op.txt";

	private static FastReader fr;
	private static PrintWriter pw ;

	static 
	{
		try
		{
			fr = new FastReader(IN_FILE_PATH); 
			pw = new PrintWriter(OP_FILE_PATH);
		}
		catch (Exception e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

    public static final class FastReader 
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
				if(s.hasMoreTokens())
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
				System.out.println("Trouble reading from the file: " + ioe.getMessage());
				// ioe.printStackTrace();
			}

			return str;
		}
    }

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
			int t = fr.nextInt();

			while(t-- > 0)
			{
				int n = fr.nextInt();
				int[] arr = new int[n];

				for(int i = 0; i < n; ++i)
				{
					arr[i] = fr.nextInt();
				}

				boolean ans = canPartition(arr, n);
				pw.println(ans);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			pw.flush();
			pw.close();
		}	
	}
}
