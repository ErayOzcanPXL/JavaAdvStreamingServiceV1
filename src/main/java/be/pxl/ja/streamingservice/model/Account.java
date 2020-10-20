package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.TooManyProfilesException;
import be.pxl.ja.streamingservice.util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Account {
	private String email;
	private String password;
	private PaymentInfo paymentInfo;
	private StreamingPlan streamingPlan;
	private List<Profile> profiles = new ArrayList<>();

	public Account(String email, String password) {
        if (email == null || email.equals("") || password == null || password.equals("")) {
            throw new IllegalArgumentException("Email and password must be filled in");
        }

		this.email = email;
		setPassword(password);
		profiles.add(new Profile("Profile 1"));
        this.streamingPlan = StreamingPlan.BASIC;
	}

	public void setStreamingPlan(StreamingPlan streamingPlan) {
		this.streamingPlan = streamingPlan;
	}

	public void addProfile(Profile profile) {
        if (profiles.size() == streamingPlan.getNumberOfScreens()) {
            throw new TooManyProfilesException("Maximum amount of profiles reached");
        }

		profiles.add(profile);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean verifyPassword(String password) {
		return PasswordUtil.isValid(password, this.password);
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

	public void setPassword(String password) {
        if (PasswordUtil.calculateStrength(password) < 5) {
            throw new IllegalArgumentException("Password must be stronger");
        }

		this.password = PasswordUtil.encodePassword(password);
	}

	public int getNumberOfProfiles() {
		return profiles.size();
	}

	public List<Profile> getProfiles() {
        Collections.sort(profiles);
		return profiles;
	}
}
