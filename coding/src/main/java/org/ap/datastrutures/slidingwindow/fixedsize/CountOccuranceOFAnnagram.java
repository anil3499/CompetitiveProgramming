package org.ap.datastrutures.slidingwindow.fixedsize;


import java.util.HashMap;
import java.util.Map;

public class CountOccuranceOFAnnagram {
    //while j<length
    //    calculations
    //           windo = j-i+1
    //           if (window<K)
    //                j++
    // CALCULATIONS
    //            if (window == K) {
    //                ans  DERIVE FROM CALCULATIONS
    //                slide THE window BY I++, J++
    //            }
    //        }
    public static int count_of_annagram(String a, String b){
        int k = b.length(); // we can say window_size ill be size of substring
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        /* for string b == aab
        we know letters size means count of letters will be same
        so we just need to check all leters present in main string and number of occurances are also same
        First we will add counter of characters in map
        * */
        for (int i =0; i<b.length();i++){
            map.put(b.toCharArray()[i],map.getOrDefault(b.toCharArray()[i] ,0)+1);
        }

        int annagram_count  =0;
        int count_of_distinct_letr = map.size(); // these many counts of distinct caharacters should present in the string
        for (int i=0, j=0; j<a.length();){
            int window = j-i + 1; //window size formula

            //if for any of the char count become zero reduce distinct letter count or delete from the map
            if (map.get(a.toCharArray()[j])!=null)
                map.put(a.toCharArray()[j],map.get(a.toCharArray()[j])-1);

            if (map.get(a.toCharArray()[j]) ==0)
                count_of_distinct_letr--;

            if (window < k)
                j++;
            else { // if (window ==k) {
                if (count_of_distinct_letr == 0)
                    annagram_count++;

                //reverting the transaction which we made above while moving higher end of frame j
                // for moving lower end of fram i

                if (map.get(a.toCharArray()[i]) != null) {

                    map.put(a.toCharArray()[i], map.get(a.toCharArray()[i])+1);
                }
                if (map.get(a.toCharArray()[i])  == 1)
                    count_of_distinct_letr++;

                i++;
                j++;
            }
        }
    return annagram_count;
    }
    public static void main(String[] args) {
        //String a = "for";
        //String b = "" ;  // like "orf","rfo" will be annagram of for//no of laters will be same bt sequence can be changed called annagram
        String a = "aabaabaa";
        String b = "aaba";
        System.out.println(count_of_annagram(a,b));
        //ans hould be count 4
    }
}

/*

    int countOccurance(string s, string p){
        unordered_map<char, int> mp;
        int ans = 0;

        //storing the occ. of string p in the map
        for (auto &x : p){
            mp[x]++;
        }

        int count = mp.size();
        int k = p.size();
        int i=0, j=0;

        while (j < s.size()){
            //calculation part
            if (mp.find(s[j]) != mp.end()){
                mp[s[j]]--;
                if (mp[s[j]] == 0){
                    count--;
                }
            }

            //window length not achived yet
            if (j-i+1 < k){
                j++;
            }

            //window length achived, find ans and slide the window
            else if (j-i+1 == k){
                //finding the ans
                if (count == 0){
                    ans++;
                }
                if (mp.find(s[i]) != mp.end()){
                    mp[s[i]]++;
                    if (mp[s[i]] == 1){
                        count++;
                    }
                }

                //slide the window
                i++;
                j++;
            }
        }
        return ans;
    }

    int main(){
        string s, p;
        cin >> s;
        cin >> p;

        cout << countOccurance(s, p) << "\n";
        return 0;*/
