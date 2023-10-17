package se.kth.id1201;

import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    { 
        ZipBetter z = new ZipBetter(args[0]);
        //System.out.println(z.binary("168 47").name);
        //ZipBuckets.Node node = z.findCollisions();
        System.out.println(z.lookup("611 31").name);
    }

    public void benchmark(String[] args){
        int repitition = 100;
        long zipBucketsSumTime = 0;
        long zipBetterSumTime = 0;
        long stratTime;
        long endTime;

        ZipBuckets zBuckets = new ZipBuckets(args[0]);
        ZipBetter zBetter = new ZipBetter(args[0]);

        System.out.printf("#look up in hash table, time in ms\n");
        System.out.printf("#%12s%12s\n", "buckets", "better");
        for(int i = 0; i < repitition; i++){
            stratTime = System.nanoTime();
            zBuckets.lookupBucket("611 31");
            endTime = System.nanoTime();
            zipBucketsSumTime += endTime - stratTime;

            stratTime = System.nanoTime();
            zBetter.lookup("611 31");
            endTime = System.nanoTime();
            zipBetterSumTime += endTime - stratTime;
        }
        System.out.printf("%12.2f%12.2f\n", zipBucketsSumTime/repitition, zipBetterSumTime/repitition);


            
    }
}

