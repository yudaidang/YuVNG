package com.example.cpu11268_local.yuvng.Model;

import android.net.Uri;

/**
 * Created by cpu11268-local on 13/03/2018.
 */

public class Contact {
    private long contactId;
    private String contactName;
    private String contactNumber;
    private Uri contactImage;
    private String contactDetail;

    public Contact(long contactId, String contactName, String contactNumber, Uri contactImage, String contactDetail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactImage = contactImage;
        this.contactDetail = contactDetail;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Uri getContactImage() {
        return contactImage;
    }

    public void setContactImage(Uri contactImage) {
        this.contactImage = contactImage;
    }

    public String getContactDetail() {
        return contactDetail;
    }

    public void setContactDetail(String contactDetail) {
        this.contactDetail = contactDetail;
    }
}
