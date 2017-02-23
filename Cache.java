import java.util.ArrayList;
public class Cache
{
	int id;
	int latency;
	int cacheSize;
	int freeSpace;
	ArrayList<Video> videos = new ArrayList<Video>();
	public Cache(int givenId, int givenLatency, int givenCacheSize)
	{
		id = givenId;
		latency = givenLatency;
		cacheSize = givenCacheSize;
		freeSpace = cacheSize;
	}

	public boolean fits(Video givenVideo){
		if(givenVideo.size <= freeSpace)
			return true;
		return false;
	}

	public void addVideo(Video givenVideo){
		videos.add(givenVideo);
		freeSpace -= givenVideo.size;
	}
}