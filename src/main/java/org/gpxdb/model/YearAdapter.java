package org.gpxdb.model;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class YearAdapter extends XmlAdapter<String, Date> {
    public Date unmarshal(String value) { 
         try {
            return new SimpleDateFormat("yyyy").parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public String marshal(Date value) {
        return new SimpleDateFormat("yyyy").format(value);
    }
}
