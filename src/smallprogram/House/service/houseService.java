package smallprogram.House.service;

import smallprogram.House.domain.House;

/**
 * ����house[],����HOuse����
 * 1.��ӦHouseView�ĵ���
 * 2.���crud
 */
public class houseService {
    private House[] houses;
    private  int idCounter=1;//ʵ��id����
    private int HouseNum=1;//��¼���з�������
    public houseService(int size){
        houses=new House[size];
        //������HouseService�����ʱ��ָ������Ĵ�С
        houses[0]=new House(1,"jack","112","������",2000,"δ����");


    }
    //list�����������з�����Ϣ
    public House[] list(){
        return houses;
    }

    //add����,����¶���
    public boolean add(House newHouse){
        if(HouseNum==houses.length){
            System.out.println("�����������������");
            return false;
        }
        houses[HouseNum]=newHouse;
        HouseNum++;
        newHouse.setId(++idCounter);
        return  true;
    }


    //del ɾ��һ��������Ϣ
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
        for(int j=index;j<HouseNum-1;j++){//�±���j+1������ѭ���ж�����������
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
