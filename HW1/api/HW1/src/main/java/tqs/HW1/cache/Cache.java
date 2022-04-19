package tqs.HW1.cache;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    
    private int requests, misses, hits;
    private Map<String, Object> cache = new HashMap<>();
    private Map<String, Long> timeForEachKey = new HashMap<>();

    private long start, livingTime, stopTime;


    public Cache(long livingTime){
        this.livingTime = livingTime;
    }


    public void push(String key, Object o){
        long maxTime = Long.sum(System.currentTimeMillis(), this.livingTime);
        timeForEachKey.put(key, maxTime);
        cache.put(key, o);
    }

    public Object get(String key){
        if (cache.containsKey(key) && System.currentTimeMillis() <= timeForEachKey.get(key)){
            return cache.get(key);
        }
        else if(cache.containsKey(key) && System.currentTimeMillis() > timeForEachKey.get(key)){
            clear(key);
        }
        
        return null;
        
    }

    public boolean clear(String key){
        if (cache.containsKey(key)){
            cache.remove(key);
            timeForEachKey.remove(key);
            return true;
        }
        return false;
    }




    // GETTERS AND SETTERS

    public long getStopTime(){
        return this.stopTime;
    }

    public void setStopTime(long newST){
        this.stopTime = newST;
    }

    public long getLivingTime() {
        return this.livingTime;
    }

    public void setLivingTime(long livingTime) {
        this.livingTime = livingTime;
    }

    public int getRequests() {
        return this.requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public int getMisses() {
        return this.misses;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    public int getHits() {
        return this.hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }


    public long getStart() {
        return this.start;
    }

    public void setStart(long start) {
        this.start = start;
    }

}
