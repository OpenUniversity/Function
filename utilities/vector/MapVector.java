package utilities.vector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * A map implementation of the vector class
 */
public class MapVector<VecType> extends Vector<VecType> {

    private Map<VecType, Double> map;

    public MapVector() {
        this.map = new HashMap<>();
    }

    @Override
    public Iterator<Scale<VecType>> iterator() {
        Iterator<Entry<VecType, Double>> mapIterator = map.entrySet().iterator();
        return new Iterator<Scale<VecType>>() {

            @Override
            public boolean hasNext() {
                return mapIterator.hasNext();
            }

            @Override
            public Scale<VecType> next() {
                Entry<VecType, Double> nextItem = mapIterator.next();
                return new Scale<VecType>(nextItem.getValue(), nextItem.getKey());
            }

        };
    }

    @Override
    public void add(VecType vec, double coefficient) {
        double newCoefficient = map.getOrDefault(vec, 0.0) + coefficient;
        if (newCoefficient == 0)
            map.remove(vec);
        else
            map.put(vec, newCoefficient);
    }

    @Override
    public int dimensions() {
        return map.size();
    }

    @Override
    public double projection(VecType vector) {
        return map.getOrDefault(vector, 0.0);
    }

}