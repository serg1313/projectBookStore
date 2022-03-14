package Task_3._1;

public class TaskFirst {

    public static void main(String[] args){
        int a= (int)(Math.random()*1000);
        int b=a%10;     //895/10 = 5
        int c=(a/10)%10; //895/10 = 89 89%10 = 9
        int d=(a/100)%10; //895/100=8 8%10 = 8
        if(b >= c & b>d || b > c & b >= d){
            System.out.println("В числе "+a+" наибольшая цифра " +b);
        }
        else {
            if (c > b & c >= d) {
                System.out.println("В числе "+a+" наибольшая цифра " +c);
            }
            else {
                System.out.println("В числе "+a+" наибольшая цифра " +d);
            }
        }
    }
}
