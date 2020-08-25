public class App {
    public static void main(String[] args) throws Exception {

        ServerThread thread = new ServerThread("Server1");
        ServerThread thread2 = new ServerThread("Server2");
        thread2.setPriority(Thread.MAX_PRIORITY);

        thread.start();
        thread2.start();
    }
}
