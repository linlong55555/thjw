package lbw.yht.adv.domain;

/**
 *
 */

public class RelAdvType implements java.io.Serializable {

	private static final long serialVersionUID = -6537038164358497523L;

	private String relationId; // 广告筛选关系id
	private AdvType typeCode; // 广告类型id
	private AdvFiltrate filtrateCode; // 筛选条件id

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public AdvFiltrate getFiltrateCode() {
		return filtrateCode;
	}

	public void setFiltrateCode(AdvFiltrate filtrateCode) {
		this.filtrateCode = filtrateCode;
	}

	public AdvType getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(AdvType typeCode) {
		this.typeCode = typeCode;
	}

}