import java.util.Scanner;
import java.util.ArrayList;
public class Classic
{
	static int nVideos, nEndpoints, nRequests, nCaches, cacheSize;
	static ArrayList<Video> videos = new ArrayList<Video>();
	static ArrayList<Request> requests = new ArrayList<Request>();
	static ArrayList<Endpoint> endpoints = new ArrayList<Endpoint>();
	static ArrayList<Cache> caches = new ArrayList<Cache>();


	public static void main(String [] args)
	{	
		read();
		// START DOING
		int usedCaches = 0;
		for(int i = 0; i < nRequests; i++){
			int videoId = requests.get(i).videoId;
			int endpointId = requests.get(i).endpointId;
			System.out.println("Adding video " + videoId);
			Video v = videos.get(videoId);
			
			Endpoint e = endpoints.get(endpointId);
			ArrayList<Cache> aCaches = e.caches;

			for(int j = 0; j < aCaches.size(); j++){
				if(aCaches.get(j).fits(v)){
					System.out.println("Trying to put video " + videoId + " in cache " + j);
					aCaches.get(j).addVideo(v);
					break;
				}
			}
		}

		System.out.println(caches.size());
		for(int i = 0; i < caches.size(); i++){
			System.out.print(i);
			for(int j = 0; j < caches.get(i).videos.size(); j++){
				System.out.print(" " + caches.get(i).videos.get(j).id);				
			}
			System.out.println();
		}
		
		
	}

	public static void read()
	{
		Scanner sc = new Scanner(System.in);
		nVideos = sc.nextInt();
		nEndpoints = sc.nextInt();
		nRequests = sc.nextInt();
		nCaches = sc.nextInt();
		cacheSize = sc.nextInt();
		sc.nextLine();

		for(int i = 0; i < nCaches; i++)
			caches.add(null); 

		for(int i = 0; i < nVideos; i++){
			Video v = new Video(sc.nextInt(), i);
			videos.add(v);
		}
		sc.nextLine();

		for(int i = 0; i < nEndpoints; i++){
			int latency = sc.nextInt();
			int intCaches = sc.nextInt();
			Endpoint e = new Endpoint(latency, intCaches);
			endpoints.add(e);
			sc.nextLine();
			for(int j = 0; j < intCaches; j++){
				int cacheId = sc.nextInt();
				int lat = sc.nextInt();
				Cache c = new Cache(cacheId, lat, cacheSize);
				e.add(c);
				caches.set(cacheId, c);
				sc.nextLine();
			}
		}

		for(int i = 0; i < nRequests; i++){
			int videoId = sc.nextInt();
			int endpointId = sc.nextInt();
			int allRequests = sc.nextInt();
			Request r = new Request(allRequests, videoId, endpointId);
			requests.add(r);
			if(i == nRequests-1)
				break;
			sc.nextLine();
		}
	}
}