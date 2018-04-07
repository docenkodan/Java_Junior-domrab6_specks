package specks;

import java.io.IOException;

public class MAIN {

	public static void main(String[] args) throws IOException {
		Specks spk = new Specks();
		spk.default_pos();
		
		/*System.out.println("Вводите значения каждой позиции"
				+ " по очередно (1 строка 1 столбец, 1 строка 2 столбец "
				+ "и т.д.): ");
		spk.add_start_pos();*/
		
		spk.start_pos_3x3();
		spk.print_tab();
		System.out.println();
		
		/*System.out.println("Минимальное количество ходов для решения "
				+ "(при условии, что решения есть): " + spk.min_number_of_steps());*/
		
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
	}
}
