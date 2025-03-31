package org.ap.datastrutures.twopointers;

public class StringCompression {
    public int compress(char[] chars) {
        if(chars.length<=1)
            return chars.length;
        char prev = chars[0];
        int i = 0, j = 0;
        while(i < chars.length) {
            int groupLength = 1;
            while(i+groupLength < chars.length && chars[i] == chars[i+groupLength]) {
                groupLength++;
            }
            chars[j++] = chars[i];
            if(groupLength > 1) {
                String num = new String(groupLength + "");
                for(int k=0; k< num.length(); k++)
                    chars[j++] = num.charAt(k);
            }

            i+=groupLength;
        }
        return j;
    }
    public int compress1(char[] chars) {
        if(chars.length<=1)
            return chars.length;
        char prev = chars[0];
        int count = 1, i = 1, j = 0;
        while(i < chars.length) {
            while(i < chars.length && chars[i] == prev) {
                count++;
                i++;
            }
            chars[j++] = prev;
            if(count > 1) {
                String num = new String(count + "");
                for(int k=0; k< num.length(); k++)
                    chars[j++] = num.charAt(k);
            }

            if(i < chars.length) {
                prev = chars[i];
                count = 1;
                if(i== chars.length-1)
                    chars[j++] = prev;
            }
            i++;
        }
        return j;
    }
}
