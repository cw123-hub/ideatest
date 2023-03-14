package smallprogram.House.service;

import smallprogram.House.domain.House;

/**
 * 定义house[],保存HOuse对象
 * 1.响应HouseView的调用
 * 2.完成crud
 */
public class houseService {
    private House[] houses;
    private  int idCounter=1;//实现id自增
    private int HouseNum=1;//记录已有房屋数量
    public houseService(int size){
        houses=new House[size];
        //当创建HouseService对象的时候，指定数组的大小
        houses[0]=new House(1,"jack","112","海淀区",2000,"未出租");


    }
    //list方法返回所有房屋信息
    public House[] list(){
        return houses;
    }

    //add方法,添加新对象
    public boolean add(House newHouse){
        if(HouseNum==houses.length){
            System.out.println("已满，不能再添加了");
            return false;
        }
        houses[HouseNum]=newHouse;
        HouseNum++;
        newHouse.setId(++idCounter);
        return  true;
    }


    //del 删除一个房屋信息
    public boolean del(int delId){
        //
        int index=-1;
        for (int i = 0; i < HouseNum; i++) {
            if(delId==houses[i].getId()){
                index=i;
            }
        }
        if(index==-1){

            return false;
        }
        for(int j=index;j<HouseNum-1;j++){//下标有j+1，所以循环判断条件是这样
            houses[j]=houses[j+1];
        }
        houses[HouseNum-1]=null;
        HouseNum--;
        return true;
    }


    public House findById(int findId) {
        int index=-1;
        for (int i = 0; i < HouseNum; i++) {
            if(findId==houses[i].getId()){
                index=i;
            }
        }
        if(index<0) return  null;

     return houses[index];

    }
}
