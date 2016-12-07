package com.bixspace.livesculture.data;

import java.io.Serializable;

/**
 * Created by junior on 29/11/16.
 */

public class CuponModel implements Serializable {


    private CuponClass coupon;

    public CuponClass getCoupon() {
        return coupon;
    }

    public void setCoupon(CuponClass coupon) {
        this.coupon = coupon;
    }

    public class CuponClass implements Serializable {
        private String id;
        private String company;
        private String valid;
        private String description;
        private String image;
        private String terms_conditions;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getValid() {
            return valid;
        }

        public void setValid(String valid) {
            this.valid = valid;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTerms_conditions() {
            return terms_conditions;
        }

        public void setTerms_conditions(String terms_conditions) {
            this.terms_conditions = terms_conditions;
        }

    }

}
