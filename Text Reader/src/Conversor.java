public class Conversor {

    public void Trans(String a){
        byte b;
        for(int i =0; i< a.length();i++){
            b = (byte)a.charAt(i);
            System.out.println(b);
        }

    }
}
