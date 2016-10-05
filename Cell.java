public class Cell extends Atomic<Type>
{
	private Type type;
	private int loc;

	public Cell(Type t, int l)
	{
		this.type = t;
		this.loc = l;
	}

	public void delta(Bag<Type> b)
	{
		Type left = new Type();
		Type right = new Type();

		left.type = "white";
		right.type = "white";

		for(Type t : b.list)
		{
			if(t.location == loc-1)
				left.type = t.type;
			else
				right.type = t.type;
		}

		this.type.type = cellRule(left, right, type);
	}

	public void output(Bag<Type> b)
	{
		Type out = new Type();
		out.type = this.type.type;
		out.location = this.loc;
		b.addIfNotFound(out);
	}

	public void clear(Bag<Type> b){}

	public int getLocation(){return this.loc;}

	public Type getType(){return this.type;}

	protected String cellRule(Type left, Type right, Type s){return this.type.type;}
}