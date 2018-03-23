package specks;

public class MAIN {

	public static void main(String[] args) {
		Specks spk = new Specks();
		spk.default_pos();
		spk.print_tab();
		System.out.println("Вводите значения каждой позиции"
				+ " по очередно (1 строка 1 столбец, 1 строка 2 столбец "
				+ "и т.д.): ");
		spk.add_start_pos();
		spk.print_tab();
		System.out.println("Минимальное количество ходов для решения "
				+ "(при условии, что решения есть): " + spk.min_number_of_steps());
		
		
		
	}

}
