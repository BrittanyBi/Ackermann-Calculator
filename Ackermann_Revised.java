// Ackermann Value Calculator
// by Brittany Bianco
// 
// This program is meant to be called from outside itself,
// and contains two methods of calculating Ackermann values.
// The first calculates them raw - with no optimization.
// The second calculates them using dynamic programming
// techniques: it stores all previously calculated values
// to a table, significantly speeding up the calculation of
// each value. This program also counts the number of accesses
// to each method and to the table, to provide the exact increase
// in efficiency of calculation.
//

public class Ackermann_Revised{
   private int[][] ackValues;
   public int count,             // records the number of calc() accesses
              optCount,          // records the number of optCalc() accesses
              tableCount,        // records the number of table accesses
              outOfBoundsCount, 
              yMax;
   
   //--------------------------------------------------------------------------
   
   public Ackermann_Revised(){
      ackValues = new int[5][101];
      count = optCount = tableCount = outOfBoundsCount = yMax = 0;
   }
   
   //--------------------------------------------------------------------------
   
   public void reset(){
   // This method resets the variable values so that 
   // the driver's loop doesn't accumulate them.
      
      count = optCount = tableCount = outOfBoundsCount = yMax = 0;
   }
   
   //--------------------------------------------------------------------------
   
   public int calc(int x, int y){
   // This method calculates an Ackermann value from 
   // scratch based on the numbers it receives.
      
      ++count;
      
      if(x == 0){
         return (y+1);
      } // end if
      
      if(y == 0){
         return calc(x-1, 1);
      } // end if
      
      return calc(x-1, calc(x, y-1));
   }
   
   //--------------------------------------------------------------------------
   
   public int optCalc(int x, int y){
   // This method resets all values in the table to 0.
   // It then calls the optimized version of calc().
      
      for(int i = 0; i < 5; i++){
         for(int k = 0; k < 101; k++){
            ackValues[i][k] = 0;
         } //end for(k) loop
      } //end for(i) loop
      
      return tableCalc(x, y);
   }
   
   //--------------------------------------------------------------------------
   
   private int tableCalc(int x, int y){
   // This method uses dynamic programming 
   // to calculate Ackermann values efficiently.
      
      ++optCount;
      
      if(y > yMax){
         yMax = y;
      } // end if
      
      if(y <= 100){
      
         ++tableCount;       // For checking the value
         if(ackValues[x][y] != 0){
            ++tableCount;    // For retrieving the value
            return ackValues[x][y];
         } // end if
         
         if(x == 0){
            ++tableCount;    // For setting the value
            return ackValues[x][y] = (y+1);
         } // end if
         
         else if(y == 0){
            ++tableCount;    // For setting the value
            return ackValues[x][y] = tableCalc(x-1, 1);
         } //end else if
         
         else{
            ++tableCount;    // For setting the value
            return ackValues[x][y] = tableCalc(x-1, tableCalc(x, y-1));
         } // end else
         
      }
      
      else{ // out of table bounds
         ++outOfBoundsCount;
         
         if(x == 0){
            return (y + 1);
         } //end if
         
         else{
            return tableCalc(x-1, tableCalc(x, y-1));
         } // end else
         
      } // end else
      
   }
   
   //--------------------------------------------------------------------------
}