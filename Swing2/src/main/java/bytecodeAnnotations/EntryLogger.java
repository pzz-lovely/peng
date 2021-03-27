package bytecodeAnnotations;

import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

/**
 * @Auther lovely
 * @Create 2020-03-16 13:32
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description
 */
public class EntryLogger extends ClassVisitor {
    private String className;

    public EntryLogger(ClassWriter writer, String className) {
        super(Opcodes.ASM4, writer);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String methodName, String desc, String signature,
                                     String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, methodName, desc, signature, exceptions);
        return new AdviceAdapter(Opcodes.ASM5, mv, access, methodName, desc){
            private String loggerName;

            @Override
            public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
                return new AnnotationVisitor(Opcodes.ASM5){
                    @Override
                    public void visit(String s, Object o) {
                        /*if(desc.equals())*/
                    }
                };
            }
        };
    }
}
