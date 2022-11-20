package chucknorris;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MessageDecoding messageDec = new MessageDecoding();
        MessageEncoding messageEnc = new MessageEncoding();
        boolean toContinue = true;
        while (toContinue) {
            System.out.println("Please input operation (encode/decode/exit):");
            String input = sc.nextLine().trim();
            switch (input) {
                case "encode":
                    messageEnc.displayEncodedMessage(sc);
                    break;
                case "decode":
                    messageDec.displayDecodedMessage(sc);
                    break;
                case "exit":
                    System.out.println("Bye!");
                    toContinue = false;
                    break;
                default:
                    System.out.println("There is no '" + input + "' operation");
            }
        }
    }
}