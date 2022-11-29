package com.bird.entity;

import java.util.Date;

public class Meeting {
    private Long id;

    private Date createDate;

    private String title;

    private String logo;

    private Date holdDate;

    private String holdAddress;

    private String remarks;

    private String welcomeSpeech;

    private String registerAgreement;

    private String assembly;

    private String contribution;

    public String getWelcomeSpeech() {
        return welcomeSpeech;
    }

    public void setWelcomeSpeech(String welcomeSpeech) {
        this.welcomeSpeech = welcomeSpeech == null ? null : welcomeSpeech.trim();
    }

    public String getRegisterAgreement() {
        return registerAgreement;
    }

    public void setRegisterAgreement(String registerAgreement) {
        this.registerAgreement = registerAgreement == null ? null : registerAgreement.trim();
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly == null ? null : assembly.trim();
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution == null ? null : contribution.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Date getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(Date holdDate) {
        this.holdDate = holdDate;
    }

    public String getHoldAddress() {
        return holdAddress;
    }

    public void setHoldAddress(String holdAddress) {
        this.holdAddress = holdAddress == null ? null : holdAddress.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}