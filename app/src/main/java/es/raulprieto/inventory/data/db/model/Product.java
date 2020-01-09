package es.raulprieto.inventory.data.db.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

public class Product {
    public static final String TAG = "product";

    //region Fields
    private String code; // {Dependency shortname + Section name + autoincremental number}
    private String name;
    private String shortName;
    private String description;
    private String serialNumber;
    private String modelCode;
    private String productType;
    private String category;
    private String subcategory;
    private String section; // section shortname, change to Dependency object
    private String state;
    private int quantity;
    private float price;
    private String seller;
    private String uriImage;
    private Date adquisitionDate;
    private Date dischargeDate;
    private String uriInfo;
    private ArrayList<String> notes;
    private ArrayList<String> tags;
    //endregion

    //region Constructors
    public Product(String code, String name, String shortName, String description, String serialNumber, String modelCode, String productType, String category, String subcategory, String section, String state, int quantity, float price, String seller, String uriImage, Date adquisitionDate, Date dischargeDate, String uriInfo, ArrayList<String> notes, ArrayList<String> tags) {
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.serialNumber = serialNumber;
        this.modelCode = modelCode;
        this.productType = productType;
        this.category = category;
        this.subcategory = subcategory;
        this.section = section;
        this.state = state;
        this.quantity = quantity;
        this.price = price;
        this.seller = seller;
        this.uriImage = uriImage;
        this.adquisitionDate = adquisitionDate;
        this.dischargeDate = dischargeDate;
        this.uriInfo = uriInfo;
        this.notes = notes;
        this.tags = tags;
    }

    public Product() {
    }
    //endregion

    //region Properties
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public Date getAdquisitionDate() {
        return adquisitionDate;
    }

    public void setAdquisitionDate(Date adquisitionDate) {
        this.adquisitionDate = adquisitionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getUriInfo() {
        return uriInfo;
    }

    public void setUriInfo(String uriInfo) {
        this.uriInfo = uriInfo;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    //endregion


    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
