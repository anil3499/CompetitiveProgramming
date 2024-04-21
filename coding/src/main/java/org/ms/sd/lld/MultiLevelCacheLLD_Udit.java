package org.ms.sd.lld;

/*
https://github.com/anomaly2104/multi-level-cache-low-level-system-design/blob/main/problem-statement.md
https://www.youtube.com/watch?v=P-WM1EmirzU&list=PL564gOx0bCLqTolRIHIsR2JPv11w8LESW&index=12

Problem Statement
Design and implement a Multiple Level Cache Management System with N levels, say:

L1 -> L2 -> L3 .... -> Ln. Each layer will store key-value pairs of data. Both KEY and VALUE are of type String. L1 is the top layer with most priority. LN is the last layer with the least priority. You are given following details about the system:

The number of levels of cache.
The capacity of each layer, i.e. number of elements that can be stored.
Read time of each layer.
Write time of each layer.
This Cache system should be able to perform following operations:

1. READ KEY Operation:
Read will start from L1 level.
If Key is found at this layer then this value will be returned.
If Key is not found at this layer then try to read from next layer.
Keep doing this until the value of KEY is found at any level, or the last level has been reached.
If the value of KEY is found at any level, then this VALUE should also be written into all previous levels of cache which have higher priority that this level.
Total Read time is the sum of Read times of all levels where READ operation was attempted and Write time of all levels where WRITE operation was attempted.
You have to print the VALUE of KEY, and the total TIME taken to read it.
2. WRITE KEY Operation:
Write will start from L1 level.
Write the value of KEY at this level and all the levels below it.
If at any level, value of KEY is same as given VALUE then don't write again and return.
Total Write time is the sum of Write times of all levels where WRITE operation was attempted and Read time of all levels where READ operation was attempted.
You have to print the total TIME taken to write this KEY-VALUE.
3. STAT Operation ( Bonus point / Optional )
Stat operations prints three information:

What is the current usage of each level of cache, i.e. Filled / Total size?
What is the average read time of this Multi-Level Cache System for last 10 READ operation?
What is the average write time of this Multi-Level Cache System for last 10 WRITE operation?
Implementing Bonus part is optional but candidates who implement this part would be rated higher.




//model==========================================================
@Getter
@AllArgsConstructor
public class LevelCacheData {
    int readTime;
    int writeTime;
}
@ToString
@AllArgsConstructor
@Getter
public class ReadResponse<Value> {
    Value value;
    Double totalTime;
}
@AllArgsConstructor
@Getter
public class StatsResponse {

    private final Double avgReadTime;
    private final Double avgWriteTime;
    private final List<Double> usages;
}
@ToString
@AllArgsConstructor
@Getter
public class WriteResponse {
    Double timeTaken;
}



//storage========================================================
public interface Storage<Key, Value> {

    void add(Key key, Value value) throws StorageFullException;
    void remove(Key key);
    Value get(Key key);

    double getCurrentUsage();
}
public class InMemoryStorage<Key, Value> implements Storage<Key, Value> {
    private final Map<Key, Value> storage;
    private final Integer capacity;

    public InMemoryStorage(Integer capacity) {
        this.storage = new HashMap<Key, Value>();
        this.capacity = capacity;
    }

    public void add(Key key, Value value) throws StorageFullException {
        if (isStorageFull()) {
            throw new StorageFullException();
        }

        storage.put(key, value);
    }

    public Value get(Key key) {
        return storage.get(key);
    }

    public void remove(Key key) {
        storage.remove(key);
    }

    public double getCurrentUsage() {
        return ((double) storage.size()) / ((double) capacity);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}

//service========================================================
public class CacheService<Key, Value> {

    private final ILevelCache<Key, Value> multiLevelCache;
    private final List<Double> lastReadTimes;
    private final List<Double> lastWriteTimes;
    private final int lastCount;

    public CacheService(@NonNull final DefaultLevelCache<Key, Value> multiLevelCache, final int lastCount) {
        this.multiLevelCache = multiLevelCache;
        this.lastCount = lastCount;
        this.lastReadTimes = new ArrayList<>(lastCount);
        this.lastWriteTimes = new ArrayList<>(lastCount);
    }

    public WriteResponse set(@NonNull final Key key, @NonNull final Value value) {
        final WriteResponse writeResponse = multiLevelCache.set(key, value);
        addTimes(lastWriteTimes, writeResponse.getTimeTaken());
        return writeResponse;
    }

    public ReadResponse<Value> get(@NonNull final Key key) {
        final ReadResponse<Value> readResponse = multiLevelCache.get(key);
        addTimes(lastReadTimes, readResponse.getTotalTime());
        return readResponse;
    }

    public StatsResponse stats() {
        return new StatsResponse(getAvgReadTime(), getAvgWriteTime(), multiLevelCache.getUsages());
    }

    private Double getAvgReadTime() {
        return getSum(lastReadTimes)/lastReadTimes.size();
    }

    private Double getAvgWriteTime() {
        return getSum(lastWriteTimes)/lastWriteTimes.size();
    }

    private void addTimes(List<Double> times, Double time) {
        if (times.size() == this.lastCount) {
            times.remove(0);
        }

        times.add(time);
    }

    private Double getSum(List<Double> times) {
        Double sum = 0.0;
        for (Double time: lastReadTimes) {
            sum += time;
        }
        return sum;
    }
}

//policy========================================================
public interface EvictionPolicy<Key> {
    void keyAccessed(Key key);
    Key evictKey();
}
public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {
    public void keyAccessed(Object o) {
        // TODO: Implement this.
    }

    public Key evictKey() {
        // TODO: Implement this.
        return null;
    }
}
//provider=======================================================
public class CacheProvider<Key, Value> {

    private final EvictionPolicy<Key> evictionPolicy;
    private final Storage<Key, Value> storage;

    public CacheProvider(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void set(Key key, Value value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            final Key keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State.");
            }

            this.storage.remove(keyToRemove);
            set(key, value);
        }
    }

    public Value get(Key key) {
        final Value value = this.storage.get(key);
        this.evictionPolicy.keyAccessed(key);
        return value;
    }

    public double getCurrentUsage() {
        return this.storage.getCurrentUsage();
    }
}

public interface ILevelCache<Key, Value> {

    @NonNull
    WriteResponse set(Key key, Value value);

    @NonNull
    ReadResponse<Value> get(Key key);

    @NonNull
    List<Double> getUsages();
}

//CacheService -> L1 -> L2 -> L3...Ln -> null
//                C1    C2    ...
@AllArgsConstructor
public class DefaultLevelCache<Key, Value> implements ILevelCache<Key, Value> {
    private final LevelCacheData levelCacheData;
    private final CacheProvider<Key, Value> cacheProvider;

    @NonNull
    private final ILevelCache<Key, Value> next;

    @NonNull
    public WriteResponse set(Key key, Value value) {
        Double curTime = 0.0;
        Value curLevelValue = cacheProvider.get(key);
        curTime += levelCacheData.getReadTime();
        if (!value.equals(curLevelValue)) {
            cacheProvider.set(key, value);
            curTime += levelCacheData.getWriteTime();
        }

        curTime += next.set(key, value).getTimeTaken();
        return new WriteResponse(curTime);
    }

    @NonNull
    public ReadResponse<Value> get(Key key) {
        Double curTime = 0.0;
        Value curLevelValue = cacheProvider.get(key);
        curTime += levelCacheData.getReadTime();

        // L1 -> L2 -> L3 -> L4
        if (curLevelValue == null) {
            ReadResponse<Value> nextResponse = next.get(key);
            curTime += nextResponse.getTotalTime();
            curLevelValue = nextResponse.getValue();
            if (curLevelValue != null) {
                cacheProvider.set(key, curLevelValue);
                curTime += levelCacheData.getWriteTime();
            }
        }

        return new ReadResponse<>(curLevelValue, curTime);
    }

    @NonNull
    public List<Double> getUsages() {
        final List<Double> usages;
        if (next == null) {
            usages = Collections.emptyList();
        } else {
            usages = next.getUsages();
        }

        usages.add(0, cacheProvider.getCurrentUsage());
        return usages;
    }
}

public class NullEffectLevelCache<Key, Value> implements ILevelCache<Key, Value> {
    @Override
    public WriteResponse set(Key key, Value value) {
        return new WriteResponse(0.0);
    }

    @Override
    public ReadResponse<Value> get(Key key) {
        return new ReadResponse<>(null, 0.0);
    }

    @Override
    public List<Double> getUsages() {
        return Collections.emptyList();
    }
}
//exception======================================================
public class StorageFullException extends RuntimeException {
}
*/
public class MultiLevelCacheLLD_Udit {
}
