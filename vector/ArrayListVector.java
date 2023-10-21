package vector;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListVector<VecType> extends Vector<VecType> {

    private ArrayList<Scale<VecType>> sum;

    public ArrayListVector() {
        this.sum = new ArrayList<>();
    }

    // copy constructor

    @Override
    public Iterator<Scale<VecType>> iterator() {
        return sum.iterator();
    }

    @Override
    public void add(VecType vec, double coefficient) {
        Scale<VecType> term;
        if (coefficient == 0)
            return;

        for (int i = 0; i < dimensions(); i++) {
            term = sum.get(i);
            if (!term.getVector().equals(vec))
                continue;
            double newScalar = term.getScalar() + coefficient;
            if (newScalar == 0)
                sum.remove(term);
            else
                term.setScalar(newScalar);
            return;
        }

        sum.add(new Scale<VecType>(coefficient, vec));
    }

    @Override
    public int dimensions() {
        return sum.size();
    }

}
