package hillel.javapro.ewallet_dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beneficiary")
public class Beneficiary {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "iban", length = 45)
    private String iban;

    @Column(name = "bank_name", length = 45)
    private String bankName;

    @Column(name = "mfo")
    private Integer mfo;

    @Column(name = "full_name", length = 45)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getMfo() {
        return mfo;
    }

    public void setMfo(Integer mfo) {
        this.mfo = mfo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beneficiary that = (Beneficiary) o;
        return Objects.equals(id, that.id) && Objects.equals(iban, that.iban) && Objects.equals(bankName, that.bankName) && Objects.equals(mfo, that.mfo) && Objects.equals(fullName, that.fullName) && Objects.equals(wallet, that.wallet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iban, bankName, mfo, fullName, wallet);
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "id=" + id +
                ", iban=" + iban +
                ", bankName=" + bankName +
                ", mfo=" + mfo +
                ", fullName=" + fullName +
                ", wallet=" + wallet +
                '}';
    }
}