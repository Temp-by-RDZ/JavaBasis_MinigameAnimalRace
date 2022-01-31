package TRDZ.tasks;

public class Runner_Cat implements participants{
	private final String name;
	private final int jump_power;
	private final int max_stamina;
	private boolean join;
	private int stamina;
	private int stage;

	Runner_Cat (String name, int stamina, int jump) {
		join=true;
		this.name = name;
		max_stamina = stamina;
		jump_power = jump*10;
		this.stamina = stamina;
		}

	/** Востановление выносливости участника
	 */
	public void refresh() {
		System.out.println("Кот "+name+"отдохнул и востановил силы");
		stamina = max_stamina;
		}

	/** Обработка прыжка участника
	 */
	public void jump(int number){
		if (!join) {return;}
		stage++;
		if (jump_power<=number) {
			System.out.println("Кот "+name+" не справился с препятсвием и сошел с гонги.");
			join=false;
			}
		else System.out.println("Кот "+name+" перепрыгнул препятсвие");
		}

	/** Обработка прохождения дистанции участника
	 */
	public void run(int number){
		if (!join) {return;}
		stage++;
		stamina-=number;
		if (stamina<=0) {
			System.out.println("Кот "+name+" выдохся и сошел с гонги.");
			join=false;
			}
		else System.out.println("Кот "+name+" пробежал дистанцию");
		}

	/** Проверка результата участника
	 * @return true / false
	 */
	public boolean isLose() {return join;}

	/** Возвращает имя участника
	 */
	public String get_Name() {return ("Кот "+name);}

	/** Возвращает причину поражения участника
	 */
	public String get_Fail() {return ("Кот "+name+" не справился с препятсвием №"+stage);}
	}
