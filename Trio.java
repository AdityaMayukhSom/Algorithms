public class Trio<U, V, W>
{
    private final U _first;
    private final V _second;
    private final W _third;

    public Trio(final U first, final V second, final W third)
    {
        this._first = first;
        this._second = second;
        this._third = third;
    }

    public U first()
    {
        return this._first;
    }

    public V second()
    {
        return this._second;
    }

    public W third()
    {
        return this._third;
    }

    @Override
    public String toString()
    {
        return String.format("Trio{first=%s, second=%s, third=%s}", this._first,
                this._second, this._third);
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
        Trio<?, ?, ?> trio = (Trio<?, ?, ?>) obj;
        return this._first.equals(trio._first)
                && this._second.equals(trio._second)
                && this._third.equals(trio._third);
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(this._first, this._second, this._third);
    }
}
