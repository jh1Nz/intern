package com.summitthai.cr.apprentice.massage;

import java.io.Serializable;

public class MassageError implements Serializable{
	    
	    private static final long serialVersionUID = 1L;
	    
	    public static final String INSERT_FAIL = "ข้อมูลไม่สามารถเพิ่มข้อมูลได้ กรุณาติดต่อผู้ดูแลระบบ";
	    public static final String UPDATE_FAIL = "ข้อมูลไม่สามารถแก้ไขข้อมูลได้ กรุณาติดต่อผู้ดูแลระบบ";
	    public static final String DELETE_FAIL = "ข้อมูลไม่สามารถลบข้อมูลได้ กรุณาติดต่อผู้ดูแลระบบ";
	    public static final String PROCESS_FAIL = "ข้อมูลไม่สามารถประมวลผลข้อมูลได้ กรุณาติดต่อผู้ดูแลระบบ";
	    
	}

