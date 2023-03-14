package smallprogram.aaa;

public class InnerClassTest {
    public static void main(String[] args) {
        new CellPhone().alarm(new Bell() {
            @Override
            public void ring() {
                System.out.println("懒猪块起床");
            }
        });
        new CellPhone().alarm(new Bell() {
            @Override
            public void ring() {
                System.out.println("小伙伴们上课了");
            }
        });



    }



}



interface Bell{
    abstract void ring();
}

class CellPhone{
    public void alarm(Bell bell){
        bell.ring();
    }

}
