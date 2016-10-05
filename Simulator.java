import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
public class Simulator<V>
{
	private int t;
	private boolean upToDate;
	List<Atomic<V>>  models;
	Bag<EventListener<V>> bag;

	public Simulator(DiscreteModel<V> m)
	{
		this.t = 0;
		this.upToDate = false;
		this.bag = new Bag();
		this.models = new ArrayList();
		if(m.isAtomic() != null)
        {
            if (!models.contains(m.isAtomic()))
                models.add(m.isAtomic());
            else
                models.set(models.indexOf(m.isAtomic()), m.isAtomic());
        }
		else
			getAllChildren(m.isNetwork(), models);
	}

	public void getNextState(Bag<Event<V>> list)
	{
		getOutput();

		for(Event<V> e : list.list)
		{
			if(e.model.isAtomic() instanceof Atomic)
				e.model.isAtomic().in.addIfNotFound(e.val);
			else
				route(e.model.isNetwork(), e.model, e.val);
		}

		t++;
		for(Atomic<V> a : models)
		{
			a.delta(a.in);
			for(EventListener el : this.bag.list)
				el.stateChange(a, t);
		}
		
		for(Atomic<V> a : models)
		{
			a.clear(a.in);
			a.out.list.clear();
			a.in.list.clear();
		}
		
		this.upToDate = false;
	}

	public void getOutput()
	{
		if(this.upToDate)
			return;

		this.upToDate = true;
		
		for(Atomic<V> a : this.models)
		{
			a.output(a.out);
			Bag<V> b = a.out;
			for(V v : b.list)
				route(a.getParent(), a, v);
		}			
	}

	public int getTime(){return this.t;}

	void addEventListener(EventListener listener){bag.list.add(listener);}

	private void getAllChildren(Network<V> n, List<Atomic<V>> s)
	{
		List<DiscreteModel<V>> hs = new ArrayList();
		n.getComponents(hs);
		for(DiscreteModel<V> dm : hs)
		{
			if(dm.isAtomic() instanceof Atomic)
				s.add(dm.isAtomic());
			else
				getAllChildren(dm.isNetwork(), s);
		}
	}

	private void route(Network<V> parent, DiscreteModel<V> src, V val)
	{
		if(parent != src)
			notifyOutputListeners(src, val, t);
		if(parent == null)
			return;

		Bag<Event<V>> r = new Bag();
		parent.route(val, src, r);
		for(Event<V> e : r.list)
		{
			if(e.model.isAtomic() != null)
				e.model.isAtomic().in.addIfNotFound(e.val);
			else if(parent != e.model)
				route(e.model.isNetwork(), e.model, e.val);
			else
				route(parent.getParent(), parent, e.val);
		}
	}

	private void notifyOutputListeners(DiscreteModel<V> m, V val, int t)
	{
		Event<V> event = new Event(m, val);
		for(EventListener e : bag.list)
			e.outputEvent(event, t);
	}
}