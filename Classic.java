import java.util.Scanner;
import java.util.ArrayList;
public class Classic
{
	static int nVideos, nEndpoints, nRequests, nCaches, cacheSize;
	static ArrayList<Video> videos = new ArrayList<Video>();
	static ArrayList<Request> requests = new ArrayList<Request>();
	static ArrayList<Endpoint> endpoints = new ArrayList<Endpoint>();


	public static void main(String [] args)
	{
		Scanner sc = new Scanner(System.in);
		nVideos = sc.nextInt();
		nEndpoints = sc.nextInt();
		nRequests = sc.nextInt();
		nCaches = sc.nextInt();
		cacheSize = sc.nextInt();
		sc.nextLine();

		for(int i = 0; i < nVideos; i++){
			Video v = new Video(sc.nextInt());
			videos.add(v);
		}
		sc.nextLine();

		for(int i = 0; i < nEndpoints; i++){
			int latency = sc.nextInt();
			int caches = sc.nextInt();
			Endpoint e = new Endpoint(latency, caches);
			sc.nextLine();
			for(int j = 0; j < caches; j++){
				int cacheId = sc.nextInt();
				int latency = sc.nextInt();
				Cache c = new Cache(cacheId, latency, cacheSize);
				e.add(c);
				sc.nextLine();
			}
		}

		for(int i = 0; i < nRequests; i++){
			int videoId = sc.nextInt();
			int endpointId = sc.nextInt();
			int allRequests = sc.nextInt();
			Request r = new Request(allRequests, videoId, endpointId)
			sc.nextLine();
		}

		// START DOING

		
	}
}