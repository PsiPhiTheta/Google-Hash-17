import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
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
		// int usedCaches = 0;
		// for(int i = 0; i < nRequests; i++){
		// 	int videoId = requests.get(i).videoId;
		// 	int endpointId = requests.get(i).endpointId;
		// 	Video v = videos.get(videoId);
			
		// 	Endpoint e = endpoints.get(endpointId);
		// 	ArrayList<Cache> aCaches = e.caches;

		// 	for(int j = 0; j < aCaches.size(); j++){
		// 		if(aCaches.get(j).fits(v)){
		// 			aCaches.get(j).addVideo(v);
		// 			break;
		// 		}
		// 	}
		// }


		



		ArrayList<Integer> intsa = new ArrayList<Integer>();
		ArrayList<Request> helper = new ArrayList<Request>();

		for(int i = 0; i < requests.size(); i++){
			Endpoint e = endpoints.get(requests.get(i).endpointId);
			Video v = videos.get(requests.get(i).videoId);

			caches = e.setCaches(caches);
			int cacheIndex = e.closest_cache(v);

			if(cacheIndex >= 0){
				Cache c = caches.get(cacheIndex);
				c.addVideo(v);
				caches = e.caches;
			}
		}

		try{
			PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
			writer.println(caches.size());
			for(int i = 0; i < caches.size(); i++){
				writer.print(i);
				for(int j = 0; j < caches.get(i).videos.size(); j++){
					if(!intsa.contains(caches.get(i).videos.get(j).id)){
						writer.print(" " + caches.get(i).videos.get(j).id);
						intsa.add(caches.get(i).videos.get(j).id);				
					}
				}
				writer.println();
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Error");
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


	private static void mergeSort(ArrayList<Request> list, ArrayList<Request> helper, int low, int high) {
        if(low < high) {
            int middle = (low+high)/2;
            mergeSort(list, helper, low, middle); //sort left half
            mergeSort(list, helper, middle+1, high); //sort right half
            merge(list, helper, low, middle, high); // merge
        }
    }
     
    private static void merge(ArrayList<Request> list, ArrayList<Request> helper, int low, int middle, int high) {
        //This loop throws Exception
        for(int i=low; i< high + 1; i++) {
            helper.add(i, list.get(i));
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /**
         * Iterate through helper array, copying back smaller element in the original list 
         */
        while(helperLeft < middle && helperRight < high) {
            if(helper.get(helperLeft).nRequests < helper.get(helperRight).nRequests) {
                list.set(current, helper.get(helperLeft));
                helperLeft++;
            } else {
                list.set(current, helper.get(helperRight));
                helperRight++;
            }
            current++;
        }

        //Copy remaining elements
        int remaining = middle - helperLeft;
        for(int j=0; j <= remaining; j++) {
            list.set(current+j, helper.get(helperLeft+j));
        }

    }
}