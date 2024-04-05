package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.summitthai.cr.apprentice.datetime.model.DateTimeRequest;
import com.summitthai.sdd.faces.view.base.ViewBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0.0
 * @author dachanon.ya
 * @since 2023-11-10
 * 
 */
@Named
@ViewScoped
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class View01003Bean extends ViewBase implements Serializable {

	private static final long serialVersionUID = 1L;
	private DateTimeRequest formDialog;
	private List<DateTimeRequest> dateTimeList;

	public View01003Bean() {
		this.formDialog = DateTimeRequest.builder().build();

	}

	@PostConstruct
	public void init() {
		log.debug("Begin init");
		this.mode = MODE_SEARCH;
		Date date = new Date();

		log.debug("show time 1 {}", this.getNextMonthFirstDate(date));
		log.debug("show time 2 {}", this.getLastDateOfMonth(date));
		log.debug("End init");
	}

	public void showMassageDialog() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "ข้อความ", "ทดสอบ message dialog");
		PrimeFaces.current().dialog().showMessageDynamic(message);
	}

	public void convertdateTodayofweek() {
		Date test = this.formDialog.getDateUi();
		ZoneId zoneId = ZoneId.of("Asia/Bangkok");
		LocalDate localDate = test.toInstant().atZone(zoneId).toLocalDate();
		localDate.getDayOfWeek();
		this.formDialog.setNameDay(localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US));

	}

	public void addList() {
		DateTimeRequest data = DateTimeRequest.builder().id(UUID.randomUUID().toString())
				.dateUi(this.formDialog.getDateUi()).nameDay(this.formDialog.getNameDay()).build();
		this.dateTimeList.add(data);
	}

	public void resetDialogButton() {

		this.formDialog = DateTimeRequest.builder().build();
		this.dateTimeList = new ArrayList<>();

	}

	public void removeDataList(int index) {
		log.debug("Begin removeDataList...");
		this.dateTimeList.remove(index);
		log.debug("Begin removeDataList...");
	}

	public Date getNextMonthFirstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		return calendar.getTime();
	}


	public Date getLastDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		log.debug("day of month {} ", dayOfMonth);
		return calendar.getTime();

	}
	public String convertDateToDateName(Date date) {
		ZoneId zoneId = ZoneId.of("Asia/Bangkok");
		LocalDate localDate = date.toInstant().atZone(zoneId).toLocalDate();
		localDate.getDayOfWeek();
		return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
	}
	
	
	public void getMonth() {
        Date date = new Date();
        Date firstDate = getNextMonthFirstDate(date);
        Date lastDate = getLastDateOfMonth(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDate);

        dateTimeList = new ArrayList<>(); // Initialize list

        for (; !calendar.getTime().after(lastDate); calendar.add(Calendar.DAY_OF_MONTH, 1)) {
        	DateTimeRequest data = DateTimeRequest.builder()
                    .id(UUID.randomUUID().toString())
                    .dateUi(calendar.getTime()) // Set date in DatetimeRequest
                    .nameDay(this.convertDateToDateName(calendar.getTime()))
                    .build();
            		
            dateTimeList.add(data); // Add to list
        }
    }
	}
	


