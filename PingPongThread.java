package Exercise1;

public class PingPongThread implements Runnable {

    String StrStr;
    boolean running = true;
    String turn;
    Object syncObj;

    PingPongThread(String str, Object obj) {
        StrStr = str;
        syncObj = obj;
    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {
        synchronized (syncObj) {
            while (running) {
                System.out.println(StrStr);

                try {
                     Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                syncObj.notifyAll();
                try{
                    syncObj.wait(100);

                }catch(InterruptedException e){       
                    e.printStackTrace();;
                }

                
            }
        }
    }

}