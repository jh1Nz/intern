package com.summitthai.cr.apprentice.history.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.history.entity.History;
import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
@Data
@Slf4j
@ToString
public class HistoryDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public DataKidEducationRequest entityToReq(History entity) {
		DataKidEducationRequest req = null;
		req = DataKidEducationRequest.builder().build();
	 	
		try {
		       
		       
		       req.setKidIname		(entity.getKidIname());
		       req.setKidFname		(entity.getKidFname());
		       req.setKidLname		(entity.getKidLname());
		       
		       req.setEduYear		(entity.getEduYear());
		       req.setEduLevel		(entity.getEduLevel());
		       req.setEduStart		(XspUtils.convertStringToDate(entity.getEduStart()));
		       req.setEduPlacePart		(entity.getEduPlacePart());
		       req.setEduPlaceType		(entity.getEduPlaceType());
		       req.setEduGran		(entity.getEduGran());
		       req.setEduBranch		(entity.getEduBranch());
		       req.setEduPlace		(entity.getEduPlace());
		       req.setEduProvine		(entity.getEduProvine());
		       req.setEduDistrict		(entity.getEduDistrict());
		       req.setEduSusdistrict		(entity.getEduSusdistrict());
		       req.setEduMark		(entity.getEduMark());
		       req.setPersanalInfoId (entity.getPersanalInfoId());
		       req.setEducationHistoryId (entity.getEducationHistoryId());

	       return req;

		   } catch (Exception e) {
		       
		       return null;
		   } finally {
		       req = null;
		   }
		}
	
	public History reqToEntity(History entity, DataKidEducationRequest req) {

		entity.setKidIname		(req.getKidIname());
		entity.setKidFname		(req.getKidFname());
		entity.setKidLname		(req.getKidLname());
		
		entity.setEduYear		(req.getEduYear());
		entity.setEduLevel		(req.getEduLevel());
		entity.setEduStart		(XspUtils.convertDateToString(req.getEduStart()));
		entity.setEduPlacePart		(req.getEduPlacePart());
		entity.setEduPlaceType		(req.getEduPlaceType());
	       entity.setEduGran		(req.getEduGran());
	       entity.setEduBranch		(req.getEduBranch());
	       entity.setEduPlace		(req.getEduPlace());
	       entity.setEduProvine		(req.getEduProvine());
	       entity.setEduDistrict		(req.getEduDistrict());
	       entity.setEduSusdistrict		(req.getEduSusdistrict());
	       entity.setEduMark		(req.getEduMark());
       entity.setPersanalInfoId (req.getPersanalInfoId());
       entity.setEducationHistoryId (req.getEducationHistoryId());



   return entity;
}

}
