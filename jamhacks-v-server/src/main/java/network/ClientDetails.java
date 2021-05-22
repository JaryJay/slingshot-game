package network;

import java.net.InetAddress;
import java.util.Objects;

public class ClientDetails {

	private InetAddress address;
	private int port;

	public ClientDetails(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, port);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ClientDetails) {
			ClientDetails other = (ClientDetails) obj;
			return other.address.equals(address) && other.port == port;
		}
		return false;
	}

}
