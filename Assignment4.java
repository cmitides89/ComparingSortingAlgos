
/*************************************************************************
 *
 *  Pace University
 *
 *  Course: CS 608 Algorithms and Computing Theory
 *  Author: Constantin Mitides
 *  Collaborators: NONE
 *  References: Textbook pesudocode 
 *
 *  Assignment: 4
 *  Problem: Sorting algorithims: Selection sort, Quicksort, random pivot quicksort
 *  Description: Implementation of the following algorithims: Selection sort, quicksort, and a quicksort with a random piviot
 *
 *  Input: Array of unsorted numbers
 *  Output: Array of sorted numbers
 *
 *  Visible data fields:
 *  COPY DECLARATION OF VISIBLE DATA FIELDS HERE
 * 
 *
 *  Visible methods:
 *  COPY SIGNATURE OF VISIBLE METHODS HERE
 * public static int[] selectionsort(int[] myarray)
 * 
 * public static quickSortPivotFirst(int[], first index, last index)
 * 
 * public static randomizedQuickSort(int[], first index, last index)
 * 
 * public static int partition(int[] myArray, int low, int high)
 * 
 * public static int randomizedPartition(int[] myArray, int low, int high)
 *
 *
 *   Remarks
 *   -------
 *
 *   CONCLUSION: 
 *      Are the measurements significantly different? Why?
         SELECTION SORT
 *        I would have expected for Selection sort increasing order would take the same amount of time as
 *        Decreasing order since Selection's best and worst case are expected to remain O(n^2), however 
 *        both decreasing order and random generated numbers seem to both be in-line with O(n^2) runtime. With Selection sort, we are essentially running
          Quicksort's worst running time because the "piviot" is always the first in the array.

        QUICKSORT
          The largest difference I noticed is between quicksort and randomized quicksort, when the pivot is the 
          first number in the array (quicksort) it takes O(n^2) time for increasing and decreasing which makes sense
          since the way quicksort pivot operates is by splitting the digits between smallest and largest of the pivot.
          if we chose 0 as our piviot or 10,000 as our piviot our recursion tree would be skewed
          which would take very long, This is the worst case for quicksort Our worst case scenarios are choosing max or min as piviot.
          Additionally, having the numbers in order or in reverse order (which are our first two tests) are worse cases as well which is why we 
          get a run time in O(n^2), The random numbers seem to give us O(n log n) which is what we would expect from an average case.

        RANDOMIZED QUICKSORT - Dear professor, i accidentally additionally did Randomized Quicksort and decided to leave it, I hope this is okay.

          This algorithim seems to have the best running time of all of them the differences between increasing, decreasing and random
          look like O(lg n) as the numbers dont even increase by a digit. This is probably because our piviot is chosen at random decreasing the 
          likelyhood of having a piviot that is going to give us a skewed balance between the piviot is very small. 

        RANDOMIZED MEDIAN OF 3
            This algorithim remains as O(lg n) when we compare the increasing order to decreasing order and the random numbers. Decreasing order took the least
            amount of time which I suspected would not be the case, I thought the random numbers would take less time, however the difference is only by roughly 600,000 nanoseconds
            which is a very small increase. Since we are taking a median of random values i think it is possible that if I ran this algorithim a few more times i could
            likely also get a similar outcome to randomized quicksort which is smaller digits. 
 *
 *
 *  In the following measurments n = 10,000 when n = 1,000,000 There is a stack overflow for Quicksort.
 * ----------------------------------------------------------------
 * Version   | increasing order | decreasing Order | random       |
 * ----------------------------------------------------------------
 * selection |                  |                  |
 *           | 486,692          | 34,802,369       | 19,425,133
 * sort      |                  |                  |
 *-----------------------------------------------------------------
 * quicksort | 33,827,529       | 71,359,564       | 2,866,266
 *-----------------------------------------------------------------
 * randomized|                  |                  |
 *           | 495,138          | 533,584          | 793,095
 * quicksort |                  |                  |
 * ----------------------------------------------------------------
 * Randomized|                  |                   |
 * median of | 8,380,082        | 1,457,166         | 2,023,371
 * 3         |                  |                   |
 *************************************************************************/
