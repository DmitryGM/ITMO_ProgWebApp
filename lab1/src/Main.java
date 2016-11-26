import fff.sss.*;

import java.lang.*;
import java.lang.Math.*;


class Main
{
	public static void main(String[] args)
	{
		try
		{
			if(args.length != 2) throw new Exception("Not good");

			String s1 = args[0];
			String s2 = args[1];

			char[] chArray1 = s1.toCharArray();
			char[] chArray2 = s2.toCharArray();

			int n1, n2;

			n1 = chArray1.length;
			n2 = chArray2.length;

			//Динамическое программирование:
			int[][] matrix;
			matrix = new int[n1 + 1][n2 + 1]; //Массив инициализируется нулями

			for(int i = 1; i <= n1; i++)
			{
				for(int j = 1; j <= n2; j++)
				{
					if(chArray1[i - 1] == chArray2[j - 1])
					{
						matrix[i][j] = matrix[i - 1][j - 1] + 1;
					}
					else
					{
						matrix[i][j] = matrix[i - 1][j] > matrix[i][j - 1] ? matrix[i - 1][j] : matrix[i][j - 1];
					}
				}
			}

			//Восстановление ответа:
			int leng = matrix[n1][n2];

			char[] ans = new char[leng];

			int i = n1, j = n2;
			int k = leng - 1;

			while(i > 0 && j > 0)
			{
				while (i > 0 && matrix[i][j] == matrix[i - 1][j]) i--;
				while (j > 0 && matrix[i][j] == matrix[i][j - 1]) j--;

				if(i > 0 && j > 0) //&& matrix[i][j] == matrix[i - 1][j - 1] + 1)
				{
					ans[k--] = chArray1[i - 1];

					i--;
					j--;
				}
			}

			String ans_string = new String(ans); //Собираем строку из массива char через конструкто строки

			//Распознана строка:
			System.out.println("length of number = " + ans_string.length());
			System.out.println(ans_string); //if empty - all ok

			try
			{				
				int number = new Integer(ans_string);

				//System.out.println((int)Math.pow((double)number, 2));
				System.out.println((int)EmptyClass.action(number));

			}
			catch(NumberFormatException ex_number)
			{
				System.out.println("Sorry, but your argument isn't numeric!");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Sorry, but your argument is not correct!");
		}
	}
}
