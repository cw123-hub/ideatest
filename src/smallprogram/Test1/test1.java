package smallprogram.Test1;

public class test1 {
    public static void main(String[] args) {
new  normal(8000,12).print();
    }

}



class  worker {
   private double sal;
   private int month;
  public  void print(){
      System.out.println(sal*month);
  }

    public worker(double sal, int month) {
        this.sal = sal;
        this.month = month;
    }

    public double getSal() {
        return sal;
    }

    public int getMonth() {
        return month;
    }
}
class teacher extends worker{
    private  double classDay;
    private  double classSal;

    public teacher(double sal, int month, double classDay, double classSal) {
        super(sal, month);
        this.classDay = classDay;
        this.classSal = classSal;
    }

    @Override
    public void print() {
        System.out.println("teacher:");
        System.out.println(super.getSal()*getMonth()+classSal*classDay);
    }
}

class  normal extends worker{
    public normal(double sal, int month) {
        super(sal, month);
    }

    @Override
    public void print() {
        System.out.println("π§»À");
        super.print();
    }
}
