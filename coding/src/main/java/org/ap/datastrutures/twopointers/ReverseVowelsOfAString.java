package org.ap.datastrutures.twopointers;

public class ReverseVowelsOfAString {
    public String reverseVowels(String s) {
        char[] charArr = s.toCharArray();
        int start = 0, end = s.length()-1;
        while(start < end) {
            while(start < s.length() && !isVowel(charArr[start])) {
                start++;
            }

            while(end >= 0 && !isVowel(charArr[end])) {
                end--;
            }

            if(start < end) {
                swap(charArr, start,end);
                start++;
                end--;
            }
        }
        return new String(charArr);
    }

    public void swap(char[] charArray, int start, int end) {
        char temp = charArray[start];
        charArray[start] = charArray[end];
        charArray[end] = temp;
    }

    public boolean isVowel(char ch){
        if(ch=='a' || ch =='A' ||
                ch=='e' || ch =='E' ||
                ch=='i' || ch =='I' ||
                ch=='o' || ch =='O'||
                ch=='u' || ch =='U')
            return true;
        return false;
    }
}
