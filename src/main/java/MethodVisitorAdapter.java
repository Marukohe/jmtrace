import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MethodVisitorAdapter extends MethodVisitor {

    private final MethodVisitor mv;

    public MethodVisitorAdapter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
        this.mv = methodVisitor;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        switch (opcode) {
            case Opcodes.GETSTATIC:
//                hack(mv, "getstatic");
                mv.visitInsn(Opcodes.ICONST_1);
                mv.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        Type.getInternalName(TraceInsn.class),
                        "traceinsn",
                        "(I)V",
                        false
                );
                break;
//            case Opcodes.PUTSTATIC:
//                hack(mv, "putstatic");
//                break;
//            case Opcodes.GETFIELD:
//                hack(mv, "getfield");
//                break;
//            case Opcodes.PUTFIELD:
//                hack(mv, "putfield");
//                break;
            default:
                break;
        }

        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }

//    @Override
//    public void visitVarInsn(int opcode, int var) {
//        if(opcode == Opcodes.ALOAD) {
//            hack(mv, "aload");
//        } else if(opcode == Opcodes.ASTORE) {
//            hack(mv, "astore");
//        }
//        super.visitVarInsn(opcode, var);
//    }

//    private static void hack(MethodVisitor mv, String msg) {
//
//        mv.visitInsn(Opcodes.ICONST_1);
//        mv.visitMethodInsn(
//                Opcodes.INVOKESTATIC,
//                Type.getInternalName(TraceInsn.class),
//                "traceinsn",
//                "(I)V",
//                false
//        );
//        mv.visitFieldInsn(
//                Opcodes.GETSTATIC,
//                Type.getInternalName(System.class),
//                "out",
//                Type.getDescriptor(PrintStream.class)
//        );
//        mv.visitLdcInsn(msg);
//        mv.visitMethodInsn(
//                Opcodes.INVOKEVIRTUAL,
//                Type.getInternalName(PrintStream.class),
//                "println",
//                "(Ljava/lang/String;)V",
//                false
//        );
//    }
//
}
