package specks;

import java.io.*;
import java.util.Scanner;

public class Specks {
	
	public int size = 3;						// Размер поля
	private int[][] tab = new int[size][size];	// Поле
	private int nominal(int i, int j)			// Значение, которое должно быть в точке [i,j]
	{
		return j+size*i+1;
	}
	
	public void print_tab()				// Вывод поля
	{
		for (int i = 0; i<size; i++)
		{
			for (int j = 0; j<size; j++)
			{
				System.out.printf("%3d",tab[i][j]);
			}
			System.out.println();
		}
	}
	
	public void default_pos()			// Позиции решенной игры
	{
		for (int i = 0; i<size; i++)
		{
			for (int j = 0; j<size; j++)
			{
				tab[i][j] = nominal(i,j);
				if ((i+1 == size)&&(j+1 == size))
					tab[i][j] = 0;
			}
		}
	}

	public void start_pos_2x2()			// Ввод начальных позиций для игры 2х2
	{
		tab[0][0] = 3;	tab[0][1] = 1;
		tab[1][0] = 2;	tab[1][1] = 0;
	} 

	public void start_pos_3x3()			// Ввод начальных позиций для игры 3х3
	{
		tab[0][0] = 8;	tab[0][1] = 7;	tab[0][2] = 6;
		tab[1][0] = 3;	tab[1][1] = 5;	tab[1][2] = 1;
		tab[2][0] = 0;	tab[2][1] = 4;	tab[2][2] = 2;
	} 
	
	public void start_pos_4x4()			// Ввод начальных позиций для игры 4х4
	{
		tab[0][0] = 1;	tab[0][1] = 2;	tab[0][2] = 3;	tab[0][3] = 4;
		tab[1][0] = 5;	tab[1][1] = 6;	tab[1][2] = 7;	tab[1][3] = 8;
		tab[2][0] = 9;	tab[2][1] = 10;	tab[2][2] = 11;	tab[2][3] = 12;
		tab[3][0] = 13;	tab[3][1] = 14;	tab[3][2] = 15;	tab[3][3] = 0;
	} 
	
