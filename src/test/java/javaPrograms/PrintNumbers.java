package javaPrograms;

public class PrintNumbers {

    void main()
    {
        printNumbers(1,100);
    }

    void printNumbers(int initialValue,int max)
    {
        if(max == 0 || initialValue == max)
            return;

        IO.println(initialValue);

        if(initialValue < max)
        {
            initialValue++;
        }

        printNumbers(initialValue,max);
    }
}
