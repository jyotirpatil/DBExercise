package Exercise1;

public class ExecutorTest {
    public static void main(String[] args){
        try{

        Object lObject = new Object();

        PingPongThread r1 =  new PingPongThread("Ping", lObject);
        PingPongThread r2 = new PingPongThread("Pong", lObject);
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);

        thread1.start();
        thread2.start();

        Thread.sleep(5000);
        r1.terminate();
        r2.terminate();

       }catch(Exception e){
         e.printStackTrace();
       }
    }
    

}
