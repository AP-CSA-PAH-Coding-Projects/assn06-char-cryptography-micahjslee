package apcsa.githubtrack;

// Implement your CString class here
public class CString {
    
    private String[] CString; // stores the array of the string

    public CString(String str) {
        this.CString = new String[str.length()]; // initialize the array to the length of the string
        for (int i = 0; i < str.length(); i++) 
        {
            this.CString[i] = str.substring(i, i + 1); // fill the array with each character of the string
        }
    }

    public void reverse() {
        String[] reversedCString = new String[this.CString.length]; // creates a new array to store the reversed string
        for (int i = 0; i < this.CString.length; i++)
        {
            reversedCString[i] = this.CString[this.CString.length - 1 - i]; // fills the new array with the characters in reverse order
        }
        this.CString = reversedCString; // updates the original array to the reversed array
    }

    public void sortAscending() {
        for (int j = 0; j < this.CString.length - 1; j++) // uses selection sort
        {
            int minIndex = j;
            for (int i = j + 1; i < this.CString.length; i++)
            {
                if (this.CString[i].compareTo(this.CString[minIndex]) < 0) // checks if the current character is smaller than the smallest in the sorted portion
                {
                    minIndex = i; // updates the index of the smallest character
                }
            }
            String temp = this.CString[j];  // saves the current character in a temporary variable
            this.CString[j] = this.CString[minIndex]; // replaces the current char with minIndex
            this.CString[minIndex] = temp; // replaces the character at minIndex with the char at j
        }
    }

    public void sortDescending() {
        for (int i = 1; i < this.CString.length; i++) // uses insertion sort
        {
            String temp = this.CString[i];
            int j = i - 1;
            while (j >= 0 && temp.compareTo(this.CString[j]) > 0) // checks if the current character is larger than the characters in the sorted portion
            {
                this.CString[j + 1] = this.CString[j]; // shifts the characters in the sorted part to the right
                j--;
            }
            this.CString[j + 1] = temp; // places the current character in its correct position in the sorted part
        }
    }

    public String[] getCString()
    {
        return this.CString; // returns the array of the string
    }

    public String toString()
    {
        String result = ""; // variable to store the final string
        for (int i = 0; i < this.CString.length; i++)
        {
            result += this.CString[i]; // adds each character in the array to the result string
        }
        return result; // returns the final string
    }

}