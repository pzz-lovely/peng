package genericReflection;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther lovely
 * @Create 2020-02-10 13:45
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class GenericReflectionTest {
    public static void main(String[] args) {
        //read class name from command line args or user input
        String name ;
        if(args.length>0) name = args[0];
        else{
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("Enter class name (e.g. java.util.Collections):");
                name = scanner.next();
            }
        }
        try{
            Class<?> c = Class.forName(name);
            printClass(c);
            for (Method m : c.getDeclaredMethods()) {
                printMethod(m);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printClass(Class<?> c){
        System.out.print(c);
        printTypes(c.getTypeParameters(),"<",",",">",true);
        Type sc = c.getGenericSuperclass();
        if (sc != null) {
            System.out.print("extends");
            printType(sc,false);
        }
        printTypes(c.getGenericInterfaces(), "implements", ",", "", false);
        System.out.println();
    }

    public static void printMethod(Method method){
        String name = method.getName();
        System.out.print(Modifier.toString(method.getModifiers()));
        System.out.print(" ");
        printTypes(method.getTypeParameters(), "<", ",", ">", true);
        printType(method.getGenericReturnType(), false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(method.getGenericParameterTypes(), "", ",", "", false);
        System.out.println(")");
    }


    /**
     * ��ӡ type  ����
     * @param types
     * @param pre ǰ��
     * @param sep �ָ�
     * @param suf ���������
     * @param isDefinition
     */
    public static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if(pre.equals("extends") && Arrays.equals(types,new Type[]{Object.class})) return;
        if(types.length > 0) System.out.print(pre);
        for (int i = 0; i < types.length; i++) {
            if(i>0) System.out.print(sep);
            printType(types[i],isDefinition);
        }
        if(types.length > 0) System.out.print(suf); //��׺
    }




    /**
     * ��ӡType���� Type��Java����������������͵�ͨ�ó����ӿڡ� ��Щ����ԭʼ���ͣ����������ͣ��������ͣ����ͱ�����ԭʼ���͡�
     * @param type
     * @param isDefinition
     */
    public static void printType(Type type,boolean isDefinition){
        /*System.out.println("pintType : "+type.getTypeName());*/

        if (type instanceof Class) { //class����������������
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        } else if (type instanceof TypeVariable) {  //�������ͱ��� �� T extends Comparable<? super T>
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if(isDefinition)
                //TypeVariable Type[] getBounds() ���������ͱ����������޶�������ñ������޶����򷵻س���Ϊ0������
                printTypes(t.getBounds(),"extends","&","",false);
        }else if(type instanceof WildcardType) {    //����ͨ����� <? super T>
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds(),"extends","&","",false);
            printTypes(t.getLowerBounds(),"super","&","",false);
        }else if(type instanceof ParameterizedType) {//�����������ӿ����� �� Comparable<? super T>
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();  //�õ�����������
            if (owner != null) {
                printType(owner, false);
                System.out.print(".");
            }
            printType(t.getRawType(),false);
            printTypes(t.getActualTypeArguments(), "<", ",", ">", false);
        }else if(type instanceof GenericArrayType){ //���������� ��T
            GenericArrayType t = (GenericArrayType) type;
            System.out.print(" ");
            printType(t.getGenericComponentType(),isDefinition);
            System.out.print("[]");
        }
    }



}
