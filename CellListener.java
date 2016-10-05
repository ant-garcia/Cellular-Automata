public class CellListener extends EventListener<Type>
{
	public CellListener(int numCells, int gen){}

	public void stateChange(Atomic<Type> m, int t)
	{
		showState((Cell)m, t);
	}

	public void outputEvent(Event<Type> e, int t){}

	public void showState(Cell c, int gen)
	{
		if(c.getType().type.equalsIgnoreCase("white"))
			System.out.print(" ");
		else
			System.out.print("\u25a0");
	}
}