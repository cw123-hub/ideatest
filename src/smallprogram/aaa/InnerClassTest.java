package smallprogram.aaa;

public class InnerClassTest {
    public static void main(String[] args) {
        new CellPhone().alarm(new Bell() {
            @Override
            public void ring() {
                System.out.println("�������");
            }
        });
        new CellPhone().alarm(new Bell() {
            @Override
            public void ring() {
                System.out.println("С������Ͽ���");
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
