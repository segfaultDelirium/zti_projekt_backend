package com.zti_projekt_try0.other;

public class CreationResult extends ModificationResult{

    Integer createdRecordId;
    public CreationResult(boolean success, String message) {
        super(success, message);
    }

    public CreationResult(boolean success, String message, Integer createdRecordId) {
        super(success, message);
        this.createdRecordId = createdRecordId;
    }

    public Integer getCreatedRecordId() {
        return createdRecordId;
    }

    public void setCreatedRecordId(Integer createdRecordId) {
        this.createdRecordId = createdRecordId;
    }


}
