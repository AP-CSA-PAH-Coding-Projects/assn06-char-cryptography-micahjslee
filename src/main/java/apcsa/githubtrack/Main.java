package apcsa.githubtrack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Implement your main application logic here
public class Main {

    public static void rotate(CString[] arr, int d) // rotates the array of CStrings to the left by d positions
    {
        int length = arr.length; // gets the length of the array
        CString[] rotatedArr = new CString[length]; // creates a new array for storing the rotated one
        for (int i = 0; i < length; i++)
        {
            rotatedArr[i] = arr[(i + d) % length]; // fills up the new array with the elements in their new positions, using modulo to wrap around the end of the array
        }
        for (int i = 0; i < length; i++)
        {
            arr[i] = rotatedArr[i]; // updates the original array to the rotated array
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File secretMessage = new File("C:\\Users\\mjsle\\OneDrive\\Desktop\\AP CSA\\assn06-char-cryptography-micahjslee\\src\\main\\resources\\secretMessage.txt"); // creates a new file object for the secret message
        Scanner fileScanner = new Scanner(secretMessage); // creates a scanner to read the file
        String sM = fileScanner.nextLine(); // reads the first line of the file, which contains the secret message
        fileScanner.close(); // closes the scanner
        String[] words = sM.split(" "); // splits the secret message into an array of words
        CString[] arr = new CString[words.length]; // creates an array of CStrings to store the CStrings for each word
        for (int i = 0; i < words.length; i++)
        {
            CString word = new CString(words[i]); // creates a new CString for the current word
            arr[i] = word; // stores the CString in the array
        }
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = CStringUtil.decrypt(arr[i]); // decrypts each CString in the array
        }

        // the code below makes a copy array and puts all CString in their numerical values, then finds the largest numerical value
        CString secretMes = new CString(sM); // creates a new CString for the secret message
        int[] numericalValues = CStringUtil.toNumerical(secretMes, 0); // converts the secret message to a numerical array
        int maxValue = 0; // variable to keep track of the largest numerical value
        for (int i = 0; i < numericalValues.length; i++)
        {
            if (numericalValues[i] > maxValue) // if the current numerical value is larger than the largest found so far, this updates maxValue
            {
                maxValue = numericalValues[i];
            }
        }
        rotate(arr, maxValue - 60); // rotates the array left by the largest ascii value - 60
        String decryptedMessage = ""; // variable to store the decrypted message
        for (int i = 0; i < arr.length; i++)
        {
            decryptedMessage += arr[i] + " "; // concatenates the decrypted strings
        }
        System.out.println(decryptedMessage); // prints the decrypted message
    }

}