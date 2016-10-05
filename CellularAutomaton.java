import java.util.Set;
import java.util.List;

public class CellularAutomaton extends Network<Type>
{
	private int size;
	private boolean ring;
	Cell[] cells;

	public CellularAutomaton(int s, boolean b)
	{
		this.size = s;
		this.ring = b;
		cells = new Cell[size];
		for(int i = 0; i < size; i++)
			cells[i] = null;
	}

	public void getComponents(List<DiscreteModel<Type>> s)
	{
        for(int i = 0; i < this.size; i++)
            if(!s.contains(this.cells[i]))
                s.add(this.cells[i]);
            else
                s.set(i, this.cells[i]);
	}

	public void route(Type t, DiscreteModel<Type> dm, Bag<Event<Type>> b)
	{
		Event<Type> el = new Event();
        Event<Type> er = new Event();
		el.val = t;
        er.val = t;
		int left = t.location - 1;
		int right = t.location + 1;

		if(left < 0 && this.ring)
		{
			el.val.location = size;
			el.model = cells[size -1];
			b.addIfNotFound(el);
		}else if(left >= 0)
		{
			el.val.location = t.location;
			el.model = cells[left];
			b.addIfNotFound(el);
		}if(right == size && this.ring)
		{
			er.val.location = -1;
			er.model = cells[0];
			b.addIfNotFound(er);
		}else if(right < size)
		{
			er.val.location = t.location;
			er.model = cells[right];
			b.addIfNotFound(er);
		}
	}

	public void placeCell(Cell c)
	{
		c.setParent(this);
		if(cells[c.getLocation()] != null)
			cells[c.getLocation()] = null;
		cells[c.getLocation()] = c;
	}
}