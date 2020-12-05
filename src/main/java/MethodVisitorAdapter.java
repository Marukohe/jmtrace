import org.objectweb.asm.MethodVisitor;

public class MethodVisitorAdapter extends MethodVisitor {

    public MethodVisitorAdapter(int api, MethodVisitor methodVisitor) {
        super(api, methodVisitor);
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }
}
