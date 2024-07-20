package hillel.javapro.ewallet_dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_session")
public class CustomerSession {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "session_datetime")
    private Instant sessionDatetime;

    @Column(name = "browser", length = 45)
    private String browser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getSessionDatetime() {
        return sessionDatetime;
    }

    public void setSessionDatetime(Instant sessionDatetime) {
        this.sessionDatetime = sessionDatetime;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var that = (CustomerSession) o;
        return Objects.equals(id, that.id) && Objects.equals(sessionDatetime, that.sessionDatetime) && Objects.equals(browser, that.browser) && Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessionDatetime, browser, customer);
    }

    @Override
    public String toString() {
        return "CustomerSession{" +
                "id=" + id +
                ", sessionDatetime=" + sessionDatetime +
                ", browser=" + browser +
                ", customer=" + customer +
                '}';
    }
}