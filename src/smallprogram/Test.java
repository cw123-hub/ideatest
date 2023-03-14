package smallprogram;

public class Test {
    public static void main(String[] args) {
          Person p=new Person("jack",12,'M');

    }

}
class Person{
    private String name; private int age; private char gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this)   {  //是同一个对象（this和obj指向同一个对象）就是相等的）
            return true;
        }else if(obj instanceof Person){  //obj的运行类型是person或者person的子类当然这里person没有子类
            Person p=(Person) obj;//向下转型，因为我需要得到obj的各个属性
            return this.name.equals(p.name) && this.age==p.age && this.gender==p.gender;
        }
        return false;
    }

    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
