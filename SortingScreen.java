import java.util.*;
import java.awt.*;
import javax.swing.*;
public class SortingScreen extends JPanel
{
public final static int WIDTH=1000,HEIGHT=WIDTH*9/16;
public final static int size=100;
public final float Bar_Height=(float)WIDTH/size;
public static float bar_height[]=new float[size];
public SwingWorker<Void,Void>shuffler,sorter;
public static int current_index,traversing_index;
public static int time=20;
static String name="";
public SortingScreen()
{
setBackground(Color.BLACK);
setPreferredSize(new Dimension(WIDTH,HEIGHT));
initBarHeight();
initSorter();
initShuffler();
}
@Override
public void paintComponent(Graphics g)
{
super.paintComponent(g);
g.setColor(Color.CYAN);
for(int i=0;i<size;i++)
{
g.fillRect(i*(int)Bar_Height,0,(int)Bar_Height,(int)bar_height[i]);
}
}
public void initBarHeight()
{
    float interval=(float)HEIGHT/size;
for(int i=0;i<size;i++)
{
bar_height[i]=i*interval;
}
}
public void initSorter()
{
sorter=new SwingWorker<>(){
@Override
public Void doInBackground()
{
switch(name)
{
case "BubbleSort":
bubbleSort();
break;
case "SelectionSort":
selectionSort();
break;
case "InsertionSort":
insertionSort();
break;
case "MergeSort":
mergeSort(bar_height,0,size-1);
selectionSort();
break;
case "InsertionSort":
insertionSort();
break;
case "MergeSort":
mergeSort(bar_height,0,size-1);
break;
case "QuickSort":
quickSort(bar_height,0,size-1);
break;
default:
break;
}
return null;
}
};
}public void initShuffler()
{
shuffler=new SwingWorker<>(){
@Override
public Void doInBackground()
{
int middle=size/2;
for(int i=0,j=middle;i<middle;i++,j++)
{
int random_index=new Random().nextInt(size);
swap(i,random_index);
random_index=new Random().nextInt(size);
swap(j,random_index);
}
return null;
}
@Override
public void done()
{
super.done();
sorter.execute();
}
};
shuffler.execute();
}
public void swap(int indexA,int indexB)
{
float temp=bar_height[indexA];
bar_height[indexA]=bar_height[indexB];
bar_height[indexB]=temp;
try{
Thread.sleep(time);
}catch(InterruptedException ie){
ie.printStackTrace();
}
repaint();}
public void selectionSort()
{
for(current_index=0;current_index<size;current_index++)
{
traversing_index=current_index;
for(int min_index=current_index+1;min_index<size;min_index++)
if(bar_height[traversing_index]>bar_height[min_index])
{
traversing_index=min_index;
}
}
swap(current_index,traversing_index);
}
}
public void insertionSort()
{
for(current_index=1;current_index<size;current_index++)
traversing_index=current_index;
while(traversing_index>0&&bar_height[traversing_index]<bar_height[traversing_index-1])
{
swap(traversing_index,traversing_index-1);
traversing_index-=1;
}
}
}
void bubbleSort()
{
for(current_index=0;current_index<size;current_index++)
{
for(traversing_index=current_index+1;traversing_index<size;traversing_index++)
{
if(bar_height[current_index]>bar_height[traversing_index])
swap(traversing_index,current_index);}
}
}
void mergeSort(float bar_height[],int l,int r)
{
if(l<r)
{
int m=(l+r)/2;
mergeSort(bar_height,l,m);
mergeSort(bar_height,m+1,r);
merge(bar_height,l,m,r);
}
}
void merge(float arr[],int l,int m,int r)
{
int n1=m-l+1;
int n2=r-m;
int L[]=new int[n1];
int R[]=new int[n2];
for (int i = 0; i < n1; ++i)
L[i] =(int)arr[l + i];
for (int j = 0; j < n2; ++j)
R[j] =(int) arr[m + 1 + j];
int k = l;
int i = 0, j = 0;
while (i < n1 && j < n2)
{
if (L[i] <= R[j])
{
arr[k] = L[i];
i++;
try{
Thread.sleep(time);
}catch(InterruptedException ie){
    ie.printStackTrace();
}repaint();
}else {
arr[k] = R[j];
j++;
try{
Thread.sleep(time);
}catch(InterruptedException ie){
ie.printStackTrace();
}
repaint();
}
k++;
}
while (i < n1)
{
arr[k] = L[i];
i++;
k++;
try{
Thread.sleep(time);
}catch(InterruptedException ie){
ie.printStackTrace();
}
repaint();
}
while (j < n2)
{
arr[k] = R[j];
j++;
k++;
try{
Thread.sleep(time);
}catch(InterruptedException ie){
ie.printStackTrace();
}
repaint();}
}
public void quickSort(float arr[],int start,int last)
{
    if(start<last)
    {
    int pi=Partition(arr,start,last);
    quickSort(arr,start,pi-1);
    quickSort(arr,pi+1,last);
    }
    }
    public int Partition(float arr[],int start,int last)
    {
        float pivot=arr[last];
        int i=start-1;
        for(int j=start;j<=last-1;j++)
        {
        if(arr[j]<pivot)
        {
        i++;
        int temp=(int)arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
        }
        try{
        Thread.sleep(time);
    }catch(InterruptedException ie){
        ie.printStackTrace();
        }
        repaint();
        }
        int temp=(int)arr[i+1];
        arr[i+1]=arr[last];
        arr[last]=temp;
        try{
        Thread.sleep(time);
    }catch(InterruptedException ie){
        ie.printStackTrace();
        }
        repaint();
        return i+1;
        }
        public static void main(String sanu[])
        {
        Scanner ss=new Scanner(System.in);
        name=ss.next();
        SwingUtilities.invokeLater(()->{
        JFrame frame=new JFrame(name);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new SortingScreen());
        frame.validate();
frame.pack();
frame.setLocationRelativeTo(null);
frame.setVisible(true);
});
        }
    }
    