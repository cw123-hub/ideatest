package smallprogram.House.view;

import smallprogram.House.domain.House;
import smallprogram.House.service.houseService;
import smallprogram.House.utils.Utility;

/**
 * ��ʾ����
 * �����û�����
 * ����house service ��ɶԷ�����Ϣ�ĸ��ֲ���
 */
public class HouseView {
    boolean loop= true;
    private char key=' ';//�����û�ѡ��
    private houseService houseService=new houseService(10);

    public  void exit(){
        char c=Utility.readConfirmSelection();
        if(c=='Y'){
            loop=false;
        }
    }
    //show mainMunu
   public void mainMenu(){
       do {
           System.out.println("====================���ݳ���ϵͳ�˵�====================");
           System.out.println("\t\t\t1 �� �� �� Դ");
           System.out.println("\t\t\t2 �� �� �� ��");
           System.out.println("\t\t\t3 ɾ �� �� ��");
           System.out.println("\t\t\t4 �� �� �� �� �� Ϣ");
           System.out.println("\t\t\t5 �� �� �� ��");
           System.out.println("\t\t\t6 ��      ��");
           System.out.println("���������ѡ��1-6");
           key= Utility.readChar();
          switch (key){
              case '1':
                  System.out.println("����");
                  addHouse();
                  break;
              case '2':
                  System.out.println("����");
                  findHouse();
                  break;
              case '3':
                  System.out.println("ɾ��");
                  delHouse();
                  break;
              case '4':
                  System.out.println("�޸�");
                  updateHouse();
                  break;
              case '5':
                  System.out.println("�����б�");
                  ListHouses();
                  break;
              case '6':
                  System.out.println("�˳�");
                  exit();
                  break;
           }

       }while (loop);
   }


   //��дlisthouses��ʾ�����б�
    public void  ListHouses(){
        System.out.println("====================�����б�====================");
        System.out.println("���\t\t����\t\t�绰\t\t��ַ\t\t����\t\t״̬��δ����/�ѳ��⣩");
        House[] houses=houseService.list();//�õ����з�����Ϣ
        for (int i = 0; i < houses.length; i++) {
            if(houses[i]==null){//ruo kong ,ze bu xian shi
                break;
            }
            System.out.println(houses[i].toString());

        }
        System.out.println("===========�����б���ʾ���==================");
    }





      //��дaddhouse�������룬����house���󣬵���add����
    public  void addHouse(){
        System.out.println("=============��ӷ���==============");
        System.out.println("������");
        String name=Utility.readString(8);
        System.out.println("�绰��");
        String phone=Utility.readString(12);
        System.out.println("��ַ��");
        String address= Utility.readString(16);
        System.out.println("���⣺");
        int rent=Utility.readInt();
        System.out.println("��ǰ״̬��");
        String state=Utility.readString(3);
        //�´���һ������
        House house = new House(0, name, phone, address, rent, state);
        if(houseService.add(house)){
            System.out.println("��ӷ��ݳɹ�");
        }
        else{
            System.out.println("��ӷ���ʧ��");
        }
    }



    public void delHouse(){
        System.out.println("============ɾ��������Ϣ===========");
        System.out.println("�������ɾ�����ݵı��(-1��ʾ�˳�)��");
        int delId =Utility.readInt();
        if(delId==-1){
            System.out.println("����ɾ��");return;
        }
       // System.out.println("��ȷ���Ƿ�ɾ����y/n��");
        char choice =Utility.readConfirmSelection();//����ѭ���ж��߼���ֻ������y����n���ܴ�ѭ���г���
        if(choice=='Y'){
            if(houseService.del(delId))
            System.out.println("==========ɾ��������Ϣ�ɹ�===========");
            else
                System.out.println("ɾ��������Ϣ������");

        }else {
            System.out.println("==============����ɾ��==============");
        }


    }


    public void findHouse(){
        System.out.println("============���ҷ���=============");
        System.out.println("������id");
        int findId = Utility.readInt();
        //houseService.findById(findId);
        if(houseService.findById(findId)==null){
            System.out.println("Not Found");return ;
        }
        System.out.println("�ҵ���");
        System.out.println(houseService.findById(findId));

    }




    public void updateHouse(){
        System.out.println("============�޸ķ�����Ϣ=============");
        System.out.println("������id");
        int findId = Utility.readInt();
        if(houseService.findById(findId)==null){
            System.out.println("���ݲ�����");
        }
        House house=houseService.findById(findId);
        System.out.print("����"+"("+ house.getName()+"):");
        String name=Utility.readString(8);
        System.out.print("�绰"+"("+ house.getPhone()+"):");
        String phone=Utility.readString(12);
        System.out.print("��ַ"+"("+ house.getAddress()+"):");
        String address=Utility.readString(16);
        System.out.print("���"+"("+ house.getRent()+"):");
        int rent = Utility.readInt();
        System.out.print("״̬"+"("+ house.getState()+"):");
        String state=Utility.readString(5);
        house.setName(name);
        house.setPhone(phone);
        house.setAddress(address);
        house.setRent(rent);
        house.setState(state);



    }
}
