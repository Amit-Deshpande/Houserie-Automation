package com.rentalroost.automation.core.qa.utils.mail;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.search.AddressTerm;

/**
 * @author kaushik_vira
 *
 */
public final class CCTerm extends AddressTerm {

	private static final long serialVersionUID = 5214730291502658665L;

	/**
	 * Constructor
	 *
	 * @param address
	 *            The Address to be compared
	 */
	public CCTerm(Address address) {
		super(address);
	}

	/**
	 * The address comparator.
	 * 
	 * @param msg
	 *            The address comparison is applied to this Message
	 * @return true if the comparison succeeds, otherwise false
	 */
	public boolean match(Message msg) {
		Address[] to;

		try {
			to = msg.getRecipients(Message.RecipientType.CC);
		} catch (Exception e) {
			return false;
		}

		if (to == null)
			return false;

		for (int i = 0; i < to.length; i++) {
			if (super.match(to[i]))
				return true;
		}
		return false;
	}

	/**
	 * Equality comparison.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof CCTerm))
			return false;
		return super.equals(obj);
	}
}
