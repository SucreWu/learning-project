package com.wujie.learning.hotspot;

/**
 * @Description: 虚拟机栈和本地方法栈溢出
 * StackOverflowError
 * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常
 * @Auther: wujie
 * @Date: 2018/12/8 14:41
 * @version: V1.0
 */
public class JavaVMStackSOF {
    /**
     * The stack size specified is too small, Specify at least 160k  书上是128k,我这就设160了
     * VM Args: -Xss160k
     */
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable{
         JavaVMStackSOF sof = new JavaVMStackSOF();
         try{
             sof.stackLeak();
         }catch (Throwable e){
             System.out.println("stack length:" + sof.stackLength);
             throw e;
         }
    }
}
