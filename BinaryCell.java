public class BinaryCell extends Cell
{
	private int rule;

	public BinaryCell(Type t, int l, int r)
	{
		super(t,l);
		this.rule = r;
	}

	protected String cellRule(Type l, Type r, Type s)
	{
		int shift = 0;
		if(l.type.equalsIgnoreCase("black"))
			shift += 4;
		if(s.type.equalsIgnoreCase("black"))
			shift += 2;
		if(r.type.equalsIgnoreCase("black"))
			shift += 1;
		if(((rule >> shift) & 0x01) == 1)
			return "black";
		else
			return "white";
	}
}