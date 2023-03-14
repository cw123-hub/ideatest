package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class cls=Class.forName("reflection.Cat");
        System.out.println(cls.newInstance().getClass());


        //getField�ò���˽������
        Field namefield=cls.getField("name");
        System.out.println(namefield);
        System.out.println(namefield.get(cls.newInstance()));//�õ������ֵ��ͳд��o.field,�����෴

        //
        Constructor constructor = cls.getConstructor();//()�п���ָ���������ͣ��������޲�
        System.out.println(constructor);
        Object o = constructor.newInstance();
        System.out.println(o.getClass());
        Constructor constructor1 = cls.getConstructor(String.class);//�����β����͵�class
        System.out.println(constructor1);

    }

    @org.junit.jupiter.api.Test
    public void Tt(){ long start = System.currentTimeMillis();
        Cat cat = new Cat();

        for (int i = 0; i < 9000; i++) {
            cat.say();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);

    }
    @org.junit.jupiter.api.Test
    public void Tt2() throws  Exception {
        long start = System.currentTimeMillis();
        Class cls=Class.forName("reflection.Cat");
        Method say = cls.getMethod("say");
        //say.setAccessible(true);
        for(int i=0;i<9000;i++){
            Object invoke = say.invoke(cls.newInstance());
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}



