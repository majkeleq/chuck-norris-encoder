package chucknorris;

import java.util.Scanner;

public class MessageDecoding extends Message {

    public void displayDecodedMessage(Scanner sc) {
        System.out.println("Input encoded string:");
        super.setMessage(sc.nextLine());
        if(!isEncodedStringValid(message)) {
            System.out.println("Encoded string is not valid");
            return;
        }
        try {
            String decodecMessage = decodeMessage();
            System.out.println("Decoded string:");
            System.out.println(decodecMessage);
        } catch (Exception e) {
            System.out.println("Encoded string is not valid");
        }
    }

    private boolean isEncodedStringValid(String message) {
        //jesli zakodowana wiadomość składa się z czegoś innego niż "0" i " "
        //lub nie jest podzielona na segmenty to zwraca falsz
        boolean isValid = message.matches("^[0 ]+$") && message.split(" ").length % 2 == 0;

        return isValid;
    }
    private String decodeMessage() {

        String temp = "";
        String binaryMessage = encodedToBinary();
        //dzielenie ciągu bitów na 7bitowe grupy, konwersja na int, a następnie konwersja na char
        for (int i = 0; i < binaryMessage.length(); i += 7) {
            temp = temp + ((char) Integer.parseInt(binaryMessage.substring( i, i + 7 ), 2));
        }
        return temp;
    }

    private String encodedToBinary() {
        /**
         * metoda, która zapisuje zaszyfrowaną wiadomość do formy binarnej (1 znak - 7 bitów)
         */
        String binaryMessage = "";
        String number = "";
        String[] encodedWords = message.split(" ");
        for (int i = 0; i < encodedWords.length; i++) {
            if ( i % 2 == 0) {
                switch (encodedWords[i]) {
                    case "00":
                        number = "0";
                        break;
                    case "0":
                        number = "1";
                        break;
                    default:
                        return "ERROR";
                }
                //number = encodedWords[i].equals("00") ? "0" : "1";
            } else {
                binaryMessage = binaryMessage.concat(number.repeat(encodedWords[i].length()));
            }
        }
        return binaryMessage;
    }
}
