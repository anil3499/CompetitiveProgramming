package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/add-binary/
public class AddBinary {
    public String addBinary(String a, String b) {
        int i=a.length()-1;
        int j = b.length()-1;

        char carry='0';
        String result = "";

        while(i>=0 && j>=0){
            char aChar = a.charAt(i);
            char bChar = b.charAt(j);
            Pair pair= sumBinary(aChar ,bChar, carry, result);
            result = pair.result;
            carry = pair.carry;
            i--;j--;
        }
        while(i>=0) {
            char aChar = a.charAt(i);
            Pair pair= sumBinary(aChar ,'0', carry, result);
            result = pair.result;
            carry = pair.carry;
            i--;
        }
        while(j>=0) {
            char bChar = b.charAt(j);
            Pair pair= sumBinary('0',bChar, carry, result);
            result = pair.result;
            carry = pair.carry;
            j--;
        }
        if(carry!='0')
            result = carry+result;
        return result;
    }

    class Pair{
        String result;
        char carry;
    }

    public Pair sumBinary(char aChar , char bChar, char carry, String result) {
        if(carry=='0' && aChar =='0' && bChar=='0' ) //000
            result = '0' + result;
        else if(carry=='1' && aChar =='1' && bChar=='1' ){ //111
            result = '1' + result;
            carry='1';
        }

        else if(carry=='0' && aChar =='0' && bChar=='1' )//001,010,100
        {   result = '1' + result;
            carry='0';
        }
        else if(carry=='0' && aChar =='1' && bChar=='0' )
        {   result = '1' + result;
            carry='0';
        }else if(carry=='1' && aChar =='0' && bChar=='0' )
        {   result = '1' + result;
            carry='0';
        }

        else if(carry=='0' && aChar =='1' && bChar=='1' ) //011,101,110
        {   result = '0' + result;
            carry='1';
        }else if(carry=='1' && aChar =='1' && bChar=='0' )
        {   result = '0' + result;
            carry='1';
        }else if(carry=='1' && aChar =='0' && bChar=='1' )
        {    result = '0' + result;
            carry='1';
        }
        Pair pair =new Pair();
        pair.result = result;
        pair.carry = carry;
        return pair;
    }
}
