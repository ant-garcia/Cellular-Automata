public class DrawCell
{
	public static void main(String[] args)
	{
		if(args.length != 4)
		{
			System.out.println("Requires: #cells, #generations, rule#, and ring | line");
			return;
		}

		int numCells = Integer.parseInt(args[0]);
		int gen = Integer.parseInt(args[1]);
		int rule = Integer.parseInt(args[2]);
		boolean ring = args[2].equalsIgnoreCase("true")? true : false;
		CellListener cl = new CellListener(numCells, gen);
		CellularAutomaton am = new CellularAutomaton(numCells, ring);

		for(int i = 0; i < numCells; i++)
		{
			Type t = new Type();
			t.type = "white";
			if(i == numCells-1)
				t.type = "black";
			Cell c = new BinaryCell(t, i, rule);
			cl.stateChange(c, 0);
			am.placeCell(c);
		}
        System.out.println();
		Bag<Event<Type>> emptyIn = new Bag();
		Simulator<Type> sim = new Simulator(am);
		sim.addEventListener(cl);
		for(int i = 0; i < gen; i++)
		{
			sim.getNextState(emptyIn);
			System.out.println();
		}
	}
}