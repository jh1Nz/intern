package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.summitthai.cr.apprentice.calculation.model.CalculationRequest;
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
public class View01002Bean extends ViewBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String search;
	private String insert;
	private String delete;
	private String view;
	private String process;
	private String report;
	private String update;
	private CalculationRequest formEdit;
	
	
	public View01002Bean() {
		this.formEdit = CalculationRequest.builder().build();
			
	}
	
	@PostConstruct
	public void init() {
		log.debug("Begin init");
		this.search= MODE_SEARCH;
		this.mode = search;
		this.resetPageInsert();
		log.debug("MODE :{}",search);
		log.debug("End init");
	}
	public void onPageInsert(ActionEvent event)  {
		log.debug("Begin onPageInsert");
		this.insert= MODE_INSERT;
		this.mode = insert;
		log.debug("MODE :{}",insert);
		log.debug("End onPageInsert");
	}
	public void onPageSearch(ActionEvent event) {
		log.debug("Begin onPageSearch");
			this.search = MODE_SEARCH;
			this.mode = search;
			log.debug("MODE :{}",search);
		log.debug("End onPageSearch");
	}
	public void onPageUpdate(ActionEvent event) {
		log.debug("Begin onPageUpdate");
			this.update = MODE_UPDATE;
			this.mode = update;
			log.debug("MODE :{}",update);
		log.debug("End onPageUpdate");
	}
	public void onPageProcess(ActionEvent event) {
		log.debug("Begin onPageProcess");
			this.process = MODE_PROCESS;
			this.mode = process;
			log.debug("MODE :{}",process);
		log.debug("End onPageProcess");
	}
	public void onPageView(ActionEvent event) {
		log.debug("Begin onPageView");
			this.view = MODE_VIEW;	
			this.mode = view;
			log.debug("MODE :{}",view);
		log.debug("End onPageView");
		
	}
	public void onPageReport(ActionEvent event) {
		log.debug("Begin onPageReport");
			this.report = MODE_REPORT;	
			this.mode = report;
			log.debug("MODE :{}",report);
		log.debug("End onPageReport");
		
	}
	
	public void onPageDelete(ActionEvent event) {
		log.debug("Begin onPageDelete");
			this.delete = MODE_DELETE;
			this.mode = delete;
			log.debug("MODE :{}",delete);
		log.debug("End onPageDelete");
		
	}
	
	
	public void integerAddvalue() {
		  log.debug("Begin integerAddvalue"); 
		  int resultInt =0;
		  int numInt1=this.formEdit.getNumInt1();
		  int numInt2=this.formEdit.getNumInt2();
		  int Oper =this.formEdit.getOp();
		  
		  if (numInt1>=0 && numInt2>=0 && Oper == 1 ) {
			resultInt = numInt1 + numInt2;
			this.formEdit.setResultInt(resultInt);
			log.debug("test add");
		}
		  else if (numInt1>=0 && numInt2>=0 && Oper == 2 ) {
			  resultInt = numInt1 - numInt2;   
			  this.formEdit.setResultInt(resultInt);
				log.debug("test Minas");
		}
		  else if (numInt1>=0 && numInt2>=0 && Oper == 3 ) {
			  resultInt = numInt1 * numInt2;
			  this.formEdit.setResultInt(resultInt);
				log.debug("test multi");
		}
		  else {
			  resultInt = numInt1 / numInt2;
			  this.formEdit.setResultInt(resultInt);
				log.debug("test device");

		}

		  log.debug("End integerAddvalue");
		 }
	
	
	public void bigDecimalAddnumber() {
		
			log.debug("Begin CalculateBigDecimal");
			int operator = this.formEdit.getOp();
			switch (operator) {
			  case 1:
				  BigDecimal sum = this.formEdit.getNumBig1().add( this.formEdit.getNumBig2());
				  this.formEdit.setResultBig(sum);
			    break;
			  case 2:
				  BigDecimal difference = this.formEdit.getNumBig1().subtract( this.formEdit.getNumBig2());
				  this.formEdit.setResultBig(difference);
				    break;
			  case 3:
				  BigDecimal product = this.formEdit.getNumBig1().multiply( this.formEdit.getNumBig2());
				  this.formEdit.setResultBig(product);
				    break;
			  case 4:
				  BigDecimal quotient = this.formEdit.getNumBig1().divide( this.formEdit.getNumBig2());
				  this.formEdit.setResultBig(quotient);
				    break;
			  default:
			    log.debug("Something Wrong");
			}
			log.debug("End CalculateBigDecimal");
		}
	
		  
		  
	
	public void resetPageInsert() {
		this.formEdit = CalculationRequest.builder().numInt1(0)
													.numInt2(0)
													.numBig1(new BigDecimal(0))
													.numBig2(new BigDecimal(0))
													.resultInt(0)
													.resultBig(new BigDecimal(0))
													.Op(1)
													.checkBoolean(true)
													.build();
	}
	
	
	
}
	
	
	
	
	
