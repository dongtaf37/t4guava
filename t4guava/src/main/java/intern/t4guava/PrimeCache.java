package intern.t4guava;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class PrimeCache {

	private static LoadingCache<Integer, PrimeNumber> cache;
	
	static {cache = CacheBuilder.newBuilder()
			.maximumSize(100) // set size
			.expireAfterAccess(10, TimeUnit.MINUTES) // set time ac
			.expireAfterWrite(20, TimeUnit.MINUTES) // set time wr
			.build(
				new CacheLoader<Integer, PrimeNumber>() {
					public PrimeNumber load(Integer id) throws Exception {
						return getPrimeArray(id);
					}
				});
	}
	
	public static LoadingCache<Integer, PrimeNumber> getLoadingCache() {
		return cache;
	}
	
	public static PrimeNumber getPrimeArray(int id) {
		PrimeNumber number = new PrimeNumber(id);
		return number;
	}
}

class PrimeNumber {
	
	private int n;
	private String list;
	
	public PrimeNumber (int n) {
		this.n = n;
		this.list = primeList(n);
	}
	
	public int getN() {
		return n;
	}
	
	public String getList() {
		return list;
	}
	
	private String primeList(int n) {
		StringBuilder sb = new StringBuilder("");
		for (int i = 2; i < n; i++) {
			if (isPrime(i)) {
				sb.append(i).append(", ");
			}
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(".");
		return sb.toString();
	}
	
	public boolean isPrime(int n) {
		for (int i = 2; i < n/2; ++i) {
			if (n%i  == 0)
				return false;
		}
		return true;
	}
	
}