package test;

import java.text.DecimalFormat;
import java.util.Scanner;

public class TestBigInteger {
	  public static void main(String[] args) throws CloneNotSupportedException{
	        DecimalFormat df = new DecimalFormat("0");//ENTRYSIZE = 1;
	        Scanner cin = new Scanner(System.in);       
	        int n = cin.nextInt();
	        int mag[] = {2};
	        FastInt A = new FastInt(1,mag);
	        for(int i = 0; i < n;i++) {
	            A.mag = cutLead0(A.mag);
	            System.out.print(A.mag[A.mag.length-1]);
	            for(int j = A.mag.length-2; j >= 0; j--)
	                System.out.print(df.format(A.mag[j]));
	            System.out.println();
	            FastInt B = (FastInt)A.clone();
	            subOne(B.mag);
	            A = A.multiply(B);
	            addOne(A.mag);               
	        }
	    }
	    
	    protected static int[] cutLead0(int []A){
	        int i;
	        for(i = A.length-1; i >= 0; i--)
	            if(A[i] != 0) break;
	        int []B = new int[i+1];
	        System.arraycopy(A, 0, B, 0, i+1);
	        return B;
	    }
	    
	    protected static void addOne(int []A){
	       int carry = 1;
	       int po = (int)Math.pow(10, FastInt.ENTRYSIZE);
	       for(int i = 0; i < A.length; i++){
	           A[i] += carry;
	           carry = A[i]/po;
	           A[i] %= po;
	       }
	       if(carry > 0){
	           int []B = new int [A.length+1];
	           for(int i = 0; i < A.length; i++) B[i] = A[i];
	           B[A.length] = carry;
	           A = B;
	       }
	    }
	    
