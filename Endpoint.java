import java.util.ArrayList;
public class Endpoint
{
	int latency;
	int nCaches;
	ArrayList<Cache> caches = new ArrayList<Cache>();

	public Endpoint(int givenLatency, int givenNCaches)
	{
		latency = givenLatency;
		nCaches = givenNCaches;
	}
	
	public void add(Cache c)
	{
		caches.add(c);
	}
}