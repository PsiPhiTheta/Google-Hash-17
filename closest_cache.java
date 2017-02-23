// Method: (endpoint, videoID) - return closest available cacheID
public static int closest_cache (int endpointID, int videoID)
{
	int min_latency = 999999;
	int closest_free_cacheID = -1;

	for (int i = 0; i<endpointID.cache.size(); i++)  //go through all the caches available to that endpoint
	{
		if ((endpoint[endpointID].cache[i].latency < min_latency) && (cache[i].addVideo(videoID) == 1))
		{
			min_latency = endpoint[endpointID].cache[i].latency;
			closest_free_cacheID = cache[i].id;
		}
	}
	return closest_free_cacheID;
}
