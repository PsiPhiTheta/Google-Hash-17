import java.util.ArrayList;
import java.lang.Math;
public class Request
{
	int nRequests;
	int videoId;
	int endpointId;
	// ArrayList<Integer> products = new ArrayList<Integer>();
	// ArrayList<Integer> productsWeights;

	public Request(int givenNRequests, int givenVideoId, int givenEndpointId)
	{
		nRequests = givenNRequests;
		videoId = givenVideoId;
		endpointId = givenEndpointId;
	}
}