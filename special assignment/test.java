import Abc.Second;
import Abc.xyz.*;

class fun extends Second {
    public void f() {
        System.out.println("This is f function of fun class inheriting second");
    }
}

public class test extends third {
    void show() {
        System.out.println("This is a method of test class which is derived from third class");
        f();
    }

    public static void main(String args[]) {
        fun obj = new fun();
        obj.fun();
        obj.f();
        test obj2 = new test();
        obj2.show();
    }
}
