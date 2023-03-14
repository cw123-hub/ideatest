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
        if (obj==this)   {  //��ͬһ������this��objָ��ͬһ�����󣩾�����ȵģ�
            return true;
        }else if(obj instanceof Person){  //obj������������person����person�����൱Ȼ����personû������
            Person p=(Person) obj;//����ת�ͣ���Ϊ����Ҫ�õ�obj�ĸ�������
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
