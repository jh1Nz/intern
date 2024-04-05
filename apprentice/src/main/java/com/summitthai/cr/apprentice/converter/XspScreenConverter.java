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
import com.summitthai.cr.apprentice.jpa.xsp.screen.manager.XspScreenManageable;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@FacesConverter(value = "xspScreenConverter", managed = true)
public class XspScreenConverter implements Converter<XspScreenRequest>{
	
	private static final long serialVersionUID = -683644823871020210L;
	
	@Inject
    private XspScreenManageable manager;
	
	@Override
    public XspScreenRequest getAsObject(FacesContext context, UIComponent component, String value) {
        log.debug("XspScreenRequest  getAsObject");
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
	public String getAsString(FacesContext context, UIComponent component, XspScreenRequest value) {
		log.debug("XspScreenRequest  getAsString");
        if (value != null) {
            return String.valueOf(value.getId());
        } else {
            return null;
        }
	}

}
