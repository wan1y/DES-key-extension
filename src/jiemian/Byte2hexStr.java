package jiemian;

public class Byte2hexStr {
	char[] cha = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
	public char[] b2hdeal(int[] bt,int lenth) {
		int k=0;
		char[] hex=new char[lenth];
        for(int i=0;i<lenth;i++)
        {
          	 int total;
            	
           	 total=0;
           	 if(bt[i]==1)
           	 {
           		 total=total+8;
           	 }
           	 if(bt[i+1]==1)
           	 {
           		 total=total+4;
           	 }
           	 if(bt[i+2]==1)
           	 {
           		 total=total+2;
           	 }
           	 if(bt[i+3]==1)
           	 {
           		 total=total+1;
           	 }
        	 hex[k]=cha[total];
        	 k++;
        	 i=i+3;
        	 }
        return hex;
	}
}
