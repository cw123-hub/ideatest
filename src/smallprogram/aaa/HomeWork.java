package smallprogram.aaa;

public class HomeWork {
    public static void main(String[] args) {
        Person[] person = new Person[4];
        person[2] = new Teacher("LY", 'M', 44, 11);
        person[3] = new Teacher("LW", 'F', 46, 22);
        person[0] = new Student("CW", 'M', 22, "181310609");
        person[1] = new Student("FM", 'M', 21, "181310625");

        for (int i = 0; i < person.length - 1; i++) {
            for (int j = 0; j < person.length - i - 1; j++) {
                if (person[j].getAge() > person[j + 1].getAge()) {
                    Person p = person[j];
                    person[j] = person[j + 1];
                    person[j + 1] = p;
                }
            }
        }
       for(int i=0;i<4;i++){
           //System.out.println();
           person[i].play();
       }
    }
}
class Person {
    private String name;
    private char sex;
    private int age;

    public void play() {
        System.out.print(name + "爱玩");
    }

    public Person(String name, char sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

class Student extends Person {
    public Student(String name, char sex, int age, String stu_id) {
        super(name, sex, age);
        this.stu_id = stu_id;
    }

    private String stu_id;

    public void study() {
        System.out.println("我承诺，我会好好学习");
    }

    @Override
    public void play() {
        super.play();
        System.out.println("足球");
    }
}



class Teacher extends Person{
    private  int  work_age;

    public Teacher(String name, char sex, int age, int work_age) {
        super(name, sex, age);
        this.work_age = work_age;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("想起");
    }
}
