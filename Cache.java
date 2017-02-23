import java.util.ArrayList;
public class Cache
{
	int id;
	int latency;
	int cacheSize;
	int freeSpace;
	public Cache(int givenId, int givenLatency, int givenCacheSize)
	{
		id = givenId;
		latency = givenLatency;
		cacheSize = givenCacheSize;
		freeSpace = 0;
	}

	public boolean addVideo(Video givenVideo){
		if(givenVideo.size <= freeSpace){
			freeSpace -= givenVideo.size;
			return true;
		}
		return false;
	}
}