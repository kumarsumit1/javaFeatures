import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/*
 * @author:Sumit Kumar
 * http://www.vogella.com/tutorials/JUnit/article.html
 */

// TODO: Auto-generated Javadoc
class myClass{
	
	private int member;

	public myClass(int member) {
		this.member = member;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		
		return 3;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		myClass other = (myClass) obj;
		if (member != other.member)
			return false;
		return true;
	}
	
	
}

/**
 * The Class testmain.
 */
public class testmain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	     volatile int v=10;
	     transient float f=10.5f;
	  //   int native n=5;
	     static final short s=29;
	     public enum Day {

	         MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	     }
	public static void main(String[] args) {
     
		 System.out.println("hi");    
		 
		 TimeZone timeZone1 = TimeZone.getTimeZone("GMT+5:30"); //IST
		// TimeZone timeZone2 = TimeZone.getTimeZone("Europe/Copenhagen");
         Calendar calendar = new GregorianCalendar();
         calendar.setTimeZone(timeZone1);
		// long timeCPH = calendar.getTimeInMillis(timeZone1);
		// System.out.println("timeCPH  = " + timeCPH);
		 System.out.println("hour = " + calendar.get(Calendar.HOUR_OF_DAY));
		 System.out.println("hour = " + calendar.get(Calendar.MINUTE));
		 //return void();
		//	testmain ts=new testmain();
		//Integer n=ts.methodA(null);
		/*System.out.println(Day.valueOf("MONDAY"));
		
		Object er=new Error();
		Object ex=new RuntimeException();
		
		if(ex instanceof Exception)
			System.out.println("ok");*/
		/*//int i = 0;
		boolean t = true;
		boolean f = false, b;
		b = (t || ((i++) == 0));
		b = (f || ((i+=2) > 0));
		System.out.println(i);	
		double d=2.3;
		int m,j,k,l=0;
		k = l++;
		System.out.println(k);			
		j = ++k;
		System.out.println(k);		
		m = j++;
		System.out.println(m);	
		*/
		//cla.eat();
		/*myClass obj1= new myClass(3);
		myClass obj2= new myClass(7);
		
		Map lst=new HashMap();
		lst.put(obj1, 2);
		lst.put(obj2, 3);
		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
		System.out.println(lst.get(obj2));

*/		
//        ClassA ct=new ClassC();
//         if(strr instanceof String){
//        
//		System.out.println("ture");

}
	
	
	 private static void printed(int i,long l) {
		
		 System.out.println(i);
		System.out.println(l);
		
	}
	
	 Integer methodA(Integer a){
		 System.out.println("Number");
		 return new Integer((Integer)1);
		 }

	 void methodA(Number a){
		 System.out.println("Integer");
		 }
		 
	static void decrease1(int num) {
	     num--;
	    }

	  static void decrease2(short[] num) {
	        num[0]--;
	    }

}