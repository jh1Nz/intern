package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.summitthai.cr.apprentice.data.HomeData;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.manager.XspPersonManageable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@ViewScoped
@Data
public class HomeBean implements Serializable{
	
	private static final long serialVersionUID = -8298776707965382136L;
	
	@Inject
	private XspPersonManageable service;
	
	private List<HomeData> homeDataList;
	
	private List<XspPerson> xspPersonList;
	
	@PostConstruct
	public void init() {
		log.debug("Begin init...");
		this.defaultHomeData();
		log.debug("End init...");
	}

	public void search() {
		log.debug("Begin search...");
		this.xspPersonList = new ArrayList<>();
		//this.xspPersonList = this.service.getData();
		log.debug("End search...");
	}
	
	private void defaultHomeData() {
		this.homeDataList = new ArrayList<>();
		HomeData homeData = HomeData.
									builder().
										code("A").
										name("B").
										category("C").
										quantity("D").
									build();
		this.homeDataList.add(homeData);
		
	}

}
