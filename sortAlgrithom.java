import java.util.Arrays;

public class sortAlgrithom {
    public static void main(String[] args) {


    }
    public static void swap(int[]arr,int start,int end){
        int temp = 0;
        temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

    }

    public static int getMaxLevel(int[]arr){
        int max = arr[0];
        int level = 0;
        for(int i=1;i<arr.length;i++){
            if(arr[i]>max){
                max = arr[i];
            }
        }
        while(max>0){
            max= max/10;
            level ++;
        }
        return level;

    }


    public static void HighBasicSort(int []arr,int start,int end,int level){
        if(start==end||start>end){
            return;
        }
        if(level<0){
            return;
        }
        int[]result = new int[end-start+1];
        int[]count = new int[11];
        count[10]=end-start+1;
        int division = (int)Math.pow(10,level-1);
        for(int i=start;i<=end;i++){
            int num = arr[i]/division %10;
            count[num]++;
        }
        for(int i =1;i<count.length-1;i++){
            count[i]=count[i]+count[i-1];
        }
        for(int i=end;i>=start;i--){
            int num = arr[i] / division % 10;
            result[count[num]-1] = arr[i];
            count[num]--;
        }
        System.arraycopy(result,0,arr,start,result.length);
        for(int i=0;i<10;i++){
            if(count[i+1]==count[i])
            {
                continue;
            }
            HighBasicSort(arr,start+count[i],start+count[i+1]-1,level-1);
        }

    }//高位优先



    public static void BasicSort(int[]arr,int level){
        int[]result = new int[arr.length];
        int[]count = new int[10];
        for(int i=0;i<level;i++) {
            int division = (int) Math.pow(10, i);
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / division % 10;
                count[num]++;
            }

            for (int m = 1; m < count.length; m++) {
                count[m] = count[m] + count[m - 1];
            }

            for (int n = arr.length - 1; n >= 0; n--) {
                int num = arr[n] / division % 10;
                result[count[num]-1] = arr[n];
                count[num]--;
            }
            System.arraycopy(result,0,arr,0,arr.length);
            Arrays.fill(count,0);
        }
    }


    public static void counterSort(int[]arr,int min,int max){
        int length = max-min+1;
        int []counter = new int[length];
        for (int j=0;j<length;j++){
            counter[j]=0;
        }
        for(int i=0;i<arr.length;i++){
            counter[arr[i]-min]++;
        }
        int loc =0;
        for (int i=1;i<counter.length;i++){
            counter[i]=counter[i]+counter[i-1];
        }
        int[]result = new int[arr.length];
        for(int i = arr.length-1;i>=0;i--){
            result[counter[arr[i]-min]-1] = arr[i];
            counter[arr[i]-min]--;
        }
        for (int i =0;i<arr.length;i++){
            arr[i]=result[i];
        }
        //累加数组确定稳定


//        for(int m=0;m<counter.length;m++){
//            for(int k=0;k<counter[m];k++){
//                arr[loc]=m+min;
//                loc++;
//            }
//        }


    }
    //min-max的计数排序



    //双轴快排
    public static void DualPivortQuickSort(int[]arr,int start,int end){

    }

    //单轴快排
    public static void QuickSort(int []arr,int start,int end){
        int length = end -start;
        if(length<=0){
            return;
        }
//        else if(length==1){
//            if(arr[end]<arr[start]){
//                swap(arr,start,end);
//            }
//        }
        else {
            int location = new_partition(arr,start,end);
            //int location = partition(arr,start,end);
            QuickSort(arr,start,location-1);
            QuickSort(arr,location+1,end);
        }

    }

    public static int partition(int[]arr,int start,int end){
        int pivort = arr[end];
        int left = start;
        int right = end-1;

        for(;left<=right;){
            if(arr[left]<pivort){
                left++;
                continue;
            }
            else if(arr[left]>=pivort){
                swap(arr,left,right);
                right--;
            }
        }
        swap(arr,left,end);
        return left;
    }

    public static int new_partition(int[]arr,int start,int end){
        int pivort = arr[end];
        int left = start;
        int right = end-1;
        while (left<=right){
            while (left<=right && arr[left]<=pivort) left++;
            while (left<=right && arr[right]>pivort) right--;
            if(left<right) swap(arr,left,right);
        }
        swap(arr,left,end);
        return left;
    }
    //二分插入
    public static void binarySort(int []arr){
        for (int i=1;i<arr.length;i++){
            int loc =findLocate(arr,0,i-1,arr[i]);
            int temp = arr[i];
            for(int j=i;j>loc;j--){
                arr[j]=arr[j-1];
            }
            arr[loc]=temp;

        }
    }
    public static int findLocate(int[]arr,int start,int end,int pivort){
        int mid = start + (end-start+1)/2 -1;
        int length = end -start;
        if(length==0){
            if(pivort<arr[start]){
                return start;
            }
            else return start+1;
        }
        else {
            if(pivort<=arr[mid]){
                return findLocate(arr,0,mid,pivort);

            }
            else {
                return findLocate(arr,mid+1,end,pivort);
            }
        }

    }
    public static void MergeSort(int[]arr, int start,int end){
        if(start==end) return;
        else if((end-start)==1){
            if(arr[end]<arr[start]){
                swap(arr,start,end);
            }
        }
        else {
            int mid = (end - start +1)/2;
            MergeSort(arr,start,start+mid-1);
            MergeSort(arr,start+mid,end);
            merge(arr,start,end);
        }
    }

    public static void merge(int[]arr1,int start,int end){
        int length = end - start +1;
        int mid = (end - start +1)/2;
        int i=start;
        int j=start + mid;
        int k=0;
        int []arr = new int[length];
        for(;k<arr.length;k++){
            if(i>=start+mid||j>end){
                break;
            }
            if(arr1[i]<=arr1[j]){
                arr[k] = arr1[i];
                i = i+1;
            }
            else {
                arr[k] = arr1[j];
                j=j+1;
            }
        }
        if(i>=start+mid){
            for(;k<length;k++){
                arr[k]=arr1[j];
                j = j+1;
            }
        }
        else if(j>end){
            for(;k<length;k++){
                arr[k] = arr1[i];
                i=i+1;
            }
        }
        for(int m=0;m<arr.length;m++){
            arr1[start +m] = arr[m];
        }
    }

    public static void ShellSort(int[]arr){


        for(int gap=4;gap>=1;gap/=2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
        }
    }
    public static void SelecSort(int[] a){
        for(int i=0;i<a.length-1;i++){
            int flag = i;
            int min = a[i];
            for(int j=i+1;j<a.length;j++){
                if(a[j]<min){
                    min =a[j];
                    flag = j;
                }
            }
            int temp = a[flag];
            a[flag] = a[i];
            a[i] = temp;
        }

    }
    public static void UnionSort(int[] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    int temp = a[j+1];
                    a[j+1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }


}
