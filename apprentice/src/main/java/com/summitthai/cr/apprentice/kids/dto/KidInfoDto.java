package com.summitthai.cr.apprentice.kids.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.kids.entity.Kidinfo;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
@ToString
public class KidInfoDto  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public DataKidRequest entityToReq(Kidinfo entity) {
		DataKidRequest req = null;
		req = DataKidRequest.builder().build();
	 	
		try {
		       
		       
		       req.setKidType		(entity.getKidType());
		       req.setKidSex		(entity.getKidSex());
		       req.setKidIname		(entity.getKidIname());
		       req.setKidFname		(entity.getKidFname());
		       req.setKidLname		(entity.getKidLname());
		       req.setPersanalInfoId	(entity.getPersanalInfoId());
		       req.setKidBirtday	(XspUtils.convertStringToDate(entity.getKidBirtday()));
		       req.setKidMom		(entity.getKidMom());
		       req.setKidDad		(entity.getKidDad());
		       req.setKidCheckStatus	(entity.getKidCheckStatus());
		       req.setKid2CheckStatus	(entity.getKid2CheckStatus());
		       req.setKidTitle		(entity.getKidTitle());
		       req.setKidFirstNameDie	(entity.getKidFirstNameDie());
		       req.setKidLastNameDie	(entity.getKidLastNameDie());
		       req.setKidChangeBirt		(XspUtils.convertStringToDate(entity.getKidChangeBirt()));
		       req.setKidInfoId			(entity.getKidInfoId());
		       req.setKidDateDie		(XspUtils.convertStringToDate(entity.getKidDateDie()));
		       req.setKidPersanalId		(entity.getKidPersanalId());
		       req.setKidAge(entity.getKidAge());

		//       req.setActiveFlag             (entity.getActiveFlag());
		//       req.setCreateDateTime         (entity.getCreateDateTime());
		//       req.setCreateUser             (entity.getCreateUser());
		//       req.setCreateProcess          (entity.getCreateProcess());
		//       req.setUpdDateTime            (entity.getUpdDateTime());
		//       req.setUpdUser                (entity.getUpdUser());
		//       req.setUpdProcess             (entity.getUpdProcess());

	       return req;

		   } catch (Exception e) {
		       
		       return null;
		   } finally {
		       req = null;
		   }
		}
	
	public Kidinfo reqToEntity(Kidinfo entity, DataKidRequest req) {

		entity.setKidType		(req.getKidType());
		entity.setKidSex		(req.getKidSex());
		entity.setKidIname		(req.getKidIname());
		entity.setKidFname		(req.getKidFname());
		entity.setKidLname		(req.getKidLname());
		entity.setPersanalInfoId	(req.getPersanalInfoId());
		entity.setKidBirtday	(XspUtils.convertDateToString(req.getKidBirtday()));
		entity.setKidMom		(req.getKidMom());
		entity.setKidDad		(req.getKidDad());
		entity.setKidCheckStatus	(req.getKidCheckStatus());
		entity.setKid2CheckStatus	(req.getKid2CheckStatus());
		entity.setKidTitle		(req.getKidTitle());
		entity.setKidFirstNameDie	(req.getKidFirstNameDie());
		entity.setKidLastNameDie	(req.getKidLastNameDie());
		entity.setKidChangeBirt		(XspUtils.convertDateToString(req.getKidChangeBirt()));
		entity.setKidInfoId			(req.getKidInfoId());
		entity.setKidDateDie	(XspUtils.convertDateToString(req.getKidDateDie()));
		entity.setKidPersanalId		(req.getKidPersanalId());
		entity.setKidAge (req.getKidAge());



   return entity;
}
}
