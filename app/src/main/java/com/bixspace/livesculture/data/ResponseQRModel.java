package com.bixspace.livesculture.data;

import java.io.Serializable;

/**
 * Created by junior on 29/11/16.
 */

public class ResponseQRModel implements Serializable {


    private String id;
    private String code;
    private String company;
    private String valid;
    private String image;
    private String description;
    private String terms_conditions;
    private String q_1;
    private String q_2;
    private String q_3;
    private String q_4;
    private String a_1;
    private String question;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTerms_conditions() {
        return terms_conditions;
    }

    public void setTerms_conditions(String terms_conditions) {
        this.terms_conditions = terms_conditions;
    }

    public String getQ_1() {
        return q_1;
    }

    public void setQ_1(String q_1) {
        this.q_1 = q_1;
    }

    public String getQ_2() {
        return q_2;
    }

    public void setQ_2(String q_2) {
        this.q_2 = q_2;
    }

    public String getQ_3() {
        return q_3;
    }

    public void setQ_3(String q_3) {
        this.q_3 = q_3;
    }

    public String getQ_4() {
        return q_4;
    }

    public void setQ_4(String q_4) {
        this.q_4 = q_4;
    }

    public String getA_1() {
        return a_1;
    }

    public void setA_1(String a_1) {
        this.a_1 = a_1;
    }

}
