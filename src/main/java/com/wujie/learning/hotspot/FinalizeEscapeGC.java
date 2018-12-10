package com.wujie.learning.hotspot;

/**
 * @Description: 一次对象自我拯救
 * @Auther: wujie
 * @Date: 2018/12/10 16:23
 * @version: V1.0
 */
public class FinalizeEscapeGC {
    /**
     * 清除一个对象需要经历两次标记过程：如果对象在进行可达性分析后发现没有与GC Roots相连接的引用链，
     * 那他将会被第一次标记，如果被判定为有必要执行finalize()方法，会将该对象放入F-Queue队列中，
     * 由一个优先级较低的Finalizer线程去执行，再GC对队列进行第二次标记前，对象可以进行"自救"
     * (譬如把自己(this关键字)赋值给某个类变量或者对象的成员变量)
     * 1.对象可以在被GC时自我拯救
     * 2.自救机会只有一次，因为一个对象的finalize()方法最多只会被系统调用一次
     */

    // 静态成员变量，不会被垃圾回收
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("I'm still alive...");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method excuted!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 置为空后，再执行finalize时进行第一次自救
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级很低，所以暂停0.5秒等待他执行
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("I'm dead...");
        }


        // 与上面一致的代码，因为finalize方法只会被调用一次，所以自救会失败
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级很低，所以暂停0.5秒等待他执行
        Thread.sleep(500);
        if (SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("I'm dead...");
        }
    }
}
