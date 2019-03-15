package in.spring4buddies.poc.data;

import java.io.Serializable;

public class BMC implements Serializable {
	private static final long serialVersionUID = 1L;
	private String brand;
	private String market;
	private String channel;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		return "Order [brand=" + brand + ", market=" + market + ", channel=" + channel + "]";
	}
}