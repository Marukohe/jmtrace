import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassVisitorAdapter extends ClassVisitor{
    ClassVisitor cv;
    public ClassVisitorAdapter(ClassVisitor cv) {
        super(Opcodes.ASM7, cv);
        this.cv = cv;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//        System.out.println("=====================");
//        System.out.println("acce== " + access);
//        System.out.println("name== " + name);
//        System.out.println("desc== " + descriptor);
//        System.out.println("sign== " + signature);
//        System.out.println("=====================");
        if(name.startsWith("java") || name.startsWith("sun") || name.startsWith("traceinsn")) return cv.visitMethod(access, name, descriptor, signature, exceptions);
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new MethodVisitorAdapter(Opcodes.ASM7, mv);
    }
}
