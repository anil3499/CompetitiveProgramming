package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/text-justification/description/
//https://www.youtube.com/watch?v=TzMl4Z7pVh8
public class TextJustification {
    public List<String> fullJustify1(String[] words, int maxWidth) {
    List<String> result = new ArrayList<>();
    List<String> currLine = new ArrayList<>();
    int lengthLine = 0;
    int i=0;
    while(i<words.length){
        //case-1 line is complete  harder case
        if((lengthLine +  currLine.size() + words[i].length()) > maxWidth){
            //line is complete
            //calculate space
            int extraSpace = maxWidth - lengthLine;
            int spaces = extraSpace / Math.max(1,currLine.size() -1);
            int remainder = extraSpace % Math.max(1,currLine.size() -1);
            //addspaces in same array
            for(int j=0;j<Math.max(1,currLine.size()-1); j++){
                int spaceCount = spaces;
                if(remainder>0) {
                    spaceCount+=1;
                    remainder--;
                }
                while(spaceCount>0){
                    currLine.set(j, currLine.get(j)+" ");
                    spaceCount--;
                }
                //System.out.println(currLine.get(j));
            }

            //add line to result and reinitialize line
            System.out.println(" len "+ currLine.size());
            String res = "";
            for(int l=0;l<currLine.size();l++){
                res+=currLine.get(l);
                //System.out.println(currLine.get(l));
            }
            result.add(res);
            currLine =  new ArrayList<>();
            lengthLine=0;
            continue;
        }
        //case-2 line is not complete
        //currLine.add(words[i]);
        currLine.add(words[i]);
        lengthLine =  lengthLine + words[i].length(); //not including spaces
        i++;
    }
    //Handeling last line
    //String lastLine =String.join(" ", currLine);//since its last line add spaces as trailing space
    String lastLine = "";
    for(int l=0;l<currLine.size();l++){
        if(l!=currLine.size()-1)
            lastLine=lastLine + currLine.get(l) + " ";
        else
            lastLine=lastLine + currLine.get(l);
    }
    int trailingSpace = maxWidth - lastLine.length();
    while(trailingSpace>0){
        lastLine+=" ";
        trailingSpace--;
    }
    result.add(lastLine);
    return result;
}
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        String line="";
        int lineSize=0;
        int from=0;
        for(int i=0; i<words.length;) {
            //System.out.println("i "+i);
            int wordLength = words[i].length();
            int space= i -from;
            if(lineSize + space + wordLength == maxWidth){
                addLineFromArray(words, from, i, lineSize+ wordLength, maxWidth, result);
                space=0;
                lineSize=0;
                from =i+1;
                i++;
            } else if(lineSize + space + wordLength > maxWidth) {
                addLineFromArray(words, from, i-1, lineSize, maxWidth, result);
                from = i;
                lineSize=0;
            } else {
                if(i==words.length-1) {
                    addLineFromArray(words, from, i, lineSize+wordLength, maxWidth, result);
                    break;
                }
                lineSize+=wordLength;
                i++;
            }
        }
        return result;
    }
    public void addLineFromArray(String[] words ,int from, int to, int lineSize, int maxWidth, List<String> result){
        //System.out.println(" from " +from +" to "+ to + " lineSize "+lineSize);
        int noOFSpacePlaces = to-from;
        int noOfSpaceWeHave = maxWidth - lineSize;
        //3/2 4/2 5/22 6/2
        int noOFSpaceBetweenStrs;

        if(noOFSpacePlaces ==0) noOFSpaceBetweenStrs=0;
        else
            noOFSpaceBetweenStrs = noOfSpaceWeHave/noOFSpacePlaces;

        int reamimingSpaces= noOfSpaceWeHave - (noOFSpaceBetweenStrs * noOFSpacePlaces);

        //System.out.println("noOFSpacePlaces: "+ noOFSpacePlaces + " noOfSpaceWeHave "+noOfSpaceWeHave+ " noOFSpaceBetweenStrs "+noOFSpaceBetweenStrs + "              reamimingSpaces "+ reamimingSpaces);

        String line= "";
        for(int i=from; i<=to; i++){
            if(to==words.length-1 || from==to){
                if(i!=to) {
                    line+=words[i];
                    line+=" ";
                    noOfSpaceWeHave--;
                }else {
                    line+=words[i];
                    while(noOfSpaceWeHave>0) {
                        line+=" ";
                        noOfSpaceWeHave--;
                    }
                }
            }else {
                line += words[i];
                if(i==to) break;
                int spaceCount;
                if(reamimingSpaces>0){
                    spaceCount = noOFSpaceBetweenStrs+1;
                    reamimingSpaces--;
                } else
                    spaceCount = noOFSpaceBetweenStrs;
                while(spaceCount>0){
                    line+=" ";
                    spaceCount--;
                }
            }
        }
        result.add(line);
    }
}
