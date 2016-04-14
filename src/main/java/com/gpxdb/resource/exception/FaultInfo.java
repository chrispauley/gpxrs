package com.gpxdb.resource.exception;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAccessType;

/**
*
*  A JAXB type representing an exception for a service request.
*
*
*
*
*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
  "errorCode",
  "reason",
  "resource",
  "exception"
})
@XmlRootElement(name = "FaultInfo")
public class FaultInfo implements Serializable
{

  private final static long serialVersionUID = 1L;
  @XmlElement(namespace = "..........")
  protected Integer errorCode;
  @XmlElement(namespace = "...............")
  protected String reason;
  @XmlElement(namespace = "...................")
  protected String resource;
  @XmlElement(namespace = "............")
  protected String exception;

  /**
   * Gets the value of the errorCode property.
   *
   * @return
   *     possible object is
   *     {@link Integer }
   *
   */
  public Integer getErrorCode() {
      return errorCode;
  }

  /**
   * Sets the value of the errorCode property.
   *
   * @param value
   *     allowed object is
   *     {@link Integer }
   *
   */
  public void setErrorCode(Integer value) {
      this.errorCode = value;
  }

  /**
   * Gets the value of the reason property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getReason() {
      return reason;
  }

  /**
   * Sets the value of the reason property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setReason(String value) {
      this.reason = value;
  }

  /**
   * Gets the value of the resource property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getResource() {
      return resource;
  }

  /**
   * Sets the value of the resource property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setResource(String value) {
      this.resource = value;
  }

  /**
   * Gets the value of the exception property.
   *
   * @return
   *     possible object is
   *     {@link String }
   *
   */
  public String getException() {
      return exception;
  }

  /**
   * Sets the value of the exception property.
   *
   * @param value
   *     allowed object is
   *     {@link String }
   *
   */
  public void setException(String value) {
      this.exception = value;
  }

}