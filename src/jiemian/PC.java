package jiemian;
public class PC {
	private int[] LFT={1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1};
	private int[] PC1={                               
			57, 49, 41, 33, 25, 17,  9,  1, 58, 50, 42, 34, 26, 18,
			10,  2, 59, 51, 43, 35, 27, 19, 11,  3, 60, 52, 44, 36,
			63, 55, 47, 39, 31, 23, 15,  7, 62, 54, 46, 38, 30, 22,
			14,  6, 61, 53, 45, 37, 29, 21, 13,  5, 28, 20, 12,  4 };
	private int[] PC2= {                          
			14, 17, 11, 24,  1,  5,  3, 28, 15,  6, 21, 10,
			23, 19, 12,  4, 26,  8, 16,  7, 27, 20, 13,  2,
			41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48,
			44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 };
	char[] cha = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    char[] L=new char[112];   
    char[] R=new char[112]; 
	int[] k_new_bit=new int[56];
	int[] sub_key=new int[768];
	
	public void deal(int k_bit[]){
		String[] hv=new String[28];
		for(int i=0;i<PC1.length;i++){
			k_new_bit[i]=k_bit[PC1[i]-1];
			}
		
		
		/**************************/
		int[] c0=new int[28];
		int[] d0=new int[28];
		int k=0;
		int s=0;
		System.arraycopy(k_new_bit,0,c0,0,28);
     System.arraycopy(k_new_bit,28,d0,0,28);
     for(int i=0;i<16;i++){
         int[] c1=new int[28];
         int[] d1=new int[28];
         if(LFT[i]==1){
             System.arraycopy(c0,1,c1,0,27);
             c1[27]=c0[0];
             System.arraycopy(d0,1,d1,0,27);
             d1[27]=d0[0];
         }else if(LFT[i]==2){
             System.arraycopy(c0,2,c1,0,26);
             c1[26]=c0[0];
             c1[27]=c0[1];

             System.arraycopy(d0,2,d1,0,26);
             d1[26]=d0[0];
             d1[27]=d0[1];
         }else{
             System.out.println("LFT Error!");
         }
         int[] tmp=new int[56];
         System.arraycopy(c1,0,tmp,0,28);
         System.arraycopy(d1,0,tmp,28,28);
         for (int j=0;j<PC2.length;j++){
             sub_key[j+s]= tmp[PC2[j]-1];//pc2
         }
         s=s+48;
         c0=c1;
         d0=d1;
         for(int i1=0;i1<28;i1++)
         {
        	 int total;
        	 int total1;
        	 
        	 total=0;
        	 total1=0;
        	 if(c0[i1]==1)
        	 {
        		 total=total+8;
        	 }
        	 if(c0[i1+1]==1)
        	 {
        		 total=total+4;
        	 }
        	 if(c0[i1+2]==1)
        	 {
        		 total=total+2;
        	 }
        	 if(c0[i1+3]==1)
        	 {
        		 total=total+1;
        	 }
        	 
        	 
        	 if(d0[i1]==1)
        	 {
        		 total1=total1+8;
        	 }
        	 if(d0[i1+1]==1)
        	 {
        		 total1=total1+4;
        	 }
        	 if(d0[i1+2]==1)
        	 {
        		 total1=total1+2;
        	 }
        	 if(d0[i1+3]==1)
        	 {
        		 total1=total1+1;
        	 }
        	 L[k]=cha[total];
        	 R[k]=cha[total1];
        	 k++;
        	 i1=i1+3;
        }
     }
  }
}
