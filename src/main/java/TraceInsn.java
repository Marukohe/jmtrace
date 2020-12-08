import static java.lang.Integer.toHexString;

public class TraceInsn {
    public static void traceinsn (Object obj, int index, String name, int RWFlag) {
        // R ThreadId ObjectId someField
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

