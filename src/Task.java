public class Task {
    public static void main(String[] args) {
        String str1 = "KamilSukaev+1";
        String str2 = "1Sukaev+1";


        if (str1.endsWith(str2)){
            System.out.println("true");
        } else{
            System.out.println("false");
        }

    }

    private static boolean isAppear(String str1, String str2) {
        StringBuilder builder1 = new StringBuilder(str1);
        StringBuilder builder2 = new StringBuilder(str2);
      String str11 = builder1.reverse().toString();

      String str22 = builder2.reverse().toString();
        for (int i = 0; i<str11.length()-1; i++){
                if (str11.charAt(i)==str22.charAt(i) && str11.length()>str22.length()){
                }

            }
        return false;

            }




        }

