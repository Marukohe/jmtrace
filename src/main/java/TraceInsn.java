import static java.lang.Integer.toHexString;

public class TraceInsn {

    String[] a = {"123", "345"};
    public static void traceinsn (Object obj, String name, int RWFlag ,int index) {
        // R 1032 b026324c6904b2a9 cn.edu.nju.ics.Foo.someField
        String out = RWFlag == 1 ? "R " : "W ";
        out += Thread.currentThread().getId() + " ";
        out += toHexString(System.identityHashCode(obj)) + " ";
        out += name.replace("/", ".");
        System.out.println(out);
    }

    public void setA() {
        Test test = new Test();
        Test test1 = new Test();
        System.out.println("hello");
        System.out.println(test1.d[1]);
        System.out.println(a[1]);
//        traceinsn(1, "12", "123",3);
    }

}

class Test {
    double[] d = {1, 24, 5};

    public double getD(int i) {
        return d[i];
    }
}
