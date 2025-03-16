public class Pair<U, V> 
{
    private final U _first;
    private final V _second;

    public Pair(final U first, final V second) 
    {
        this._first = first;
        this._second = second;
    }

    public U first() 
    {
        return this._first;
    }

    public V second() 
    {
        return this._second;
    }

    @Override
    public String toString() {
        return "Pair{" + "first=" + this._first + ", second=" + this._second + '}';
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        } 

        if (obj == null || getClass() != obj.getClass()) 
        {
            return false;
        }
        
        Pair<?, ?> pair = (Pair<?, ?>) obj;

        return this._first.equals(pair._first) 
            && this._second.equals(pair._second);
    }

    @Override
    public int hashCode() 
    {
        return java.util.Objects.hash(this._first, this._second);
    }
}