package com.summitthai.cr.apprentice.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

import com.summitthai.cr.apprentice.jpa.xsp.person.manager.XspPersonManageable;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@FacesConverter(value = "xspPersonConverter", managed = true)
public class XspPersonConverter implements Converter<XspPersonRequest>{
	
	private static final long serialVersionUID = -683644823871020210L;
	
	@Inject
    private XspPersonManageable manager;

    @Override
    public XspPersonRequest getAsObject(FacesContext context, UIComponent component, String value) {
        log.debug("XspPersonRequest  getAsObject");
        if (value != null && value.trim().length() > 0) {
            try {
                return this.manager.getResponseAsMap(value);

            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid country."));
            }
        } else {
            return null;
        }
    }
    
	@Override
	public String getAsString(FacesContext context, UIComponent component, XspPersonRequest value) {
		log.debug("XspPersonRequest  getAsString");
        if (value != null) {
            return String.valueOf(value.getId());
        } else {
            return null;
        }
	}
}
