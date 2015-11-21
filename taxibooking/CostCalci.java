
package RefactoredTBS.taxibooking;



public class CostCalci {

  
    static double tourCost(String IntialStaion, String FinalStaion) {
        int InilStation=IntialStaion.charAt(0)-'A'+1;
        int FnlStation=FinalStaion.charAt(0)-'A'+1;
        int Tmp_Var=Math.abs(FnlStation-InilStation);
        
        if(Tmp_Var>1){
            return 5*15 + 10*7.5 + (Tmp_Var-1)*15*7.5;
        }
        else
            return Tmp_Var*(5*15 + 10*7.5);
    }
    
}
