package be.pxl.ja.streamingservice.model;

import be.pxl.ja.streamingservice.exception.InvalidDateException;
import java.time.LocalDate;

public class PaymentInfo {
    private String firstName;
    private String lastName;
	private CreditCardNumber cardNumber;
	private LocalDate expirationDate;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        if (LocalDate.now().minusDays(31).isBefore(expirationDate)) {
            throw new InvalidDateException(expirationDate, "expiration date", "expiration date must be at least one month away from now");
        }

        this.expirationDate = expirationDate;
    }

	public void setCardNumber(CreditCardNumber cardNumber) {
        this.cardNumber = cardNumber;
	}
}
