package chucknorris;

import java.util.Scanner;

public class MessageEncoding extends Message {

    public void displayEncodedMessage(Scanner sc) {
        System.out.println("Input string:");
        super.setMessage(sc.nextLine());
        System.out.println("Encoded string");
        System.out.println(encodeMessage());
    }

    /**
     * Funkcja zapisuje każdy znak w formie 7bitów i zwraca tak zapisaną wiadomość
     * @return
     */
    private String messageToBits() {
        int charBits;
        String lineBits = "";
        String temp;
        for (int i = 0; i < message.length(); i++) {
            charBits = Integer.parseInt(Integer.toBinaryString(message.charAt(i)));
            temp = String.format("%07d", charBits);//kazdy znak zapisywany jako 7 bitow
            lineBits = lineBits.concat(temp); //przepisywanie oryginalnego stringa do formy bitowej (kazdy znak po 7 bitow)
        }
        return lineBits;
    }

    /**
     * Metoda korzysta z metody messageToBits(), która zwraca wiadomość w zapisanę w formie bitowej
     * (7 bitów na każdy znak)
     * Jeżeli napotyka sekwencję bitów zaczynającą się od "0" to rozpoczyna sekcję od "00 " a następnie
     * drukuje "0" tyle razy ile występuje "0" z rzędu
     * Jeżeli napotyka sekwencję bitów zaczynającą się od "1" to rozpoczyna sekcję od "0 " a następnie
     * drukuje "0" tyle razy ile występuje "1" z rzędu
     * @return
     */
    private String encodeMessage() {
        String lineBits = messageToBits();
        int count; //licznik wystapien
        String temp = "";
        for (int i = 0; i < lineBits.length(); i++)     {
            count = 1;
            //rozpoczecie nowej serii od 0 lub 00
            switch (lineBits.charAt(i)) {
                case '0':
                    temp = temp.concat(" 00 ");
                    break;
                case '1':
                    temp = temp.concat(" 0 ");
                    break;
                default:
                    break;
            }
            //zlicza ilość wystąpień i zmienia iterację na kolejną sekwencję !!count=1!!
            for (int j = i + 1; j < lineBits.length() + 1; j++) {
                if (j == lineBits.length()) {
                    i = j;
                    temp = temp.concat("0".repeat(count));
                    break;
                }
                if (lineBits.charAt(j) == lineBits.charAt(i)) {
                    count++;
                } else {
                    i = j - 1;
                    temp = temp.concat("0".repeat(count));
                    break;
                }
            }
        }
        return temp.trim();
    }
}