import java.util.Random;
import java.util.Arrays;

public class Assignment4{
    public static Random random = new Random();

    public static void main(String args[]){

        int arraySize = 10000;
        int[] myArray = populateArray(arraySize);
        // System.out.println("sorting selection sort array");

        // // //regular sort ascending order
        long startTime = System.nanoTime();
        int[] sortedArray = selectionSort(myArray);
        System.out.println("sorting selection sort increasing array took "+ (System.nanoTime()- startTime) +" nanoseconds");

        //random order
        myArray = populateArrayRandomly(arraySize);
        System.out.println("sorting selection sort array randomly");

        startTime = System.nanoTime();
        sortedArray = selectionSort(myArray);
        System.out.println("sorting selection sort array randomly took " + (System.nanoTime() - startTime) + " nanoseconds");

        //reverse order
        myArray = populateArrayReverse(arraySize);
        System.out.println("sorting selection sort array reversed order");

        startTime = System.nanoTime();
        sortedArray = selectionSort(myArray);
        System.out.println("sorting selection sort array reversed order took " + (System.nanoTime() - startTime) + " nanoseconds");

        


//--------------------------------------------END OF SELECTION SORT NOW ENTERING QUICK SORT TERRITORY ------------------------------------------------------


        //ascending order
        int[] myQuickSortArray = populateArray(arraySize);
        int maxVal = (myQuickSortArray.length - 1);

        System.out.println("sorting quicksort array");
        startTime = System.nanoTime();
        quickSortPivotFirst(myQuickSortArray, 0, maxVal);
        System.out.println("sorting quicksort in ascending order took "+(System.nanoTime() - startTime)+" nanoseconds");

        //random order
        myQuickSortArray = populateArrayRandomly(arraySize);
        maxVal = (myQuickSortArray.length -1);
        System.out.println("sorting random quicksort array");

        startTime = System.nanoTime();
        randomizedQuickSort(myQuickSortArray, 0, maxVal);
        System.out.println("sorting quicksort in random order took " +(System.nanoTime() - startTime) +" nanoseconds");

        //populate reverse order
        myQuickSortArray = populateArrayReverse(arraySize);
        maxVal = (myQuickSortArray.length -1);

        startTime = System.nanoTime();
        quickSortPivotFirst(myQuickSortArray, 0, maxVal);
        System.out.println("sorting quicksort in reverse order took " +(System.nanoTime() - startTime) + " nanoseconds");

        // //--------------------------------------------END OF QUICKSORT SORT NOW ENTERING QUICK SORT RANDOM PIVOT TERRITORY ------------------------------------------------------
        myQuickSortArray = populateArray(arraySize);
        maxVal = myQuickSortArray.length -1;

        System.out.println("randomized quicksort");

        startTime = System.nanoTime();
        randomizedQuickSort(myQuickSortArray, 0, maxVal);
        System.out.println("Sorting randomized quicksort in ascending order took "+(System.nanoTime() - startTime)+" nanoseconds");

        myQuickSortArray = populateArrayRandomly(arraySize);
        maxVal = myQuickSortArray.length - 1;

        System.out.println("randomized quicksort random order");

        startTime = System.nanoTime();
        randomizedQuickSort(myQuickSortArray, 0, maxVal);
        System.out.println("Sorting randomized quicksort in random order took " + (System.nanoTime() - startTime)+ " nanoseconds");
        //printingFunction(myQuickSortArray);
        myQuickSortArray = populateArrayReverse(arraySize);
        maxVal = myQuickSortArray.length - 1;

        System.out.println("randomized quicksort reverse order");

        startTime = System.nanoTime();
        randomizedQuickSort(myQuickSortArray, 0, maxVal);
        System.out.println("Sorting randomized quicksort in ascending order took " + (System.nanoTime() - startTime)+ " nanoseconds");
        
    }

    public static int[] populateArray(int n){
        int[] myArray = new int[n];
        for(int i =0; i < n; i++){
            myArray[i] = i;
        }
        return myArray;
    }

    public static int[] populateArrayReverse(int n){
        int[] myArray = new int[n];
        int indexCounter = 0;

        for(int i = n; i > 0; i--){
            // System.out.println(i);
            myArray[indexCounter] = i;
            indexCounter = indexCounter + 1;
        }
        return myArray;
    }

