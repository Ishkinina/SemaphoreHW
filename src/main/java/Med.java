import java.util.concurrent.Semaphore;

/**
 * Created by eishkinina on 17.03.17.
 */
public class Med {

    public static void main(String[] args) {
        final Semaphore room = new Semaphore(5);

        for (int i=0; i<10; i++){
            final int finalI = i;
            Thread t = new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        room.acquire();

                    System.out.println("Patient " + finalI + "is in room");

                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   finally {System.out.println("Patient " + finalI + "is coming out");
                    room.release();}
                }
            } , ""+i);

            t.start();
        }

    }
}
