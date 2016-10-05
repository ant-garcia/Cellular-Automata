import java.util.ArrayList;
public abstract class EventListener<V>
{
	public abstract void outputEvent(Event<V> e, int t);
	public abstract void stateChange(Atomic<V> model, int t);
}