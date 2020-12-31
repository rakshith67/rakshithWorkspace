package splitwise;

import java.util.ArrayList;
import java.util.List;

public class Bill {

	private String paidUser;

	private List<Owe> oweList = new ArrayList<>();

	public Bill(String paidUser, List<Owe> oweList) {
		this.paidUser = paidUser;
		this.oweList = oweList;
	}

	public String getPaidUser() {
		return paidUser;
	}

	public List<Owe> getOweList() {
		return oweList;
	}

}
