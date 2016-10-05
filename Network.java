import java.util.Set;
import java.util.List;

public abstract class Network<X> extends DiscreteModel<X>
{

	public Network(){}
	public void getComponents(List<DiscreteModel<X>> set){}
	public void route(X val, DiscreteModel<X> m, Bag<Event<X>> bag){}
	public Network<X> isNetwork(){return this;}
	public Atomic<X> isAtomic(){return null;}
}