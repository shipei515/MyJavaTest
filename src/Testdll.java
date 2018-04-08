public class Testdll 
{ 
         static 
         { 
                System.loadLibrary("testag"); 
         } 

         public native static int get(); 

         public native static void set(int i); 

         public static void main(String[] args) 
         { 

                Testdll test = new Testdll(); 
                test.set(10); 
                System.out.println(test.get()); 
         } 
} 