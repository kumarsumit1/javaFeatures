//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.01 at 11:09:08 PM IST 
//


package com.wipro.test;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zooInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zooInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="zooName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="zooId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="animals" type="{}Animals"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zooInfo", propOrder = {
    "zooName",
    "zooId",
    "animals"
})
public class ZooInfo {

    @XmlElement(required = true)
    protected String zooName;
    protected int zooId;
    @XmlElement(required = true)
    protected Animals animals;

    /**
     * Gets the value of the zooName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZooName() {
        return zooName;
    }

    /**
     * Sets the value of the zooName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZooName(String value) {
        this.zooName = value;
    }

    /**
     * Gets the value of the zooId property.
     * 
     */
    public int getZooId() {
        return zooId;
    }

    /**
     * Sets the value of the zooId property.
     * 
     */
    public void setZooId(int value) {
        this.zooId = value;
    }

    /**
     * Gets the value of the animals property.
     * 
     * @return
     *     possible object is
     *     {@link Animals }
     *     
     */
    public Animals getAnimals() {
        return animals;
    }

    /**
     * Sets the value of the animals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Animals }
     *     
     */
    public void setAnimals(Animals value) {
        this.animals = value;
    }

}
