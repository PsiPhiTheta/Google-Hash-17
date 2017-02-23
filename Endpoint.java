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

	public int closest_cache (Video givenVideo)
	{
		int min_latency = 999999;
		int closest_free_cacheID = -1;

		for (int i = 0; i < caches.size(); i++)  //go through all the caches available to that endpoint
		{
			if((caches.get(i).latency < min_latency) && (caches.get(i).fits(givenVideo)))
			{
				min_latency = caches.get(i).latency;
				closest_free_cacheID = caches.get(i).id;
			}
		}
		return closest_free_cacheID;
	}

	public ArrayList<Cache> setCaches(ArrayList<Cache> givenCaches){
		caches = givenCaches;
		return caches;
	}
}