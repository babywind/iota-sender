import jota.IotaAPI;
import jota.dto.response.GetNodeInfoResponse;
import jota.error.ArgumentException;
import org.junit.Test;

public class TestDome {

	@Test
	public void test() {
		System.out.println("begin test");
	}

	@Test
	public void testApi() throws ArgumentException {
		IotaAPI api = new IotaAPI.Builder().build();
		GetNodeInfoResponse response = api.getNodeInfo();

		System.out.println(response.getAppName());
	}
}
