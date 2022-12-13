public class Main {

    public Main() {}
    public static void main(String[] Args){
        while(true) {
            try {
                new AuthenticationUI();
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
}