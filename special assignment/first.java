import Abc.Second;
import Abc.xyz.third;

class first {
    void fun() {
        System.out.println("This is fun class of first class");
    }

    public static void main(String args[]) {
        second obj = new second();
        obj.fun();
        Second obj1 = new Second();
        obj1.fun();
        third obj2 = new third();
        obj2.f();
        System.out.println("Hello world");
    }
}