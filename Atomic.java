import java.util.ArrayList;

public abstract class Atomic<X> extends DiscreteModel<X>
{
	Bag<X> in = new Bag();
	Bag<X> out = new Bag();
	public abstract void delta(Bag<X> list);	
	public abstract void output(Bag<X> list);
	public abstract void clear(Bag<X> list);
	public Atomic<X> isAtomic(){return this;}
	public Network<X> isNetwork(){return null;}
}