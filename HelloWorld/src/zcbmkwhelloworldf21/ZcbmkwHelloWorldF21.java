package zcbmkwhelloworldf21;

/**
 * @author Zach Brown ZCBMKW
 */
public class ZcbmkwHelloWorldF21 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Name user;
        user = new Name();
        user.setName("Zach Brown");
        user.setCourseNum("CS3330");
        user.printName();
        user.invokeMe();
    }
}
