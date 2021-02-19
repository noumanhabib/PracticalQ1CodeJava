
public class Collection {
	private int id;
	private String name;
	private char gender;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getInvesment() {
		return invesment;
	}

	public void setInvesment(int invesment) {
		this.invesment = invesment;
	}

	private int age;
	private int invesment;
	
	public Collection(int id, String name, char gender, int age, int invesment) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.invesment = invesment;
	}
	
}
