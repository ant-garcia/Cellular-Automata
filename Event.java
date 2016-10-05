public class Event<V>
{
	DiscreteModel<V> model;
	V val;

	public Event(){};

	public Event(Event<V> e){};

	public Event(DiscreteModel<V> a, V v)
	{
		this.model = a;
		this.val = v;
	};

}