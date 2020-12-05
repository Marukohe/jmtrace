import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class PreMainAgent {
    static {
        System.out.println("PreMainAgent class static block run...");
    }

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("PreMainAgent agentArgs : " + agentArgs);
//        Class<?>[] cLasses = inst.getAllLoadedClasses();
//        for (Class<?> cls : cLasses) {
//            System.out.println("PreMainAgent get loaded class:" + cls.getName());
//        }
        inst.addTransformer(new DefineTransformer(), true);
    }

    static class DefineTransformer implements ClassFileTransformer {

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] classfileBuffer)
                throws IllegalClassFormatException {
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            ClassVisitor cv = new ClassVisitorAdapter(cw);
            cr.accept(cv, ClassReader.EXPAND_FRAMES);
            System.out.println("PreMainAgent transform Class:" + className);
            return cw.toByteArray();
        }
    }

}
