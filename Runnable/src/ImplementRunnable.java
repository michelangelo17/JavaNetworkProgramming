public class ImplementRunnable implements Runnable {

  private int threadIndex;

  public ImplementRunnable(int index) {
    this.threadIndex = index;
  }

  public void run() {
    int clientNumber = 1;
    while (clientNumber != 100) {
      System.out.println("Server" + threadIndex + " sent data to client: " + clientNumber);
      try {
        Thread.sleep(1000);
      } catch (Exception e) {
        System.out.println(e);
      }

      clientNumber++;
    }
  }

  public void start() {
    Thread thread = new Thread(this);
    thread.start();
  }
}