	public void add_start_pos()			// Ввод начальных позиций с клавиатуры
	{
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i<size; i++)
		{
			for (int j = 0; j<size; j++)
			{
				tab[i][j] = scan.nextInt();
			}
		}
	} 
	
	public int min_number_of_steps()	// Вычисление и вывод минимального количества ходов для решения (не правильно работает)
	{
		int i, j, step = 0, steps = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++) 
			{
				if ((tab[i][j] != nominal(i,j))&&(tab[i][j] != 0))
				{
					step = Math.abs(tab[i][j]-(nominal(i,j)));
					while ((step % 3 != 0))
					{
						step-=1;
						steps+=1;
					}
					while (step > 0)
					{
						step-=3;
						steps+=1;
					}
				}
			}
		}
		return steps;
	}
	
	private boolean win_game ()			// Игра решена (проверка)
	{
		int k = 0;
		for (int i = 0; i<size; i++)
		{
			for (int j = 0; j<size; j++)
			{
				if ((tab[i][j] == nominal(i,j))||((i+1 == size)&&(j+1 == size)&&(tab[i][j] == 0)))
				k++;
			}
		}
		if (k == size*size)
			return true;
		else 
			return false;
	}
	
	private boolean progress_game_3x3 ()// Еще одна проверка. Нужна для решения 3х3 (не используется)
	{
		if ((tab[0][0] == 1)&&(tab[0][1] == 2)&&(tab[0][2] == 3)&&
				(tab[1][0] == 7)&&(tab[1][1] == 0)&&(tab[1][2] == 4)&&
				(tab[2][0] == 8)&&(tab[2][1] == 6)&&(tab[2][2] == 5))
			return true;
		else
			return false;
	}
	
	private void change(int i1, int j1, int i0, int j0)							// Перемещение ячейки в пустою ячейку [i0, j0]
	{
		int changer = 0;
		changer = tab[i1][j1];
		tab[i1][j1] = tab[i0][j0];
		tab[i0][j0] = changer;
	}
	
	private void double_change(int i0, int j0, int i1, int j1, int i2, int j2)	// Оборот ячеек по/против часовой ([i1,j1] движется первой) с той-же пустой ячейкой как в начале так и в конце
	{
		change(i1, j1, i0, j0);
		if ((i1 == i0)&&(j2 == j0))
		{
			change(i2, j1, i1, j1);
			change(i2, j2, i2, j1);
			change(i0, j0, i2, j2);
		}
		else
		{
			change(i1, j2, i1, j1);
			change(i2, j2, i1, j2);
			change(i0, j0, i2, j2);
		}
	}
	
	public void decision_2x2 ()			//Решение для 2х2
	{
		if (tab[0][0] == 0)
			change(0, 0, 0, 1);
		if (tab[0][1] == 0)
			change(0, 1, 1, 1);
		if (tab[1][0] == 0)
			change(1, 0, 1, 1);
		
		while (!win_game())
		{
			double_change(1,1,0,1,1,0);
		}
	}
	
	/*
	 * Решение 3х3 состоит в том, чтобы поставить все числа по порядку по кругу по часовой, 
	 * но 7 и 8 не по порядку, 
	 * в центре всегда пустая ячейка
	 * А после из этого положения привести головоломку к собранному виду
	*/
	
	public void decision_3x3_0 ()		//1 часть решения 3х3 (ставим пустую ячейку в центр)
	{
		int i; int j; int i0 = 0; int j0 = 0;
		// Находим "0"
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++)
			{
				if (tab[i][j] == 0)
				{
					i0 = i; j0 = j;
				}
			}
		}
		// Перемещаем 0 в центр
		if ((i0 != 1)||(j0 != 1))
			if (i0 == 1)
			{
				change(i0, j0, i0, 1);
				j0 = 1;
			}
			else
				if (j0 == 1)
				{
					change(i0, j0, 1, j0);
					i0 = 1;
				}
				else
					{
						change(i0, j0, i0, 1);
						j0 = 1;
						change(i0, j0, 1, j0);
						i0 = 1;
					}	
	}
	
	public void decision_3x3_1 ()		//2 часть решения 3х3 (ставим 1 на место)
	{
		int i; int j; int i0 = 1; int j0 = 1; 
		int k = 1; int ik = 0; int jk = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++)
			{
				if (tab[i][j] == k)
				{
					ik = i; jk = j;
				}
			}
		}
		if ((ik != 0)||(jk != 0))
		{
			if ((ik == 2)&&(jk == 0))
			{
				double_change(i0, j0, ik-1, jk, ik, jk+1);
				ik = 1; jk = 0;
			}
			if ((ik == 1)&&(jk == 0))
			{
				double_change(i0, j0, ik, jk, ik+1, jk+1);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 2))
			{
				double_change(i0, j0, ik, jk-1, ik-1, jk);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 1))
			{
				double_change(i0, j0, ik, jk, ik-1, jk+1);
				ik = 1; jk = 2;
			}
			if ((ik == 0)&&(jk == 2))
			{
				double_change(i0, j0, ik+1, jk, ik, jk-1);
				ik = 1; jk = 2;
			}
			if ((ik == 1)&&(jk == 2))
			{
				double_change(i0, j0, ik, jk, ik-1, jk-1);
				ik = 0; jk = 1;
			}
			if ((ik == 0)&&(jk == 1))
				double_change(i0, j0, ik+1, jk-1, ik, jk);
		}
		
	}
	
	public void decision_3x3_2 ()		//3 часть решения 3х3 (ставим 2 на место)
	{
		// 2
		int i; int j; int i0 = 1; int j0 = 1; 
		int k = 2; int ik = 0; int jk = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++)
			{
				if (tab[i][j] == k)
				{
					ik = i; jk = j;
				}
			}
		}
		if ((ik != 0)||(jk != 1))
		{
			if ((ik == 2)&&(jk == 0))
			{
				double_change(i0, j0, ik-1, jk, ik, jk+1);
				ik = 1; jk = 0;
			}
			if ((ik == 1)&&(jk == 0))
			{
				double_change(i0, j0, ik, jk, ik+1, jk+1);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 2))
			{
				double_change(i0, j0, ik, jk-1, ik-1, jk);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 1))
			{
				double_change(i0, j0, ik, jk, ik-1, jk+1);
				ik = 1; jk = 2;
			}
			if ((ik == 0)&&(jk == 2))
			{
				double_change(i0, j0, ik+1, jk, ik, jk-1);
				ik = 1; jk = 2;
			}
			if ((ik == 1)&&(jk == 2))
			{
				double_change(i0, j0, ik, jk, ik-1, jk-1);
				ik = 0; jk = 1;
			}
		}
	}
	
	public void decision_3x3_3 ()		//4 часть решения 3х3 (ставим 3 на место)
	{
		// 3
		int i; int j; int i0 = 1; int j0 = 1; 
		int k = 3; int ik = 0; int jk = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++)
			{
				if (tab[i][j] == k)
				{
					ik = i; jk = j;
				}
			}
		}
		if ((ik != 0)||(jk != 2))
		{
			if ((ik == 2)&&(jk == 0))
			{
				double_change(i0, j0, ik-1, jk, ik, jk+1);
				ik = 1; jk = 0;
			}
			if ((ik == 1)&&(jk == 0))
			{
				double_change(i0, j0, ik, jk, ik+1, jk+1);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 2))
			{
				double_change(i0, j0, ik, jk-1, ik-1, jk);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 1))
			{
				double_change(i0, j0, ik, jk, ik-1, jk+1);
				ik = 1; jk = 2;
			}
			if ((ik == 1)&&(jk == 2))
			{
				change(1,0,i0,j0);
				change(0,0,1,0);
				change(0,1,0,0);
				change(0,2,0,1);
				change(1,2,0,2);
				change(1,1,1,2);
				change(0,1,1,1);
				change(0,0,0,1);
				change(1,0,0,0);
				change(1,1,1,0);
				ik = 1; jk = 2;
			}
		}
	}
	
	public void decision_3x3_4 ()		//5 часть решения 3х3 (ставим 4 на место)
	{
		// 4
		int i; int j; int i0 = 1; int j0 = 1; 
		int k = 4; int ik = 0; int jk = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++)
			{
				if (tab[i][j] == k)
				{
					ik = i; jk = j;
				}
			}
		}
		if ((ik != 1)||(jk != 2))
		{
			if ((ik == 2)&&(jk == 0))
			{
				double_change(i0, j0, ik-1, jk, ik, jk+1);
				ik = 1; jk = 0;
			}
			if ((ik == 1)&&(jk == 0))
			{
				double_change(i0, j0, ik, jk, ik+1, jk+1);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 2))
			{
				double_change(i0, j0, ik, jk-1, ik-1, jk);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 1))
			{
				double_change(i0, j0, ik, jk, ik-1, jk+1);
				ik = 1; jk = 2;
			}
		}
	}
	
	public void decision_3x3_5 ()		//6 часть решения 3х3 (ставим 5 на место)
	{
		// 5
		int i; int j; int i0 = 1; int j0 = 1; 
		int k = 5; int ik = 0; int jk = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++)
			{
				if (tab[i][j] == k)
				{
					ik = i; jk = j;
				}
			}
		}
		if ((ik != 2)||(jk != 2))
		{
			if ((ik == 2)&&(jk == 0))
			{
				double_change(i0, j0, ik-1, jk, ik, jk+1);
				ik = 1; jk = 0;
			}
			if ((ik == 1)&&(jk == 0))
			{
				double_change(i0, j0, ik, jk, ik+1, jk+1);
				ik = 2; jk = 1;
			}
			if ((ik == 2)&&(jk == 1))
			{
				change(1,0,1,1);
				change(2,0,1,0);
				change(2,1,2,0);
				change(2,2,2,1);
				change(1,2,2,2);
				change(1,1,1,2);
				double_change(i0, j0, 2, 1, 1, 0);
				change(1,2,1,1);
				change(2,2,1,2);
				change(2,1,2,2);
				change(1,1,2,1);
			}
		}
	}
	
	public void decision_3x3_6 ()		//7 часть решения 3х3 (ставим 6, 7 и 8 на место)
	{
		// 6-7-8
		int i; int j; int i0 = 1; int j0 = 1; 
		int k = 6; int ik = 0; int jk = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++)
			{
				if (tab[i][j] == k)
				{
					ik = i; jk = j;
				}
			}
		}
		if ((ik != 2)&&(jk != 1))
		{
			if ((ik == 2)&&(jk == 0))
			{
				double_change(i0, j0, ik+1, jk, ik, jk+1);
				ik = 1; jk = 0;
			}
			if ((ik == 1)&&(jk == 0))
			{
				double_change(i0, j0, ik, jk, ik+1, jk+1);
				ik = 2; jk = 1;
			}
		}
	}
	
	public void decision_3x3_7 ()		//8 часть решения 3х3 (преобразуем головоломку к собранному виду)
	{
		change(1, 2, 1, 1);
		change(2, 2, 1, 2);
		change(2, 1, 2, 2);
		change(2, 0, 2, 1);
		change(1, 0, 2, 0);
		change(1, 1, 1, 0);
		change(1, 2, 1, 1);
		change(2, 2, 1, 2);
	}
}
