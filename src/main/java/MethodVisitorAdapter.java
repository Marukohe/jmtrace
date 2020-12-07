import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.logging.LoggingPermission;

public class MethodVisitorAdapter extends MethodVisitor {

    private final MethodVisitor mv;

    public MethodVisitorAdapter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
        this.mv = methodVisitor;
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
//        System.out.println("=====================");
//        System.out.println("owner: " + owner);
//        System.out.println("name: " + name);
//        System.out.println("descriptor: " + descriptor);
//        System.out.println("=====================");
        if(owner.startsWith("java") || owner.startsWith("sun")) {
            mv.visitFieldInsn(opcode, owner, name, descriptor);
            return;
        }

        switch (opcode) {
            case Opcodes.GETSTATIC:
            case Opcodes.PUTSTATIC:
                mv.visitLdcInsn(owner);
                mv.visitLdcInsn(owner + "." + name);
                if(opcode == Opcodes.GETSTATIC) mv.visitInsn(Opcodes.ICONST_1);
                else mv.visitInsn(Opcodes.ICONST_2);
                mv.visitInsn(Opcodes.ICONST_M1);
                mv.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        Type.getInternalName(TraceInsn.class),
                        "traceinsn",
                        "(Ljava/lang/Object;Ljava/lang/String;II)V",
                        false
                );
                break;
//            case Opcodes.GETFIELD:
//                // [obj] -> [obj, obj, name, rw, index]
//                mv.visitInsn(Opcodes.DUP);
//                mv.visitLdcInsn(owner + "." + name);
//                mv.visitInsn(Opcodes.ICONST_1);
//                mv.visitInsn(Opcodes.ICONST_M1);
//                mv.visitMethodInsn(
//                        Opcodes.INVOKESTATIC,
//                        Type.getInternalName(TraceInsn.class),
//                        "traceinsn",
//                        "(Ljava/lang/Object;Ljava/lang/String;II)V",
//                        false
//                );
//                break;
            case Opcodes.PUTFIELD:
                /*
                 * [obj, value] ->* [obj, value, obj] ->* [obj, value, obj, name, rw, index]
                 */
                if(descriptor.startsWith("[") || (!descriptor.equals("D") && !descriptor.equals("L"))) {
                    // category 1
                    mv.visitInsn(Opcodes.DUP2);
                    mv.visitInsn(Opcodes.POP);
                } else {
//                    System.out.println(descriptor);
                    mv.visitInsn(Opcodes.DUP2_X1);
                    mv.visitInsn(Opcodes.POP2);
                    mv.visitInsn(Opcodes.DUP_X2);
                }

//                mv.visitLdcInsn(owner);
                mv.visitLdcInsn(owner + "." + name);
                mv.visitInsn(Opcodes.ICONST_2);
                mv.visitInsn(Opcodes.ICONST_M1);
                mv.visitMethodInsn(
                        Opcodes.INVOKESTATIC,
                        Type.getInternalName(TraceInsn.class),
                        "traceinsn",
                        "(Ljava/lang/Object;Ljava/lang/String;II)V",
                        false
                );
                break;
            default:
                break;
        }

        mv.visitFieldInsn(opcode, owner, name, descriptor);
    }

//    @Override
//    public void visitInsn(int opcode) {
//        switch (opcode) {
//            case Opcodes.IALOAD:
//            case Opcodes.LALOAD:
//            case Opcodes.FALOAD:
//            case Opcodes.DALOAD:
//            case Opcodes.AALOAD:
//            case Opcodes.BALOAD:
//            case Opcodes.CALOAD:
//            case Opcodes.SALOAD:
//                // [arrref, index]  ->  [rw, obj, name, index]
////                mv.visitInsn(Opcodes.ICONST_1);
//
//                break;
//            case Opcodes.IASTORE:
//            case Opcodes.LASTORE:
//            case Opcodes.FASTORE:
//            case Opcodes.DASTORE:
//            case Opcodes.AASTORE:
//            case Opcodes.BASTORE:
//            case Opcodes.CASTORE:
//            case Opcodes.SASTORE:
////                mv.visitInsn(Opcodes.ICONST_0);
//                break;
//            default:
//                break;
//        }
//
//        mv.visitInsn(opcode);
//    }

}
