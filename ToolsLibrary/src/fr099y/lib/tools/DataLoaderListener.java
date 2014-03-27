package fr099y.lib.tools;

/**
 * <br>
 * Энэ санд агуулагдах <strong>>DataLoader</strong> классын <strong>onPostExecute</strong> функц дээр дуудагдах interface. <br>
 * <strong>onPostExecuted</strong> функцээр орж ирэх <strong>String</strong> төрлийн хувьсагч нь <strong>DataLoader</strong> классын уншсан өгөгдөл байна. Өгөгдөл уншихад ямар нэг алдаа
 * гарсан бол уг хувьсагч <strong><i>null</i></strong> утгатай байна.
 * @author Fr099y
 *
 */
public interface DataLoaderListener {
	
	void onPostExecuted(String data);
}
