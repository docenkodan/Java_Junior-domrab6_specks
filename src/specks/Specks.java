package specks;

import java.io.*;
import java.util.Scanner;

public class Specks {
	
	private int size = 3;						//Размер поля
	private int[][] tab = new int[size][size];	//Поле
	
	public void default_pos()			//Позиции решенной игры
	{
		for (int i = 0; i<size; i++)
		{
			for (int j = 0; j<size; j++)
			{
				tab[i][j] = j+size*i+1;
				if ((i+1 == size)&&(j+1 == size))
					tab[i][j] = 0;
			}
		}
	}
	
	public void print_tab()				//Вывод поля
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
	
	public void add_start_pos()		//Ввод начальных позиций
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
	
	public int min_number_of_steps()	//Вычисление и вывод минимального количества ходов для решения
	{
		int i, j, step = 0, steps = 0;
		for (i = 0; i<size; i++)
		{
			for (j = 0; j<size; j++) 
			{
				if ((tab[i][j] != j+size*i+1)&&(tab[i][j] != 0))
				{
					step = Math.abs(tab[i][j]-(j+size*i+1));
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
	
	public void change(int i1, int j1, int i2,int j2)//Смена местами двух ячеек
	{
		int changer = 0;
		changer = tab[i1][j1];
		tab[i1][j1] = tab[i2][j2];
		tab[i2][j2] = changer;
	}
	
	public void decision ()
	{
		
		
	}
	
}
