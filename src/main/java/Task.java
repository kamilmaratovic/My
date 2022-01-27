import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task {
    public Task() {
    }

    public static void main(String[] args) {
        String a = "I like learning java";
        reverse(a);
        reverse1(a);
    }

    public static void reverse (String sentence){
        String[] words1 = sentence.split(" ");
        String reverse = "";
        for (int i = words1.length-1; i>=0; i--){
            reverse+=words1[i]+" ";
        }
        System.out.println(reverse);
    }

    public static void reverse1 (String sentence){
        List<String> words = Arrays.asList(sentence.split(" "));
        List<String> sent = new ArrayList<>();

        for(int i = words.size()-1; i>=0; i--){
            String a = words.get(i);
            sent.add(a);
        }
        for(String v: sent){
            if(v!=sent.get(words.size()-1)){
                System.out.print(v+" ");
            } else{
                System.out.println(v);
            }
        }
        }


    }




