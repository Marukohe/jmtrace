import static java.lang.Integer.toHexString;

public class TraceInsn {
    public static void traceinsn (Object obj, int index, String name, int RWFlag) {
        // R 1032 b026324c6904b2a9 cn.edu.nju.ics.Foo.someField
        String out = RWFlag == 1 ? "R " : "W ";
        out += Thread.currentThread().getId() + " ";
        out += toHexString(System.identityHashCode(obj)) + " ";
        if(name.equals(" ")) {
            String des = obj.getClass().getComponentType().getCanonicalName();
            out += String.format("%s[%d]", des, index);
        } else {
            out += name.replace("/", ".");
        }
        System.out.println(out);
    }
}

