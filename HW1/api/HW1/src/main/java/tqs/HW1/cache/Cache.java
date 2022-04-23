package tqs.HW1.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Cache {
    
    private int requests, misses, hits = 0;
    private Map<String, Object> cache = new HashMap<>();
    private Map<String, Long> timeForEachKey = new HashMap<>();

    private long livingTime;


    public Cache(long livingTime){
        this.livingTime = livingTime;
    }


    public void push(String key, Object o){
        long maxTime = Long.sum(System.currentTimeMillis(), this.livingTime);
        timeForEachKey.put(key, maxTime);
        cache.put(key, o);
    }

    public Object get(String key){
        this.requests++;
        if (cache.containsKey(key) && System.currentTimeMillis() <= timeForEachKey.get(key)){
            this.hits++;
            return cache.get(key);
        }
        else if(cache.containsKey(key) && System.currentTimeMillis() > timeForEachKey.get(key)){
            clear(key);
        }
        this.misses++;
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

    public void clearAll(){
        cache.clear();
    }

    public int getSize(){
        List<String> keysToRemove = new ArrayList<>();

        for (String key : cache.keySet()){
            if(System.currentTimeMillis() > timeForEachKey.get(key)){
                keysToRemove.add(key);
            }
        }
        for (String e : keysToRemove)
            clear(e);

        return cache.size();
    }

    // GETTERS AND SETTERS

    public long getLivingTime() {
        return this.livingTime;
    }

    public void setLivingTime(long livingTime) {
        this.livingTime = livingTime;
    }

    public int getRequests() {
        return this.requests;
    }


    public int getMisses() {
        return this.misses;
    }

   
    public int getHits() {
        return this.hits;
    }

    public int getRequestsHitsMisses(){
        return this.hits+this.misses+this.requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }
    public void setMisses(int misses) {
        this.misses = misses;
    }
    public void setHits(int hits) {
        this.hits = hits;
    }



    @Override
    public String toString() {
        return "{requests: " + getRequests() +
            ", misses: " + getMisses() + 
            ", hits: " + getHits() + 
            "}";
    }

}
