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
			endpoints.add(e);
			sc.nextLine();
			for(int j = 0; j < caches; j++){
				int cacheId = sc.nextInt();
				int lat = sc.nextInt();
				Cache c = new Cache(cacheId, lat, cacheSize);
				e.add(c);
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




		// START DOING
		int usedCaches = 0;
		String result = "";
		for(int i = 0; i < nRequests; i++){
			int vI = requests.get(i).videoId;
			int ePI = requests.get(i).endpointId;
			
			Endpoint e = endpoints.get(ePI);
			ArrayList<Cache> aLC = e.caches;
			for(int j = 0; j < aLC.size(); j++){
				if(aLC.get(j).addVideo(videos.get(vI))){
					result += "\n" + j + " ";
					while(aLC.get(j).addVideo(videos.get(vI))){
						String s = vI + " ";
						result += s;
					}
				}
			}
		}

		System.out.println(result);
		
		
	}
}