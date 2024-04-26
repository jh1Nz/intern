package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import com.summitthai.cr.apprentice.exception.DefaultException;
import com.summitthai.cr.apprentice.history.manager.HistoryManager;
import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.history.model.HistoryResponse;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.kids.manager.KidManager;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.kids.model.KidInfoResponse;
import com.summitthai.cr.apprentice.massage.MassageComplete;
import com.summitthai.cr.apprentice.massage.MassageError;
import com.summitthai.cr.apprentice.persanal.PersanalManager;
import com.summitthai.cr.apprentice.persanal.model.PersanalRequest;
import com.summitthai.cr.apprentice.persanal.model.PersanalResponse;
import com.summitthai.cr.apprentice.status.SimpleStatus;
import com.summitthai.cr.apprentice.utils.XspUtils;
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
public class Cep01002Bean extends ViewBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private DataKidEducationRequest formDialogEdu;
	private DataKidRequest formDialogKid;
	private PersanalRequest formEdit;
	private PersanalRequest formCriteria;
	private PersanalRequest formSelected;
	private List<DataKidRequest> kidList;
	private List<DataKidEducationRequest> eduList;
	private List<PersanalRequest> persanalList;
	private String update;
	private String delete;
	@Inject
	private PersanalManager persanalManager;
	
	
	@Inject
	private  KidManager kidManager;
	
	@Inject
	private  HistoryManager historyManager;
	

	

	public Cep01002Bean() {
		this.formDialogEdu = DataKidEducationRequest.builder().build();
		this.formDialogKid = DataKidRequest.builder().build();
		this.formEdit = PersanalRequest.builder().build();
		this.formCriteria = PersanalRequest.builder().build();
		this.formSelected = PersanalRequest.builder().build();

	}

	@PostConstruct
	public void init() {
		log.debug("Begin init");

		this.onPageSearch();
		

		log.debug("End init");
	}

	public void onPageSearch() {
		log.debug("Begin onPageInsert");

		this.mode = MODE_SEARCH;
		//PersanalResponse res = this.persanalManager.findByReq(PersanalRequest.builder().persanalInfoId("A001").build());
		//log.debug("PersanalResponse {}",res);
		this.persanalList = new ArrayList<>();
		log.debug("End onPageInsert");
		
		
	}
	

	public void onPageInsert() {
		log.debug("Begin onPageInsert");
		log.debug("This is Mode: {}", MODE_INSERT);
		this.mode = MODE_INSERT;
		this.resetEditButtonOnClick();
	

		log.debug("End onPageInsert");
		
	}
	
	public void onPageUpdate(ActionEvent event) {
		log.debug("Begin onPageUpdate");
			this.update = MODE_UPDATE;
			this.mode = update;
			log.debug("MODE :{}",update);
		log.debug("End onPageUpdate");
	}
	
	public void onPageDelete(ActionEvent event) {
		log.debug("Begin onPageDelete");
			this.delete = MODE_DELETE;
			this.mode = delete;
			log.debug("MODE :{}",delete);
		log.debug("End onPageDelete");
		
	}
	
	public void Persanal() {
		log.debug("ประเภทบุคลากร {}",this.formEdit.getEmpType());
		log.debug("เลขที่ตำแหน่ง {}",this.formEdit.getPosNum());
		log.debug("รหัสประจำตัว {}",this.formEdit.getEmpId());
		log.debug("เลขประจำตัวประชาชน {}",this.formEdit.getPerId());
		log.debug("คำนำหน้าชื่อ {}",this.formEdit.getNameTitle());
		log.debug("ชื่อ {}",this.formEdit.getFirstName());
		log.debug("นามสกุล {}",this.formEdit.getLastName());
		log.debug("ตำแหน่งในหน่วยงาน {}",this.formEdit.getPosJob());
		log.debug("หน่วยงาน {}",this.formEdit.getDepastment());
		
	}


	public void resetDialogOnClick() {
		log.debug("Begin Reset1");
		this.formDialogKid = DataKidRequest.builder()
				.kidInfoId(UUID.randomUUID().toString())
				.kidType("1")
				.kidIname("นาย")
				.kidTitle("นาย")
                .kidSex("M")
                .kidCheckStatus("true")
                .kid2CheckStatus("false")
                .build();
		log.debug("End Reset1");
		
	}
	public int loopNo() {
	    int currentNoTable = this.formDialogEdu.getNoTable();
	    currentNoTable++;
	    this.formDialogEdu.setNoTable(currentNoTable);
	    return currentNoTable;
	}

	public void resetDialogTwoOnClick() {
		log.debug("Begin Reset2");
		this.formDialogEdu = DataKidEducationRequest.builder()
												 .kidIname("นาย")
												 .eduGran("ปริญญาตรี")
												 .eduBranch("บริหารธุรกิจ")
												 .eduPlace("มหาวิทยาลัยเเม่ฟ้าหลวง")
												 .noTable(loopNo())
												 .eduPlacePart("ราชการ")
												 .eduPlaceType("สามัญศึกษา")
												 .build();
		log.debug("End Reset2");
		
	}

	public void closeDialogOnClick() {
		log.debug("Begin closeDialog");
		this.formDialogKid = DataKidRequest.builder().build();
		this.formDialogEdu = DataKidEducationRequest.builder().build();
		PrimeFaces.current().executeScript("PF('dlg001').hide()");
		
		log.debug("End closeDialog");
		
	}

	public String getFullname(String initialName, String firstName, String lastName) {
		return initialName + " " + firstName + " " + lastName;
	}

	public int calculateAge(Date birthDate) {
		LocalDate currentDate = LocalDate.now();
		LocalDate dateOfBirth = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return Period.between(dateOfBirth, currentDate).getYears();
	}
	

	public void addKidList() {
		log.debug("Begin AddKidTable");
		PrimeFaces.current().executeScript("PF('dlg001').hide()");
		
//				.kidInfoId(UUID.randomUUID().toString())
//				.kidMom(this.formDialogKid.getKidMom())
//				.kidDad(this.formDialogKid.getKidDad())
				this.formDialogKid.setKidFullName(this.getFullname(this.formDialogKid.getKidIname(), this.formDialogKid.getKidFname(),
						this.formDialogKid.getKidLname()));
				this.formDialogKid.setKidAge(calculateAge(this.formDialogKid.getKidBirtday()));

				
		this.kidList.add(formDialogKid);
		log.debug("End AddKidTable");
	}
	
	public void addEduList() {
		log.debug("Begin AddKidEducaTionTable");
		PrimeFaces.current().executeScript("PF('dlg002').hide()");
//		DataKidEducationRequest dataEdu = DataKidEducationRequest.builder()
//				.educationHistoryId(UUID.randomUUID().toString())
//				.noTable(this.formDialogEdu.getNoTable())
//				.eduYear(this.formDialogEdu.getEduYear())
//				.eduLevel(this.formDialogEdu.getEduLevel())
//				.kidFullname(this.getFullname(this.formDialogEdu.getKidIname(), this.formDialogEdu.getKidFname(),
//						this.formDialogEdu.getKidLname()))
//				.eduGran(this.formDialogEdu.getEduGran())
//				.eduBranch(this.formDialogEdu.getEduBranch())
//				.eduPlace(this.formDialogEdu.getEduPlace())
//				.build();
		
		
		this.formDialogEdu.setKidFullname(this.getFullname(this.formDialogEdu.getKidIname(), this.formDialogEdu.getKidFname(),
						this.formDialogEdu.getKidLname()));
		this.eduList.add(formDialogEdu);
		log.debug("End AddKidEducaTionTable");
	}
	
	public void removeDataList(int index) {
		log.debug("Begin removeDataList...");
		this.eduList.remove(index);
		log.debug("End removeDataList...");
	}
	public void testingOnclick() {
		log.debug("Begin testingOnclick...");
		log.debug("End testingOnclick...");
	}
	
	public void resetEditButtonOnClick() {
		log.debug("Begin ResetEdit");
		this.formEdit = PersanalRequest.builder()
				.empType("1")
				.nameTitle("01").build();
		this.kidList = new ArrayList<>();
		this.eduList = new ArrayList<>();
		this.formEdit.setKidslist(new ArrayList<>());
		this.formEdit.setEducationList(new ArrayList<>());
		log.debug("End ResetEdit");
		
	}
	
	
	
	private void prepareData() {
		log.debug("Begin prepareData..");
		try {
			if (this.mode.equals(MODE_INSERT)) {
				this.formEdit.setPersanalInfoId(UUID.randomUUID().toString());
			}
		} catch (Exception e) {
			log.error("Exception prepareData",e);
		}finally {
			log.debug("End prepareData..");
		}	
		
	}
	
	public void insertButtonOnClick() {
		log.debug("Begin insertButtonOnClick");
		PersanalResponse res = null;
		try {
			this.prepareData();
			if (!this.kidList.isEmpty()) {
				this.formEdit.getKidslist().addAll(this.kidList);
			} 
			if (!this.eduList.isEmpty()) {
				this.formEdit.getEducationList().addAll(this.eduList);
			} 
			res = this.persanalManager.insert(this.formEdit);
            if (res.getStatus().equals(SimpleStatus.SUCCESS)) {
            	XspUtils.scrollToTop();
                this.onPageSearch();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", MassageComplete.INSERT_COMPLETE));
                
             } else {
            	 XspUtils.scrollToTop();
            	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MassageError.INSERT_FAIL));
                 throw new DefaultException(MassageError.INSERT_FAIL);
             }
            
		} catch (DefaultException de) {
			log.error("DefaultException insertButtonOnClick {}",de);
		} catch (Exception e) {
			log.error("Exception insertButtonOnClick {}",e);
		} finally {
			log.debug("End insertButtonOnClick");
		}
	}
	
	public void setdialog() {
		log.debug("Begin Reset1");
		PrimeFaces.current().executeScript("PF('dlg001').hide()");
		this.formDialogKid = DataKidRequest.builder()
				.kidInfoId(UUID.randomUUID().toString())
				.kidType("01")
				.kidIname("01")
				.kidTitle("01")
                .kidSex("M")
                .kidCheckStatus("true")
                .kid2CheckStatus("false")
                .build();
		log.debug("End Reset1");
		
	}
	
	public void setdialogEdu() {
		log.debug("Begin Reset1");
		PrimeFaces.current().executeScript("PF('dlg002').hide()");
		this.formDialogEdu = DataKidEducationRequest.builder()
				.educationHistoryId(UUID.randomUUID().toString())
				.kidIname("01")
				.eduPlacePart("01")
				.eduPlace("01")
				.eduBranch("01")
				.eduGran("01")
				.eduPlace("01")
				.eduProvine("01")
				.eduDistrict("01")
				.eduSusdistrict("01")
				.eduPlaceType("01")
                .build();
		log.debug("End Reset1");
		
	}
	
	public void searchButtonOnclick() {
		log.debug("Begin searchButtonOnclick");
		PersanalResponse res = null;
		
		this.persanalList = new ArrayList<>();
		try {
			res = this.persanalManager.findByReq(formCriteria);
			
			if(!res.getDataRequestList().isEmpty()) {
				
				this.persanalList.addAll(res.getDataRequestList());
			}
			
			
		} catch (Exception e) {
			log.debug("Exception searchButtonOnclick {}",e);
			
		}
		
	}
	
	public void onRowSelect(SelectEvent event) {
		  log.debug("Begin onRowSelect...");


		  try {
			  	   this.mode = MODE_UPDATE;
		           this.formEdit = PersanalRequest.builder().build();
		           this.formEdit = this.formSelected;	            
		           DataKidRequest kidreq = DataKidRequest.builder().persanalInfoId(this.formEdit.getPersanalInfoId()).build();
		           KidInfoResponse res = this.kidManager.findByReq(kidreq);
		           this.formEdit.setKidslist(res.getDataRequestList());
		           this.kidList = this.formEdit.getKidslist();
					
					DataKidEducationRequest eduReq = DataKidEducationRequest.builder().persanalInfoId(this.formEdit.getPersanalInfoId()).build();
					HistoryResponse eduRes = this.historyManager.findByReq(eduReq);
					this.formEdit.setEducationList(eduRes.getDataRequestList());
					this.eduList = this.formEdit.getEducationList();
		        } catch (Exception e) {
		         log.error("Error : {}", e);
		        } finally {
		         log.debug("End onRowSelect...");
		        }
		  
		  
		  
		 }
	
	public void updateButtonOnClick() {
		log.debug("Begin updateButtonOnClick ");
		PersanalResponse perRes = null;
		
		try {
			this.prepareData();
			
			perRes = this.persanalManager.update(this.formEdit);
			if(perRes.getStatus().equals("SUCCESS")){
				
				this.onPageSearch();
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "UPDATE_COMPLETE"));
			}else {
				throw new DefaultException("UPDATE_FAIL");
				
			}
			
			
		} catch (DefaultException ex) {
            log.error("Exception :{}", ex);
            messageError(ex);
        } catch (Exception e) {
        	messageError(e);
		}finally {
			XspUtils.scrollToTop();

			log.debug("End...updateButtonOnClick...");
            perRes = null;
		}
		
	}
	public void deleteButtonOnClick() {
		log.debug("Begin deletButtonOnClick...");
		PersanalResponse resPer = null;
		
		try {
			resPer = this.persanalManager.delete(this.formEdit);
			
			if (resPer.getStatus().equals("SUCCESS")) {
				this.onPageSearch();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "DELETE_COMPLETE"));
			}else {
                throw new DefaultException("DELETE_FAIL");
            } 
		} catch (Exception e) {
			log.error("Exception :{}", e);
            messageError(e);
		}finally {
			log.debug("End...deletButtonOnClick...");
			resPer = null;
		}
			
		}
		
		
	}
	
	


