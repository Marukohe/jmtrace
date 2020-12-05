import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassVisitorAdapter extends ClassVisitor{
    public ClassVisitorAdapter(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
//        System.out.println("=====================");
//        System.out.println("acce== " + access);
//        System.out.println("name== " + name);
//        System.out.println("desc== " + descriptor);
//        System.out.println("sign== " + signature);
//        System.out.println("=====================");
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new MethodVisitorAdapter(Opcodes.ASM6, mv);
    }
}
