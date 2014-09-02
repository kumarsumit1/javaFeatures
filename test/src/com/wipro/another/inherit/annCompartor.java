package com.wipro.another.inherit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class annCompartor {


   // private static HashMap<String,Second> msgs;

    public static void main(String[] args) 
    {           

    List<String> ls=new ArrayList<String>();


   ls.add("fourth");
   ls.add("2");
   ls.add("second");
   ls.add("first");
   ls.add("third");
   ls.add("1");

   Iterator it=ls.iterator();

   // before sorting
   while(it.hasNext())
   {
     String s=(String)it.next();

     System.out.println(s);
   }
   
   
		   
	Comparator<Object> tes= new Comparator<Object>()   {
       public int compare(Object o1, Object o2) 
       {
          if(o1 instanceof String && o2 instanceof String) 
          {
             String s_1 = (String)o1;
             String s_2 = (String)o2;

             return s_2.compareTo(s_1);
          } 
          return 0;    
       }
   };
   
   Collections.sort(ls, tes);

  /* Collections.sort(ls, new Comparator() 
                           {

                            public int compare(Object o1, Object o2) 
                            {
                                return -1;

                                // it can also return 0, and 1
                            }
                           }    
                    );
*/
System.out.println(" ");

//after sorting
   Iterator iti=ls.iterator();
   while(iti.hasNext())
   {
     String s=(String)iti.next();

     System.out.println(s);
   }

}


}
