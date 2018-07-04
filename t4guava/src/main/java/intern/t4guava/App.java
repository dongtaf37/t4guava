package intern.t4guava;

import static spark.Spark.*;

import java.util.concurrent.ExecutionException;
import com.google.common.cache.LoadingCache;

public class App {
    public static void main( String[] args ) {
        
    	// default port: 4567
    	port(8888); 
    	
    	App test = new App();
    	
    	get("prime", "application/json", (req, res) -> {
    		String n = req.queryParams("n");
    		return test.getPrimeGuava(Integer.parseInt(n)).getList();
    	});	
    	
    }
    
    private PrimeNumber getPrimeGuava(int id) throws ExecutionException {
		LoadingCache<Integer, PrimeNumber> cache = PrimeCache.getLoadingCache();
		System.out.println("cache size: " + cache.size());
		return cache.get(id);
	}
}
