package com.company;
import java.util.Locale;
import java.util.Scanner;

public class CaesarVigenereCipher {

    boolean keepGoing = true;
    String alphabetCaesar = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ", alphabetVigenere = " ABCDEFGHIJKLMNOPQRSTUVWXYZÆØÅ";
    Scanner in = new Scanner(System.in);

    public int chooseEncryption(){
        System.out.print("\nWhat cipher would you like to use?:\n(1)Caesar\n(2)Vigenere\n(3)To exit\n\nAnswer:");
        return in.nextInt();
    }

    public int chooseKryptOrDekrypt(){
        System.out.print("\nDo you want to krypt (1), dekrypt (2) or any other to exit: ");
        return in.nextInt();
    }
    public String inputText(){
        System.out.print("Enter text: ");
        return in.next().toUpperCase(Locale.ROOT);
    }
    public int [] convertCharsToInts(String textInput){
        int[] convertedChars = new int[textInput.length()];
        for (int i = 0; i < textInput.length(); i++) {
            convertedChars[i] = alphabetCaesar.indexOf(textInput.charAt(i));
        }
        return convertedChars;
    }
    public int[] applyShift(int[] convertedChars){
        int[] shiftAppliedNumbers = new int[convertedChars.length];
        System.out.print("Please enter the shift key. To dekrypt enter a negative number, to krypt enter a positive number: ");
        int shift = in.nextInt();
        for (int i = 0; i < convertedChars.length; i++) {
            if (shift > 0) {
                shiftAppliedNumbers[i] = convertedChars[i] + shift;
            } else if (shift < 0){
                shiftAppliedNumbers[i] = convertedChars[i] + shift;
            } else {
                System.out.println("Nothing happened :[");
            }
            if (shiftAppliedNumbers[i] < 0){
                shiftAppliedNumbers[i] = 29 - shiftAppliedNumbers[i] ;
            }
        }
        return shiftAppliedNumbers;
    }
    public char[] convertIntsToChars(int[] shiftAppliedNumbers){
        char[] krypedOrDekryptedChars = new char[shiftAppliedNumbers.length];
        for (int i = 0; i < shiftAppliedNumbers.length; i++) {
            if (0 > shiftAppliedNumbers[i]){
                shiftAppliedNumbers[i] += 29;
            }
            krypedOrDekryptedChars[i] = (shiftAppliedNumbers[i] < 30) ? alphabetCaesar.charAt(shiftAppliedNumbers[i]) : alphabetCaesar.charAt(shiftAppliedNumbers[i] % 29);
            }
        return krypedOrDekryptedChars;
    }
    public void printKrypedOrDekryptedText(char[] kryptedOrDekryptedChars){
        for (char kryptedOrDekryptedChar : kryptedOrDekryptedChars) {
            System.out.print(kryptedOrDekryptedChar);
        }
    }
    public int[] vigenereKeyCodeInput(){
        System.out.print("Enter the keycode: ");
        String keyCode = in.next().toUpperCase(Locale.ROOT);
        int[] valuesOfKeyCode = new int[keyCode.length()];
        for (int i = 0; i < keyCode.length(); i++) {
            valuesOfKeyCode[i] = alphabetVigenere.indexOf(keyCode.charAt(i));
        }
        return valuesOfKeyCode;
    }
    public int[] applyVigenereKeyCode(int[] valuesOfKeyCode, int[] valuesOfPlaintext, int choice){
        int[] appliedKeyCodeInts = new int[valuesOfPlaintext.length];
        for (int i = 0, j = 0; i < valuesOfPlaintext.length; i++, j++) {
            if (j > valuesOfKeyCode.length - 1){
                j = 0;
            }
            if (choice == 2){
                valuesOfKeyCode[j] =- valuesOfKeyCode[j];
            }
            appliedKeyCodeInts[i] = valuesOfPlaintext[i] + valuesOfKeyCode[j];
        }
        return appliedKeyCodeInts;
    }
    public void caesarKryption(){
        printKrypedOrDekryptedText(convertIntsToChars(applyShift(convertCharsToInts(inputText()))));
    }
    public void caesarDekryption(){
        printKrypedOrDekryptedText(convertIntsToChars(applyShift(convertCharsToInts(inputText()))));
    }
    public void vigenereKryption(int choice){
        printKrypedOrDekryptedText(convertIntsToChars(applyVigenereKeyCode(vigenereKeyCodeInput(),convertCharsToInts(inputText()), choice)));
    }
    public void vigenereDekryption(int choice){
        printKrypedOrDekryptedText(convertIntsToChars(applyVigenereKeyCode(vigenereKeyCodeInput(),convertCharsToInts(inputText()), choice)));
    }
    public void runProgram(){
        while (keepGoing){
            int choice = chooseEncryption();
            switch (choice) {
                case 1 -> {
                    int choiceCaesar = chooseKryptOrDekrypt();
                    if (choiceCaesar == 1) {
                        caesarKryption();
                    } else if (choiceCaesar == 2) {
                        caesarDekryption();
                    } else {
                        runProgram();
                    }
                }
                case 2 -> {
                    int choiceVigenere = chooseKryptOrDekrypt();
                    if (choiceVigenere == 1) {
                        vigenereKryption(choiceVigenere);
                    } else if (choiceVigenere == 2) {
                        vigenereDekryption(choiceVigenere);
                    } else {
                        runProgram();
                    }
                }
                default -> {
                    System.out.println("Goodbye");
                    keepGoing = false;
                }
            }
        }
    }
    public static void main(String[] args) {
	    new CaesarVigenereCipher().runProgram();
    }
}