package com.flower.mine.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
public class Gain {

    @EmbeddedId
    private GainPK gainPK;
    @Column(nullable = false, precision = 20, scale = 10)
    private BigDecimal value;

    public GainPK getGainPK() {
        return gainPK;
    }

    public void setGainPK(GainPK gainPK) {
        this.gainPK = gainPK;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Embeddable
    public static class GainPK implements Serializable {
        @Column(nullable = false, length = 20)
        private String username;
        @Column(nullable = false)
        private Date date;

        public GainPK() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GainPK gainPK = (GainPK) o;
            return Objects.equals(username, gainPK.username) &&
                    Objects.equals(date, gainPK.date);
        }

        @Override
        public int hashCode() {
            return Objects.hash(username, date);
        }
    }
}
