package task2;

public class LimitedCounter {
    private int initialCount;
    private int limit;
    private Counter counter;


    public LimitedCounter(int initialCount, int limit){
        this.initialCount = initialCount;
        this.limit = limit;
        counter = new Counter();
    }


    public int inc(){
        if(counter.count < limit){
            return counter.inc();
        }else{
            return counter.count;
        }
    }


    class Counter{
        private int count;

        Counter(){
            count = initialCount;
        }

        int inc(){
            return ++count;
        }
    }


    public static void main(String[]args){
        LimitedCounter cnt = new LimitedCounter(1,5);
        int cntVal = 0;
        for(int i = 0; i < 9; i++){
            cntVal = cnt.inc();
        }
        System.out.println("Значение счетчика: "+cntVal);
        LimitedCounter.Counter innerCnt = cnt.new Counter();
        System.out.println(innerCnt);
    }
}