    public static int[] populateArrayRandomly(int n){
        int[] myArray = new int[n];
        for(int i =0; i < n; i++){
            myArray[i] = random.nextInt(n);
        }
        return myArray;
    }
    //SELECTION SORT
    public static int[] selectionSort(int[] myArray){
        //repeatedly find the minimum element (if ascending order) from unsorted part and put it at beginning
        for(int j = 1; j < myArray.length; j++){
            int currentValue = myArray[j];
            int prevIndex = j -1;
            while( prevIndex >= 0 && myArray[prevIndex] > currentValue){
                myArray[prevIndex + 1] = myArray[prevIndex];
                prevIndex = prevIndex -1;
            }
            myArray[prevIndex+1] = currentValue;
        }
        return myArray;
    }

    //QUICKSORT PIVOT FIRST
    public static void quickSortPivotFirst(int[] myArray, int low, int high){
       if(low < high){
            int pivot = partition(myArray, low, high);
            quickSortPivotFirst(myArray, low, pivot -1);
            quickSortPivotFirst(myArray, pivot +1, high);
       }
    }
    public static int partition(int[] myArray, int low, int high){
        //take the last element as the pivot and place the pivot in the correct position 
        //in sorted array
        //place all larger elementsto the right and all smaller to the left.
        //this procedure rearranges the subarray 
        int pivot = myArray[high];

        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (myArray[j] <= pivot) {
                i++;
                int temp = myArray[i];
                myArray[i] = myArray[j];
                myArray[j] = temp;
            }
        }
        int temp = myArray[i + 1];
        myArray[i + 1] = myArray[high];
        myArray[high] = temp;
        return (i + 1);
    }

    static void printingFunction(int myArray[]){
        for(int i = 0; i<myArray.length; i++){
            System.out.print(" "+myArray[i] + " ");
        }
    }
    
    //QUICKSORT 3 POSITIONS FROM SUBARRAY (Randomized)
    public static int randomizedPartition(int[] myArray, int low, int high){
        //median of all three randomly chosen items
        // int i = medianOfrandom(low, high);
        int i = medianOfrandom(myArray);
        // System.out.println(i);
        //exchange [high] with [i]
        int temp = myArray[high];
        myArray[high] = myArray[i];
        myArray[i] = temp;
        return partition(myArray, low, high);
    }
    public static void randomizedQuickSort(int myArray[], int low, int high){
        if(low < high){
            int randomizedPart = randomizedPartition(myArray, low, high);
            randomizedQuickSort(myArray, low, randomizedPart - 1);
            randomizedQuickSort(myArray, randomizedPart + 1, high);

        }
    }
    // public static int partitionOfRandom(int[] myArray){
    //     int pivot = myArray[myArray.length - 1];
    //     int low = myArray[0];
    //     for(int j = 0; j < myArray.length -1; ){
    //         if(low <= pivot){
    //             int temp = low;
                
    //         }
    //     }
        
    //     return myPivot;
    // }
    // public static int medianOfrandom(int[] myArray){
    //     int low = 0;
    //     int high = (myArray.length - 1);
    //     int median = 0;
    //     int a = myArray[random.nextInt((high - low) + 1) + low];
    //     int b = myArray[random.nextInt((high - low) + 1) + low];
    //     int c = myArray[random.nextInt((high - low) + 1) + low];
    //     if(a < b && b < c ){
    //         median = b;    
    //     }else if(b < a && a < c){
    //         median = a;
    //     }else{
    //         median = c;
    //     }
    //     return median;
    // }
    public static int medianOfrandom(int low, int high){
        int a = random.nextInt( (high - low) + 1 ) + low;
        int b = random.nextInt( (high - low) + 1 ) + low;
        int c = random.nextInt( (high - low) + 1 ) + low;
        int[] medianArray = new int[3];
        medianArray[0] = a;
        medianArray[1] = b;
        medianArray[2] = c;
        Arrays.sort(medianArray);
        // System.out.println(medianArray[0]+" "+medianArray[1]+" "+medianArray[2]);
        return (medianArray[1]);

    }
}