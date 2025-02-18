package Exercise2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SumOfIntegers {

    public static void main(String[] args){
        int[] A = {30, 909, 3190, 99, 3990, 9009, 999, 9009};

        int sum = solution(A);

        System.out.println("Max sum =>"+sum);

    }

    public static int firstDigit(int n) 
    { 
         while (n >= 10)  
            n /= 10;       
        return n; 
    }
    
    public static int solution(int[] A){    
        List<Integer> sumList = new ArrayList<Integer>();

        for(int i = 0; i < A.length-1; i++){
            for(int j= i+1; j<= A.length-1; j++){                
                
                int f1 = firstDigit(A[i]);
                int f2 = firstDigit(A[j]);
                int l1 = A[i]%10;
                int l2 = A[j]%10;
                if (f1 == f2 && l1 == l2){
                    sumList.add(A[i]+A[j]);
                }
            }
        }

        if(sumList.isEmpty()){
            return -1;            
        }else {
            return Collections.max(sumList);   
        }          

    }

}
