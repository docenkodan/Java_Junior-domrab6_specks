package specks;

import java.io.IOException;
import java.util.Scanner;

public class MAIN {

	public static void main(String[] args) throws IOException {
		int size = 3;				// При значении 3 будет запускаться игра 3х3, при значении 2 соответственно 2х2
		Specks spk = new Specks();
		spk.size = size;
		//spk.default_pos();
		/*System.out.println("Вводите значения каждой позиции"
				+ " по очередно (1 строка 1 столбец, 1 строка 2 столбец "
				+ "и т.д.): ");
		spk.add_start_pos();*/
		
		/*System.out.println("Минимальное количество ходов для решения "
		+ "(при условии, что решения есть): " + spk.min_number_of_steps());*/
		if (size == 3)
		{
			spk.start_pos_3x3();// Для задания начальных значений игры изменить этот класс
			spk.print_tab();
			System.out.println();
			
			spk.decision_3x3_0();
			spk.print_tab();
			System.out.println();
			
			spk.decision_3x3_1();
			spk.print_tab();
			System.out.println();

			spk.decision_3x3_2();
			spk.print_tab();
			System.out.println();
			
			spk.decision_3x3_3();
			spk.print_tab();
			System.out.println();
			
			spk.decision_3x3_4();
			spk.print_tab();
			System.out.println();
			
			spk.decision_3x3_5();
			spk.print_tab();
			System.out.println();

			spk.decision_3x3_6();
			spk.print_tab();
			System.out.println();
			
			spk.decision_3x3_7();
			spk.print_tab();
			System.out.println();
		}else
		{
			spk.start_pos_2x2();// Для задания начальных значений изменить этот класс
			spk.print_tab();
			System.out.println();
			
			spk.decision_2x2();
			spk.print_tab();
			System.out.println();
		}
	}
}
