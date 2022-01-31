package TRDZ.tasks;

public class Initialization {

	private static final String[] names = {"Александр","Алексей","Борис","Владимир","Владислав","Всеволод","Григорий","Константин","Иван","Петр"};
	private static final String[] cames = {"Филя","Персид","Барсик","Тоторо","Васька","Люций","Гаврил","Рото","Горо","Тор"};
	protected static participants[] Runner; 	//Аналог - Object[] Runner
	protected static blocks[] Road;				//Аналог - Object[] Road;

	public static void main(String[] args) {
	//region Обьявление бегунов и маршрута
		Runner = new participants[10];
		Road = new blocks[5];
		System.out.print("В забеге участвуют... ");
		int Runners_human= (int) (Math.random()*10);
		System.out.print("человек:"+Runners_human+", ");
		int Runners_animal= (int) (Math.random()*(10-Runners_human));
		System.out.print("котов:"+Runners_animal+", ");
		int Runners_robot=10-Runners_human-Runners_animal;
		System.out.println("роботов:"+Runners_robot+".");
		Runners_animal+=Runners_human;
		Runners_robot+=Runners_human+Runners_animal;
		for (int i = 0; i < Runner.length; i++) {
			Runner[i] = (Runners_human>0) ? ( new Runner_Human (names[i],(int)(300+Math.random()*47000),((int)(30+Math.random()*170)))) :
						//Люди могут пробежать от 300метров до 50 километров и прыгнуть от 300 см до 2 метров
						((Runners_animal>0) ? (new Runner_Cat (cames[i],(int)(100+Math.random()*6900),((int)(30+Math.random()*120)))) :
						//Коты могут пробежать от 100метров до 7 километров и прыгнуть от 300 см до 1.5 метров
											(new Runner_Robot (("MKD32"+i),(int)(3000+Math.random()*197000),((int)(Math.random()*50)))));
						//Роботы могут пробежать от 3 до 200 километров но прыгают от 0 до 500 см
			Runners_human--;
			Runners_animal--;
			Runners_robot--;
			}
		for (int i = 0; i < Road.length; i++) {
			Road[i] = ((int) (Math.random()*2)>0) ? ( new Road_up((int)(10+Math.random()*190))) : (new Road_forward((int)(10+Math.random()*10000)));
			//На фрагменте пути нужно либо пробежать от 10м до 10км лиюо перепрыгнуть препятствие высотой от 100см до 2м
			}
		get_road();
	//endregion
		System.out.println("");
	//region Прохождение маршрута
		for (int i=0; i<Road.length; i++){
			System.out.print("\nЭтап "+(i+1));
			if (Road[i] instanceof Road_forward) {
				System.out.println(" - Пробежать "+Road[i].get_Length()+"м");
				for (participants runner : Runner) {
					//Аналог  (Элемент instanceof Класс)  ((Клас) Элемент).run(...);
					runner.run(Road[i].get_Length());
					}
				}
			else {
				System.out.println(" - Перепрыгнуть препятсвие высоой в "+Road[i].get_Length()+"см");
				for (participants runner : Runner) {
					//Аналог  (Элемент instanceof Класс)  ((Клас) Элемент).jump(...);
					runner.jump(Road[i].get_Length());
					}
				}
			}
	//endregion
	//region Вывод результатов
		System.out.print("\nПобедители: ");
		for (participants participants : Runner) {
			if (participants.isLose()) System.out.print(participants.get_Name()+"; ");
			}
		System.out.println("\n\nСошли с дистанции: ");
		for (participants participants : Runner) {
			if (!participants.isLose()) System.out.println(participants.get_Fail());
			}
	//endregion
		}

	/** Выводит полученый маршрут участников
	 */
	public static void get_road() {
		System.out.print("Маршрут: ");
		for (blocks obj : Road){
		System.out.print(" -> ");
		if (obj instanceof Road_up) {System.out.print("Прыгнуть выше "+obj.get_Length()+" см");}
		if (obj instanceof Road_forward) {System.out.print("Пробежать "+obj.get_Length()+" м");}
		}

	}

	}



interface participants {
	void refresh();
	void jump(int number);
	void run(int number);
	boolean isLose();
	String get_Name();
	String get_Fail();
	}

interface blocks {
	int get_Length();
	}