	    protected static void subOne(int []A){
	       int po = (int)Math.pow(10, FastInt.ENTRYSIZE);
	       A[0] -= 1;
	       for(int i = 1; i < A.length; i++){
	           if(A[i-1] >= 0) break;
	           A[i-1] = (A[i-1] + po) % po;
	           A[i]--;
	       }
	    }
	}
	class FastInt implements Cloneable{
	    public final static int ENTRYSIZE = 1;          //Bits per entry in mag
	    protected int[] mag;                            //magnitude in little-endian format
	    protected int signum = 0;                       //neg = -1,0 = 0,pos = 1                
	    public static int logN = 0;  
	    public static int []reverse;
	    public final static int MAXN = 134217728;       //Maximum value for n 2^27
	    protected final static long P = 2013265921;     // The prime 15*2^{27}+1
	    protected final static int OMEGA = 440564289;   //Root of unity 31^{15}mod P
	    protected final static int TWOINV = 1006632961; //2^{-1}mod P

	    @Override
	    public Object clone(){
	        FastInt C;
	        try{
	            C = (FastInt)super.clone();
	        }
	        catch(CloneNotSupportedException ex){
	            return null;
	        }
	        C.mag = (int[])mag.clone();
	        return C;
	    }
	    
	    public FastInt(int signnum, int[] mag) {
	        this.signum = signnum;
	        this.mag = (int[])mag.clone();
	    }
	     
	    //FFT算法迭代实现的支持方法
	    protected static int makePowerOfTwo(int length) {
	        int i;
	        for (i = 1; i < length; i *= 2);
	        return i;
	    }

	    protected static int[] rootsOfUnity(int n) {      //assumes n is power of 2
	        int nthroot = OMEGA;
	        for (int t = MAXN; t > n; t /= 2)               //Find prim. nth root of unity        
	            nthroot = (int) (((long) nthroot * nthroot) % P);
	        int[] roots = new int[n];
	        int r = 1;                                      //r will run through all nth roots of unity
	        for (int i = 0; i < n; i++) {
	            roots[i] = r;
	            r = (int) (((long) r * nthroot) % P);
	        }
	        return roots;
	    }

	    protected static int[] padWithZeros(int[] mag, int n) {
	        int[] tmp = new int[n];
	        for (int i = 0; i < mag.length; i++) {
	            tmp[i] = mag[i];
	        }
	        for (int i = mag.length; i < n; i++) {
	            tmp[i] = 0;
	        }
	        return tmp;
	    }

	    protected static void reverseRoots(int[] root) {//root[i]^-1
	        int temp;
	        for (int i = 1; i < (root.length + 1) / 2; i++) {
	            temp = root[i];
	            root[i] = root[root.length - i];
	            root[root.length - i] = temp;
	        }
	    }
	    
	    protected static void bitreverse(int[] A, int logN) {
	        int[] temp = new int[A.length];
	        for (int i = 0; i < A.length; i++) {
	            temp[reverse[i]] = A[i];
	        }
	        for (int i = 0; i < A.length; i++) {
	            A[i] = temp[i];
	        }
	    }
	    
	    protected static int[] reverseArray(int n, int logN) {
	        int[] result = new int[n];
	        for (int i = 0; i < n; i++) {
	            result[i] = reverse(i, logN);
	        }
	        return result;
	    }

	    protected static int reverse(int N, int logN) {
	        int bit = 0;
	        int result = 0;
	        for (int i = 0; i < logN; i++) {        //二进制形式的倒数
	            bit = N & 1;
	            result = (result << 1) + bit;
	            N = N >>> 1;
	        }
	        return result;
	    }
	    
	    protected static int modInverse(int n) {    //assume n is power of two
	        int result = 1;
	        for (long twoPower = 1; twoPower < n; twoPower *= 2) { //n = 2^t
	            result = (int) (((long) result * TWOINV ) % P);
	        }
	        return result;
	    }
	    
	    protected static int logBaseTwo(int n) {
	        int i = 0;
	        for(i = 0; n > 1 ; i++)  n >>= 1;
	        return i;
	    }
	    
	    public FastInt multiply(FastInt val) {
	        int n = makePowerOfTwo(Math.max(mag.length, val.mag.length)) * 2;
	        logN = logBaseTwo(n);                       //log of n base 2
	        reverse = reverseArray(n, logN);            // initialize reversal lookup table
	        int signResult = signum * val.signum;
	        int[] A = padWithZeros(mag, n);             // copies mag into A padded w/0's
	        int[] B = padWithZeros(val.mag, n);         //copies val.mag into B padded @/0's
	        int[] root = rootsOfUnity(n);               //creates all n roots of unity
	        FFT(A, root, n);                            //leaves FFT result int A
	        FFT(B, root, n);                            //leaves FFT result int B
	        for (int i = 0; i < n; i++) {
	            A[i] = (int) (((long) A[i] * B[i]) % P); //componet -wise multiply
	        }
	        reverseRoots(root);                         //reverse root to creat inverse roots
	        inverseFFT(A, root, n);                     //leaves inverse FFT resul in A
	        propagateCarries(A);
	        return new FastInt(signResult, A);
	    }        
	    //FFT 算法的迭代实现
	    public static void FFT(int[] A, int[] root, int n) {
	        int prod, term, index;                              //valus for common subexpressions
	        int subsize = 1;                                    //subproblem size;
	        bitreverse(A, logN);
	        for (int lev = 1; lev <= logN; lev++) {
	            subsize *= 2;
	            for (int base = 0; base < n - 1; base += subsize) {//iterate subproblems
	                int j = subsize / 2;
	                int rootIndex = A.length / subsize;
	                for (int i = 0; i < j; i++) {
	                    index = base + i;
	                    prod = (int) (((long) root[i * rootIndex] * A[index + j]) % P);
	                    term = A[index];
	                    A[index + j] = (int) (((long) term + P - prod) % P);
	                    A[index] = (int) (((long) term + prod) % P);
	                }
	            }
	        }
	    }

	    public static void inverseFFT(int[] A, int[] root, int n) {
	        int inverseN = modInverse(n); //n^(-1)
	        FFT(A, root, n);
	        for (int i = 0; i < n; i++) {
	            A[i] = (int) (((long) A[i] * inverseN) % P);
	        }
	    }
	    
	    protected static void propagateCarries(int[] A) {
	        int carry = 0;
	        int po = (int)Math.pow(10,ENTRYSIZE);
	        for (int i = 0; i < A.length; i++) {
	            carry = A[i]; A[i] = 0;
	            for(int j = 0; carry != 0; j++){               
	                A[i+j] += carry%po; 
	                carry /= po;
	            }
	        }//convert A to right no. of bits/entry
	     }
}
