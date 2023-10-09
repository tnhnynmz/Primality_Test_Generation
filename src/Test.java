import java.util.Scanner;
import java.util.Iterator;
import java.util.Random;
import java.math.BigInteger;
public class Test {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
        Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		
        /** Make an object of FermatPrimality class **/
        /** Accept number **/
        
        /** Accept number of iterations **/
        /**/
        /*ITERASYON SAYISI SINIRLANACAK*/
        /**/
		
		while(true) {
       	 System.out.println("\nSelect Primality Testing Algorithm:\n0-Random Prime Genaration\n1-Trial Division\n2-Miller-Rabin Algorithm\n3-Fermat's Algorithm\n4-Solovay-Strassen Algorithm");
            int selection = scan.nextInt();
       	 	
       	 	
            if (selection > 4 || selection < 0) {
            	System.out.println("Selection Must Between 0-4 :");
            	selection = scan.nextInt();
            }
            
            else if (selection == 0) {
            		long rtime = System.nanoTime();
            		long rnumber = Math.abs(rand.nextLong());
                    while (!millerRabin(rnumber,3)) {
                    	rnumber = Math.abs(rand.nextLong());
                    }
                    rtime = System.nanoTime()-rtime;
                    System.out.println(rnumber+" In "+rtime+"ns");
            }
            
            else {
           	 System.out.println("\nEnter number\n");
                long num = scan.nextLong();
                if (selection == 1) {
               	long time = System.nanoTime();
                 	boolean p = TrialDivision(num);
                 	time = System.nanoTime()-time;
                 	System.out.println("Time Passed: "+ time);  
                     // To check if a number is a prime or not
                     if(p)
                         System.out.print("\n"+ num +" is prime for Trial\n");
                     else
                         System.out.print("\n"+ num +" is composite for Trial\n");
                 }
                 else {
                 	System.out.println("\nEnter number of iterations");
                     int k = scan.nextInt();
                     switch (selection) {
                     case 2:
                     	 /** check if prime **/
		
                   	  long time1 = System.nanoTime();
                         boolean prime = millerRabin(num, k);
                         time1 = System.nanoTime()-time1;
                         System.out.println("Time Passed: "+ time1); 
                         if (prime)
                             System.out.println("\n"+ num +" is prime for Miller\n");
                         else
                             System.out.println("\n"+ num +" is composite for Miller\n");
                         break;
                     case 3:
                   	  long time2 = System.nanoTime();
                     	boolean prime2 = fermat(num, k);
                     	time2 = System.nanoTime()-time2;
                     	System.out.println("Time Passed: "+ time2); 
                         if (prime2)
                             System.out.println("\n"+ num +" is prime for Fermat\n");
                         else
                             System.out.println("\n"+ num +" is composite for Fermat\n");
                         break;
                     case 4:
                   	  long time3 = System.nanoTime();
                     	boolean prime3 = SolovayStrassen(num, k);
                     	time3 = System.nanoTime()-time3;
                     	System.out.println("Time Passed: "+ time3); 
                         if (prime3)
                             System.out.println("\n"+ num +" is prime for SS\n");
                         else
                             System.out.println("\n"+ num +" is composite for SS\n"); 
                     	 break;
                     }         
                 }
            }
		}
		
		
		/*
		
		long numbers[] = {3486989953832791253L,
				4509339978131276597L,
				3471431205268879693L,
				8870130174609217141L,
				3471431205268879693L,
				3308677294897540229L,
				6676564343486897591L,
				6676564343486897591L,
				8525361941876014471L,
				7208461789750335523L

};
        long result = 0;
        for(int i = 0; i < numbers.length; i++) {
            long temp = System.nanoTime();
            fermat(numbers[i],1);
            temp = System.nanoTime()-temp;
            result += temp;
        }
        System.out.println(result/10);
        result = 0;
        for(int i = 0; i < numbers.length; i++) {
            long temp = System.nanoTime();
            millerRabin(numbers[i],1);
            temp = System.nanoTime()-temp;
            result += temp;
        }
        System.out.println(result/10);
        result = 0;
        for(int i = 0; i < numbers.length; i++) {
            long temp = System.nanoTime();
            SolovayStrassen(numbers[i],1);
            temp = System.nanoTime()-temp;
            result += temp;
        }
        System.out.println(result/10);
        result = 0;
        for(int i = 0; i < numbers.length; i++) {
            long temp = System.nanoTime();
            TrialDivision(numbers[i]);
            temp = System.nanoTime()-temp;
            result += temp;
        }
        System.out.println(result/10);*/
	}
	
	public static boolean fermat(long n, int iteration)
    {
        /** base case **/
        if (n == 0 || n == 1)
            return false;
        /** base case - 2 is prime **/
        if (n == 2)
            return true;
        /** an even number other than 2 is composite **/
        if (n % 2 == 0)
            return false;
        
        Random rand = new Random();
        for (int i = 0; i < iteration; i++)
        {
            long r = Math.abs(rand.nextLong());            
            long a = r % (n - 1) + 1;
            if (modPow(a, n - 1, n) != 1)
                return false;
        }
        return true;      
    }
	public static boolean millerRabin(long n, int iteration)
    {
        /** base case **/
        if (n == 0 || n == 1)
            return false;
        /** base case - 2 is prime **/
        if (n == 2)
            return true;
        /** an even number other than 2 is composite **/
        if (n % 2 == 0)
            return false;
 
        long s = n - 1;
        while (s % 2 == 0)
            s /= 2;
 
        Random rand = new Random();
        for (int i = 0; i < iteration; i++)
        {
            long r = Math.abs(rand.nextLong());            
            long a = r % (n - 1) + 1, temp = s;
            long mod = modPow(a, temp, n);
            while (temp != n - 1 && mod != 1 && mod != n - 1)
            {
                mod = mulMod(mod, mod, n);
                temp *= 2;
            }
            if (mod != n - 1 && temp % 2 == 0)
                return false;
        }
        return true;        
    }
	/** Function to calculate (a * b) % c **/
	public static long mulMod(long a, long b, long mod) 
    {
		//return ((a%mod)*(b%mod))%mod;
        return BigInteger.valueOf(a).multiply(BigInteger.valueOf(b)).mod(BigInteger.valueOf(mod)).longValue();
    }
    /** Function to calculate (a ^ b) % c **/
	private static long modPow(long base, long exponent, long mod) {
        int shift = 63; // bit position
        BigInteger result = BigInteger.valueOf(base); // (1 * 1) * base = base

        // Skip all leading 0 bits and the most significant 1 bit.
        while (((exponent >> shift--) & 1) == 0)
            ;

        while (shift >= 0) {
            result = result.multiply(result);
            result = result.mod(BigInteger.valueOf(mod));
            if (((exponent >> shift--) & 1) == 1) {
            	result = result.multiply(BigInteger.valueOf(base));
                result = result.mod(BigInteger.valueOf(mod));
            }
        }
        return result.longValue();
    }
	
    /*public static long modPow(long a, long b, long c)
    {
        long res = 1;
        for (int i = 0; i < b; i++)
        {
            res *= a;
            res %= c;
        }
        return res % c;
    }*/
	 // Function to check if a number is
	 // a prime number or not
	 static boolean TrialDivision(long N){
	  
	     // Initializing with the value 2
	 // from where the number is checked
	     int i = 2;
	  
	     // Computing the square root of
	 // the number N
	     int k =(int) Math.ceil(Math.sqrt(N));
	  
	     // While loop till the
	 // square root of N
	     while(i<= k){
	  
	         // If any of the numbers between
	     // [2, sqrt(N)] is a factor of N
	     // Then the number is composite
	         if(N % i == 0)
	             return false;
	         i += 1;
	     }
	  
	     // If none of the numbers is a factor,
	 // then it is a prime number
	     return true;
	 }
	 
	 
	 
	 /** Function to calculate jacobi (a/b) **/
	 public static long Jacobi(long a, long b)
	    {
	        if (b <= 0 || b % 2 == 0)
	            return 0;
	        long j = 1L;
	        if (a < 0)
	        {
	            a = -a;
	            if (b % 4 == 3)
	                j = -j;
	        }
	        while (a != 0)
	        {
	            while (a % 2 == 0)
	            {
	                a /= 2;
	                if (b % 8 == 3 || b % 8 == 5)
	                    j = -j;
	            }
	 
	            long temp = a;
	            a = b;
	            b = temp;
	 
	            if (a % 4 == 3 && b % 4 == 3)
	                j = -j;
	            a %= b;
	        }
	        if (b == 1)
	            return j;
	        return 0;
	    }
	    /** Function to check if prime or not **/
	    public static boolean SolovayStrassen(long n, int iteration)
	    {
	        /** base case **/
	        if (n == 0 || n == 1)
	            return false;
	        /** base case - 2 is prime **/
	        if (n == 2)
	            return true;
	        /** an even number other than 2 is composite **/
	        if (n % 2 == 0)
	            return false;
	 
	        Random rand = new Random();
	        for (int i = 0; i < iteration; i++)
	        {
	            long r = Math.abs(rand.nextLong());            
	            long a = r % (n - 1) + 1;
	            long jacobian = (n + Jacobi(a, n)) % n;
	            long mod = modPow(a, (n - 1)/2, n);
	            if(jacobian == 0 || mod != jacobian) 
	                return false;
	        }
	        return true;        
	    }
	    static long power(long x, long y, long p) {
	         
	    	long res = 1; // Initialize result
	         
	        //Update x if it is more than or
	        // equal to p
	        x = x % p;
	 
	        while (y > 0) {
	             
	            // If y is odd, multiply x with result
	            if ((y & 1) == 1)
	                res = (res * x) % p;
	         
	            // y must be even now
	            y = y >> 1; // y = y/2
	            x = (x * x) % p;
	        }
	         
	        return res;
	    }
	    
    /*
    public static long calc(long a, long b, long c) {
    	long res = (long) Math.pow(a, b);
    	return res%c;
    }*/
}
