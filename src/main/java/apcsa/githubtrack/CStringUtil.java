package apcsa.githubtrack;

// Implement your CStringUtil class here
public class CStringUtil {

    public static boolean isPalindrome(CString str) // palindrome checker that correctly ignores spaces
    {
        String[] cStringArray = str.getCString(); // gets the array of the CString
        int left = 0;
        int right = cStringArray.length - 1;
        
        while (left < right) // algorithm similar to binary search that compares characters from the left and right, skipping spaces and ignoring case
        {
            while (left < right && cStringArray[left].equals(" ")) // skips spaces from the left
            {
                left++;
            }
            while (left < right && cStringArray[right].equals(" ")) // Skips spaces from the right
            {
                right--;
            }
            if (!cStringArray[left].equalsIgnoreCase(cStringArray[right])) // compares the character, ignoring case
            {
                return false; // if any characters don't match, its not a palindrome - return false
            }
            left++;
            right--;
        }
        return true; // if all characters match, it is a palindrome, so return true
    }

    public static int[] toNumerical(CString str, int offset) // converts a CString to its numerical ascii values
    {
        String[] cStringArray = str.getCString(); // gets the array of the CString str
        int[] numArray = new int[cStringArray.length]; // creates a new array to store the numerical values
        for (int i = 0; i < cStringArray.length; i++)
        {
            numArray[i] = (int) cStringArray[i].charAt(0) + offset; // converts the character to its ascii value and adds the offset
        }
        return numArray; // returns numArray
    }

    public static int maxMirror(CString str) // finds the length of the longest mirrored section in CString
    {
        int[] cStringArray = toNumerical(str, 0); // gets the numArray of str
        int maxMirrorLength = 1; // variable to return for the length of longest mirror, set to one because the smallest mirror is a single character
        if (cStringArray.length == 0) // if the string is empty, return 0
        {
            return 0;
        }
        for (int i = 0; i < cStringArray.length; i++)
        {
            for (int j = cStringArray.length - 1; j >= 0; j--)
            {
                int length = 0; // variable to keep track of the length of the current mirror
                int k = 0; // variable to keep track of the index when checking the mirror
                while (i + k < cStringArray.length && j - k >= 0 && cStringArray[i+k] == cStringArray[j-k]) // while in bounds, checks if the characters on both ends of the array are mirrored and if so increases the length of the current mirror and increases k
                {
                    k++;
                    length++;
                }
                if (length > maxMirrorLength) // if the current mirror is longer than the longest mirror found so far, this updates maxMirrorLength
                {
                    maxMirrorLength = length;
                }
            }
        }
        return maxMirrorLength; // returns the length of the longest mirror
    }

    public static int maxMirror(int[] str) // overloaded method
    {
        int maxMirrorLength = 1; // variable to return for the length of longest mirror
        if (str.length == 0) // if the string is empty, return 0
        {
            return 0;
        }
        for (int i = 0; i < str.length; i++)
        {
            for (int j = str.length - 1; j >= 0; j--)
            {
                int length = 0; // variable to keep track of the length of the current mirror
                int k = 0; // variable to keep track of the index when checking the mirror
                while (i + k < str.length && j - k >= 0 && str[i+k] == str[j-k]) // while in bounds, checks if the characters on both ends of the array are mirrored and if so increases the length of the current mirror and increases k
                {
                    k++;
                    length++;
                }
                if (length > maxMirrorLength) // if the current mirror is longer than the longest mirror found so far, this updates maxMirrorLength
                {
                    maxMirrorLength = length;
                }
            }
        }
        return maxMirrorLength; // returns the length of the longest mirror
    }

    public static int[] memeifyArray(int[] arr)
    {
        if (arr.length == 0) // if the array is empty, return an empty array
        {
            return new int[0];
        }
        int[] memeifiedArray = new int[arr.length]; // creates a new array to store the memeified array
        for (int i = 0; i < arr.length; i++) // fills the new array with the values from the original array
        {
            memeifiedArray[i] = arr[i];
        }
        for (int i = 0; i < arr.length - 1; i++) // puts 7's after all the 6's
        {
            if (arr[i] == 6)
            {
                memeifiedArray[i + 1] = 7; // makes sure 7's go AFTER 6's
            }
        }
        int j = 0; // placeholder variable for the original array (arr)
        for (int i = 0; i < memeifiedArray.length; i++)
        {
            if (memeifiedArray[i] != 6 && !(i > 0 && memeifiedArray[i] == 7 && memeifiedArray[i - 1] == 6)) // skips if the current value is 6 or the previous value uis 6 when the current value is 7
            {
                while (j < arr.length && (arr[j] == 6 || arr[j] == 7)) // finds the next value that is not 6 or 7
                {
                    j++;
                }
                if (j < arr.length) // Avoids out of bounds error
                {
                    memeifiedArray[i] = arr[j]; // replaces the current value with the next value that is not 6 or 7
                    j++;
                }
            }
        }
        return memeifiedArray; // returns the memeified array
    }

    public static boolean nestedSequence(CString outer, CString inner)
    {
        boolean appear = false; // variable to return for whether inner appears in outer
        outer.sortAscending(); // sorts outer in ascending order
        inner.sortAscending(); // sorts inner in ascending order
        int[] outerArray = toNumerical(outer, 0); // gets the numerical array of outer
        int[] innerArray = toNumerical(inner, 0); // gets the numerical array of inner
        for (int i = 0; i < innerArray.length; i++) // checks if each value in inner appears in outer
        {
            appear = false;
            for (int j = 0; j < outerArray.length; j++) // loops through outer to see if the current value in inner appears in it
            {
                if (innerArray[i] == outerArray[j]) // if the value appears, sets appear to true
                {
                    appear = true;
                }
            }
            if (!appear) // if any value in inner does not appear in outer this returns false
            {
                return false;
            }
        }
        return true; // if all values in inner appear in outer, returns true
    }

    public static CString decrypt(CString str)
    {
        String[] arr = str.getCString();
        int[] nums = toNumerical(str, 0); // converts to numerical representation
        int clumps = 0; // varaiable for counting the clumps
        int i = 0; // placeholder variable for iterating through the array
        while (i < nums.length - 1)
        {
            if (nums[i] == nums[i + 1])
            {
                clumps++;
                int val = nums[i]; // variable to store the value of the clump to detect if still in the clump
                while (i < nums.length && nums[i] == val) // while still in the clump, skip through the clump
                {
                    i++;
                }
            }
            else
            {
                i++;
            }
        }
        String[] shifted = new String[arr.length]; // creates a new array to store the shifted string characters
        for (int j = 0; j < arr.length; j++)
        {
            int currentCharNum = (int) arr[j].charAt(0); // gets the ascii value of the current character
            currentCharNum -= clumps; // shifts the character back by the number of clumps
            Character.toString(currentCharNum); // converts the shifted ascii value back to a string
            shifted[j] = String.valueOf((char) currentCharNum); // stores the shifted character in the new array
        }
        String[] reversed = new String[shifted.length]; // creates a new array to store the reversed string
        for (int j = 0; j < shifted.length; j++)
        {
            reversed[j] = shifted[shifted.length - 1 - j]; // fills the new array with the characters in reverse order
        }
        String result = ""; // variable to store the final result
        for (int j = 0; j < reversed.length; j++)
        {
            result += reversed[j]; // concatenates (adds on) the characters in the reversed array to get the final result
        }
        return new CString(result); // returns the final result as a new CString
    }

}