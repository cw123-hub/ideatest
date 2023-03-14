package smallprogram.House.view;

import smallprogram.House.domain.House;
import smallprogram.House.service.houseService;
import smallprogram.House.utils.Utility;

/**
 * 显示界面
 * 接受用户输入
 * 调用house service 完成对房屋信息的各种操作
 */
public class HouseView {
    boolean loop= true;
    private char key=' ';//接收用户选择
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
           System.out.println("====================房屋出租系统菜单====================");
           System.out.println("\t\t\t1 新 增 房 源");
           System.out.println("\t\t\t2 查 找 房 屋");
           System.out.println("\t\t\t3 删 除 房 屋");
           System.out.println("\t\t\t4 修 改 房 屋 信 息");
           System.out.println("\t\t\t5 房 屋 列 表");
           System.out.println("\t\t\t6 退      出");
           System.out.println("请输入你的选择：1-6");
           key= Utility.readChar();
          switch (key){
              case '1':
                  System.out.println("新增");
                  addHouse();
                  break;
              case '2':
                  System.out.println("查找");
                  findHouse();
                  break;
              case '3':
                  System.out.println("删除");
                  delHouse();
                  break;
              case '4':
                  System.out.println("修改");
                  updateHouse();
                  break;
              case '5':
                  System.out.println("房屋列表");
                  ListHouses();
                  break;
              case '6':
                  System.out.println("退出");
                  exit();
                  break;
           }

       }while (loop);
   }


   //编写listhouses显示房屋列表
    public void  ListHouses(){
        System.out.println("====================房屋列表====================");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（未出租/已出租）");
        House[] houses=houseService.list();//得到所有房屋信息
        for (int i = 0; i < houses.length; i++) {
            if(houses[i]==null){//ruo kong ,ze bu xian shi
                break;
            }
            System.out.println(houses[i].toString());

        }
        System.out.println("===========房屋列表显示完毕==================");
    }





      //编写addhouse接受输入，创建house对象，调用add方法
    public  void addHouse(){
        System.out.println("=============添加房屋==============");
        System.out.println("姓名：");
        String name=Utility.readString(8);
        System.out.println("电话：");
        String phone=Utility.readString(12);
        System.out.println("地址：");
        String address= Utility.readString(16);
        System.out.println("月租：");
        int rent=Utility.readInt();
        System.out.println("当前状态：");
        String state=Utility.readString(3);
        //新创建一个房子
        House house = new House(0, name, phone, address, rent, state);
        if(houseService.add(house)){
            System.out.println("添加房屋成功");
        }
        else{
            System.out.println("添加房屋失败");
        }
    }



    public void delHouse(){
        System.out.println("============删除房屋信息===========");
        System.out.println("请输入待删除房屋的编号(-1表示退出)：");
        int delId =Utility.readInt();
        if(delId==-1){
            System.out.println("放弃删除");return;
        }
       // System.out.println("请确认是否删除（y/n）");
        char choice =Utility.readConfirmSelection();//含有循环判断逻辑，只有输入y或者n才能从循环中出来
        if(choice=='Y'){
            if(houseService.del(delId))
            System.out.println("==========删除房屋信息成功===========");
            else
                System.out.println("删除房屋信息不存在");

        }else {
            System.out.println("==============放弃删除==============");
        }


    }


    public void findHouse(){
        System.out.println("============查找房屋=============");
        System.out.println("请输入id");
        int findId = Utility.readInt();
        //houseService.findById(findId);
        if(houseService.findById(findId)==null){
            System.out.println("Not Found");return ;
        }
        System.out.println("找到了");
        System.out.println(houseService.findById(findId));

    }




    public void updateHouse(){
        System.out.println("============修改房屋信息=============");
        System.out.println("请输入id");
        int findId = Utility.readInt();
        if(houseService.findById(findId)==null){
            System.out.println("房屋不存在");
        }
        House house=houseService.findById(findId);
        System.out.print("姓名"+"("+ house.getName()+"):");
        String name=Utility.readString(8);
        System.out.print("电话"+"("+ house.getPhone()+"):");
        String phone=Utility.readString(12);
        System.out.print("地址"+"("+ house.getAddress()+"):");
        String address=Utility.readString(16);
        System.out.print("租金"+"("+ house.getRent()+"):");
        int rent = Utility.readInt();
        System.out.print("状态"+"("+ house.getState()+"):");
        String state=Utility.readString(5);
        house.setName(name);
        house.setPhone(phone);
        house.setAddress(address);
        house.setRent(rent);
        house.setState(state);



    }
}
