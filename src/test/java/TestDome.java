import jota.IotaAPI;
import jota.dto.response.GetAccountDataResponse;
import jota.dto.response.GetNewAddressResponse;
import jota.dto.response.GetNodeInfoResponse;
import jota.error.ArgumentException;
import org.junit.Before;
import org.junit.Test;

public class TestDome {
	private static final String TEST_SEED1 = "IHDEENZYITYVYSPKAURUZAQKGVJEREFDJMYTANNXXGPZ9GJWTEOJJ9IPMXOGZNQLSNMFDSQOTZAEETUEA";

	private IotaAPI iotaApi;

	@Before
	public void before() {
		iotaApi = new IotaAPI.Builder()
				.protocol("http")
				.host("nodes.testnet.iota.org")
				.port("80")
				.build();
	}

	@Test
	public void testApi() throws ArgumentException {
		GetNodeInfoResponse response = iotaApi.getNodeInfo();

		System.out.println(response.getAppName());

		System.out.println(response.getAppVersion());
	}

	@Test
	public void shouldCreateANewAddressWithChecksum() throws ArgumentException {
		final GetNewAddressResponse res1 = iotaApi.getNewAddress(TEST_SEED1, 1, 0, true, 5, false);
//		assertThat(res1.getAddresses().get(0), Is.is(TEST_ADDRESS_WITH_CHECKSUM_SECURITY_LEVEL_1));

		String adrr = res1.getAddresses().get(0);
		System.out.println("addr:" + adrr);

		for (String add : res1.getAddresses() ) {
			System.out.println("address:" + add);

		}
	}

	@Test
	public void testBalance () throws ArgumentException {
		GetAccountDataResponse accountData = iotaApi.getAccountData(TEST_SEED1, 1, 0, true, 0, true, 0, 0, true, 0);

		System.out.println( accountData.getBalance());
	}
}
