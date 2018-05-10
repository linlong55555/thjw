package lbw.yht.adv.domain;


/**
 *
 */

public class RelAdvLocation implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;
	
	private String relationId; //广告筛选关系id
	private AdvLocation advId; //广告位id
	private AdvFiltrate filtrateCode; //筛选条件id
	
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public AdvLocation getAdvId() {
		return advId;
	}
	public void setAdvId(AdvLocation advId) {
		this.advId = advId;
	}
	public AdvFiltrate getFiltrateCode() {
		return filtrateCode;
	}
	public void setFiltrateCode(AdvFiltrate filtrateCode) {
		this.filtrateCode = filtrateCode;
	}


	
	
	
}