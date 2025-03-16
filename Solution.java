import java.io.PrintWriter;
import java.util.stream.IntStream;

public class Solution
{
	private static final String IN_FILE_PATH = "./in.txt";
	private static final String OP_FILE_PATH = "./op.txt";

	private static final FastReader fr;
	private static final PrintWriter pw;

	static
	{
		try
		{
			fr = new FastReader(IN_FILE_PATH);
			pw = new PrintWriter(OP_FILE_PATH);
		} catch (Exception e)
		{
			throw new ExceptionInInitializerError(e);
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
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			pw.flush();
			pw.close();
		}
	}
}
